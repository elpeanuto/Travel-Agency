package edu.elpeanuto.tms.servies.dao.daoImpl;

import edu.elpeanuto.tms.model.User;
import edu.elpeanuto.tms.servies.dao.UserDAO;
import edu.elpeanuto.tms.servies.dao.db.DBConnection;
import edu.elpeanuto.tms.servies.dao.db.PoolConnectionBuilder;
import edu.elpeanuto.tms.servies.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class that communicate with database (user, userInfo tables)
 *
 * @see edu.elpeanuto.tms.servies.dao.BaseDAO
 * @see edu.elpeanuto.tms.servies.dao.UserDAO
 */

public class UserDAOImpl implements UserDAO {
    private final DBConnection dbConnection;

    public UserDAOImpl() {
       dbConnection = PoolConnectionBuilder.getInstance();
    }

    public UserDAOImpl(DBConnection dbConnection) {
       this.dbConnection = dbConnection;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dbConnection.getConnection();
    }

    @Override
    public boolean resetPassword(String email, String password) throws DAOException {
        String resetPasswordPattern = "UPDATE user SET password = ? WHERE email = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(resetPasswordPattern)
        ) {
            stmt.setString(1, password);
            stmt.setString(2, email);

            int isCompleted = stmt.executeUpdate();

            return isCompleted > 0;

        } catch (SQLException e) {
            throw new DAOException("SQLException in resetPassword(String email, String password)", e);
        }
    }

    @Override
    public boolean promote(Long id, String status) throws DAOException {
        String promotePattern = "UPDATE user SET status = ? WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(promotePattern)
        ) {
            stmt.setString(1, status);
            stmt.setLong(2, id);

            int rowCounter = stmt.executeUpdate();

            return rowCounter > 0;

        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in promote(Long id, String status), params: id: %s status: %s", id.toString(), status), e);
        }
    }

    @Override
    public boolean isEmailOccupied(String email) throws DAOException {
        String isEmailOccupied = "SELECT * FROM user WHERE email = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(isEmailOccupied)
        ) {
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            throw new DAOException("SQLException in isEmailOccupied(String email)", e);
        }

    }

    @Override
    public boolean userVerify(String email, String password) throws DAOException {
        String userVerifyPattern = "SELECT * FROM user WHERE email = ? and password = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(userVerifyPattern)
        ) {
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            throw new DAOException("SQLException in userVerify(String email, String password)", e);
        }
    }

    @Override
    public Optional<User> get(Long id) throws DAOException {
        String getPattern = "SELECT * FROM user INNER JOIN userinfo ON user.id = userInfo.id WHERE user.id=?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(getPattern)
        ) {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            return getUser(rs);
        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in get(Long id), params: %s", id.toString()), e);
        }
    }

    @Override
    public Optional<User> get(String email) throws DAOException {
        String getPattern = "SELECT * FROM user INNER JOIN userinfo ON user.userInfoId = userInfo.id WHERE user.email=?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(getPattern)
        ) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            return getUser(rs);
        } catch (SQLException e) {
            throw new DAOException("SQLException in get(String email)", e);
        }
    }

    @Override
    public List<User> getPaginationByStatus(Integer start, Integer numOfStrings, String status) throws DAOException {
        String getUsersToPromotePattern = configureGetUsersToPromoteQuery(status);
        List<User> userList = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(getUsersToPromotePattern)
        ) {
            int parameterPosition = 1;

            stmt.setString(parameterPosition++, User.STATUS.Banned.name());
            stmt.setString(parameterPosition++, User.STATUS.Client.name());

            if (status.equals(User.STATUS.Admin.name()) || status.equals(User.STATUS.Leader.name()))
                stmt.setString(parameterPosition++, User.STATUS.Manager.name());
            if (status.equals(User.STATUS.Leader.name()))
                stmt.setString(parameterPosition++, User.STATUS.Admin.name());

            stmt.setInt(parameterPosition++, start);
            stmt.setInt(parameterPosition, numOfStrings);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                userList.add(new User(rs.getLong("id"), rs.getLong("userInfoId"),
                        rs.getString("name"), "password", rs.getString("email"),
                        rs.getString("phoneNumber"), rs.getString("status")));
            }

            return userList;

        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in getPaginationByStatus(Integer start, Integer numOfStrings," +
                            " String status), params: start: %s, numOfStrings: %s , status: %s", start.toString(), numOfStrings, status), e);
        }
    }

    @Override
    public Optional<Integer> getNumberOfNotesByStatus(String status) throws DAOException {
        StringBuilder getUsersToPromotePattern = new StringBuilder();

        getUsersToPromotePattern.append("SELECT COUNT(*) FROM user INNER JOIN userinfo on user.userInfoId = userinfo.id WHERE status = ? or status = ?");

        if (status.equals(User.STATUS.Admin.name()) || status.equals(User.STATUS.Leader.name()))
            getUsersToPromotePattern.append(" or status = ?");

        if (status.equals(User.STATUS.Leader.name()))
            getUsersToPromotePattern.append(" or status = ?");

        getUsersToPromotePattern.append(";");


        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(getUsersToPromotePattern.toString())
        ) {
            int parameterPosition = 1;

            stmt.setString(parameterPosition++, "Banned");
            stmt.setString(parameterPosition++, "Client");

            if (status.equals(User.STATUS.Admin.name()) || status.equals(User.STATUS.Leader.name()))
                stmt.setString(parameterPosition++, User.STATUS.Manager.name());
            if (status.equals(User.STATUS.Leader.name()))
                stmt.setString(parameterPosition, User.STATUS.Admin.name());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in getNumberOfNotesByStatus(String status), params: %s", status), e);
        }
    }

    @Override
    public List<User> getAll() throws DAOException {
        String getALlPattern = "SELECT * FROM user INNER JOIN userinfo on user.userInfoId = userinfo.id";

        List<User> userList = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(getALlPattern)
        ) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                userList.add(new User(rs.getLong("id"), rs.getLong("userInfoId"),
                        rs.getString("name"), "password", rs.getString("email"),
                        rs.getString("phoneNumber"), rs.getString("status")));
            }

            return userList;

        } catch (SQLException e) {
            throw new DAOException("SQLException in getAll()", e);
        }
    }

    @Override
    public boolean save(User user) throws DAOException {
        String userSavePattern = "INSERT INTO user(userInfoId, name, password, email, phoneNumber, status) values(?,?,?,?,?,?)";
        String userInfoSavePattern = "INSERT INTO userinfo(name,surname,dateOfBirth,gender,citizenship," +
                "passportSerial,passportNumber,passportValidDate) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection con = getConnection()) {
            try (PreparedStatement stmt1 = con.prepareStatement(userSavePattern);
                 PreparedStatement stmt2 = con.prepareStatement(userInfoSavePattern, Statement.RETURN_GENERATED_KEYS)) {
                con.setAutoCommit(false);

                userInfoStmtSet(user, stmt2);

                int rowCounter1 = stmt2.executeUpdate();

                try (ResultSet generatedKeys = stmt2.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        stmt1.setLong(1, generatedKeys.getLong(1));
                        stmt1.setString(2, user.getName());
                        stmt1.setString(3, user.getPassword());
                        stmt1.setString(4, user.getEmail());
                        stmt1.setString(5, user.getPhoneNumber());
                        stmt1.setString(6, user.getStatus());
                    } else {
                        logger.error("Fatal error in save(User user), no ID obtained.");
                        throw new RuntimeException();
                    }
                }

                int rowCounter2 = stmt1.executeUpdate();

                con.commit();

                return rowCounter1 > 0 && rowCounter2 > 0;
            } catch (SQLException e) {
                con.rollback();
                throw new SQLException(e);
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in save(User user), params: %s", user.toString()), e);
        }
    }


    @Override
    public boolean update(User user) throws DAOException {
        String userUpdatePattern = "UPDATE user SET name=?, email=?, phoneNumber=? WHERE id=?";
        String userInfoSavePattern = "UPDATE userinfo SET name=?,surname=?,dateOfBirth=?,gender=?,citizenship=?," +
                "passportSerial=?,passportNumber=?,passportValidDate=? WHERE id=?";

        try (Connection con = getConnection()) {
            try (PreparedStatement stmt1 = con.prepareStatement(userUpdatePattern);
                 PreparedStatement stmt2 = con.prepareStatement(userInfoSavePattern)) {
                con.setAutoCommit(false);

                stmt1.setString(1, user.getName());
                stmt1.setString(2, user.getEmail());
                stmt1.setString(3, user.getPhoneNumber());
                stmt1.setLong(4, user.getId());

                userInfoStmtSet(user, stmt2);
                stmt2.setLong(9, user.getUserInfoId());

                int rowCounter1 = stmt1.executeUpdate();
                int rowCounter2 = stmt2.executeUpdate();

                con.commit();

                return rowCounter1 > 0 && rowCounter2 > 0;
            } catch (SQLException e) {
                con.rollback();
                throw new SQLException(e);
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in update(User user), params: %s", user.toString()), e);
        }
    }

    private void userInfoStmtSet(User user, PreparedStatement stmt2) throws SQLException {
        stmt2.setString(1, user.getRealName());
        stmt2.setString(2, user.getRealSurName());
        stmt2.setString(3, user.getDateOfBirth());
        stmt2.setString(4, user.getGender());
        stmt2.setString(5, user.getCitizenship());
        stmt2.setString(6, user.getPassportSerial());
        stmt2.setString(7, user.getPassportNumber());
        stmt2.setString(8, user.getPassportValidDate());
    }

    private Optional<User> getUser(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return Optional.of(new User(rs.getLong("id"), rs.getLong("userInfoID"), rs.getString("name"),
                    null, rs.getString("email"), rs.getString("phoneNumber"), rs.getString("status"),
                    rs.getString("userInfo.name"), rs.getString("surname"), rs.getString("gender"),
                    rs.getString("dateOfBirth"), rs.getString("citizenship"),
                    rs.getString("passportSerial"), rs.getString("passportNumber"), rs.getString("passportValidDate")));
        }

        return Optional.empty();
    }

    private String configureGetUsersToPromoteQuery(String status){
        StringBuilder getUsersToPromotePattern = new StringBuilder();

        getUsersToPromotePattern.append("SELECT * FROM user INNER JOIN userinfo on user.userInfoId = userinfo.id WHERE status = ? or status = ?");

        if (status.equals(User.STATUS.Admin.name()) || status.equals(User.STATUS.Leader.name()))
            getUsersToPromotePattern.append(" or status = ?");

        if (status.equals(User.STATUS.Leader.name()))
            getUsersToPromotePattern.append(" or status = ?");

        getUsersToPromotePattern.append("LIMIT ?, ?;");

        return getUsersToPromotePattern.toString();
    }
}
