package edu.elpeanuto.tms.servies.dao.daoImpl;

import edu.elpeanuto.tms.model.User;
import edu.elpeanuto.tms.model.enums.Gender;
import edu.elpeanuto.tms.model.enums.UserStatus;
import edu.elpeanuto.tms.servies.dao.UserDAO;
import edu.elpeanuto.tms.servies.dao.db.DBConnection;
import edu.elpeanuto.tms.servies.dao.db.PoolConnectionBuilder;
import edu.elpeanuto.tms.servies.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class that communicate with database (user, user_info tables)
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
        String resetPasswordPattern = "UPDATE users SET password = ? WHERE email = ?";

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
    public boolean promote(Long id, UserStatus status) throws DAOException {
        String promotePattern = "UPDATE users SET status = ? WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(promotePattern)
        ) {
            stmt.setString(1, status.name());
            stmt.setLong(2, id);

            int rowCounter = stmt.executeUpdate();

            return rowCounter > 0;

        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in promote(Long id, String status), params: id: %s status: %s", id.toString(), status), e);
        }
    }

    @Override
    public boolean isEmailOccupied(String email) throws DAOException {
        String isEmailOccupied = "SELECT * FROM users WHERE email = ?";

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
        String userVerifyPattern = "SELECT * FROM users WHERE email = ? and password = ?";

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
        String getPattern = "SELECT * FROM users INNER JOIN user_info ui ON users.user_info_id = ui.id WHERE users.id=?";

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
        String getPattern = "SELECT * FROM users INNER JOIN user_info ui ON users.user_info_id = ui.id WHERE users.email=?";

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
    public List<User> getPaginationByStatus(Integer start, Integer numOfStrings, UserStatus status) throws DAOException {
        String getUsersToPromotePattern = configureGetUsersToPromoteQuery(status.name());

        List<User> userList = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(getUsersToPromotePattern)
        ) {
            int parameterPosition = 1;

            stmt.setString(parameterPosition++, UserStatus.Banned.name());
            stmt.setString(parameterPosition++, UserStatus.Client.name());

            if (status.equals(UserStatus.Admin) || status.equals(UserStatus.Leader))
                stmt.setString(parameterPosition++, UserStatus.Manager.name());
            if (status.equals(UserStatus.Leader))
                stmt.setString(parameterPosition++, UserStatus.Admin.name());

            stmt.setInt(parameterPosition++, start);
            stmt.setInt(parameterPosition, numOfStrings);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                userList.add(new User(rs.getLong("id"), rs.getLong("user_info_id"),
                        rs.getString("name"), "password", rs.getString("email"),
                        rs.getString("phone_number"), UserStatus.valueOf(rs.getString("status"))));
            }

            return userList;

        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in getPaginationByStatus(Integer start, Integer numOfStrings," +
                    " String status), params: start: %s, numOfStrings: %s , status: %s", start.toString(), numOfStrings, status), e);
        }
    }

    @Override
    public Optional<Integer> getNumberOfNotesByStatus(UserStatus status) throws DAOException {
        StringBuilder getUsersToPromotePattern = new StringBuilder();

        getUsersToPromotePattern.append("SELECT COUNT(*) FROM users INNER JOIN user_info ui ON users.user_info_id = ui.id WHERE status = ? or status = ?");

        boolean isAdminOrLeader = status.equals(UserStatus.Admin) || status.equals(UserStatus.Leader);

        if (isAdminOrLeader)
            getUsersToPromotePattern.append(" or status = ?");

        if (status.equals(UserStatus.Leader))
            getUsersToPromotePattern.append(" or status = ?");

        getUsersToPromotePattern.append(";");


        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(getUsersToPromotePattern.toString())
        ) {
            int parameterPosition = 1;

            stmt.setString(parameterPosition++, UserStatus.Banned.name());
            stmt.setString(parameterPosition++, UserStatus.Client.name());

            if (isAdminOrLeader)
                stmt.setString(parameterPosition++, UserStatus.Manager.name());
            if (status.equals(UserStatus.Leader))
                stmt.setString(parameterPosition, UserStatus.Admin.name());

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
        String getALlPattern = "SELECT * FROM users INNER JOIN user_info ui ON users.user_info_id = ui.id";

        List<User> userList = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(getALlPattern)
        ) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                userList.add(new User(rs.getLong("id"), rs.getLong("user_info_id"),
                        rs.getString("name"), "password", rs.getString("email"),
                        rs.getString("phone_number"), UserStatus.valueOf(rs.getString("status"))));
            }

            return userList;

        } catch (SQLException e) {
            throw new DAOException("SQLException in getAll()", e);
        }
    }

    @Override
    public boolean save(User user) throws DAOException {
        String userSavePattern = "INSERT INTO users(user_info_id, name, password, email, phone_number, status) values(?,?,?,?,?,?)";
        String userInfoSavePattern = "INSERT INTO user_info(name,surname,date_of_birth,gender,nationality," +
                "passport_serial,passport_number,passport_valid_date) VALUES (?,?,?,?,?,?,?,?)";

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
                        stmt1.setString(6, user.getStatus().name());
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
        String userUpdatePattern = "UPDATE users SET name=?, phone_number=? WHERE id=?";
        String userInfoSavePattern = "UPDATE user_info SET name=?,surname=?,date_of_birth=?,gender=?,nationality=?," +
                "passport_serial=?,passport_number=?,passport_valid_date=? WHERE id=?";

        try (Connection con = getConnection()) {
            try (PreparedStatement stmt1 = con.prepareStatement(userUpdatePattern);
                 PreparedStatement stmt2 = con.prepareStatement(userInfoSavePattern)) {
                con.setAutoCommit(false);

                stmt1.setString(1, user.getName());
                stmt1.setString(2, user.getPhoneNumber());
                stmt1.setLong(3, user.getId());

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
        stmt2.setString(2, user.getRealSurname());
        stmt2.setString(3, user.getDateOfBirth());
        stmt2.setString(4, user.getGender() == null ? null : user.getGender().name());
        stmt2.setString(5, user.getCitizenship());
        stmt2.setString(6, user.getPassportSerial());
        stmt2.setString(7, user.getPassportNumber());
        stmt2.setString(8, user.getPassportValidDate());
    }

    private Optional<User> getUser(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return Optional.of(new User(rs.getLong("id"), rs.getLong("user_info_id"), rs.getString("name"),
                    null, rs.getString("email"), rs.getString("phone_number"), UserStatus.valueOf(rs.getString("status")),
                    rs.getString("ui.name"), rs.getString("surname"), rs.getString("gender") == null ? null : Gender.valueOf(rs.getString("gender")),
                    rs.getString("date_of_birth"), rs.getString("nationality"),
                    rs.getString("passport_serial"), rs.getString("passport_number"), rs.getString("passport_valid_date")));
        }

        return Optional.empty();
    }

    private String configureGetUsersToPromoteQuery(String status) {
        StringBuilder getUsersToPromotePattern = new StringBuilder();

        getUsersToPromotePattern.append("SELECT * FROM users INNER JOIN user_info ui ON users.user_info_id = ui.id WHERE status = ? or status = ?");

        if (status.equals(UserStatus.Admin.name()) || status.equals(UserStatus.Leader.name()))
            getUsersToPromotePattern.append(" or status = ?");

        if (status.equals(UserStatus.Leader.name()))
            getUsersToPromotePattern.append(" or status = ?");

        getUsersToPromotePattern.append(" LIMIT ?, ?;");

        return getUsersToPromotePattern.toString();
    }
}
