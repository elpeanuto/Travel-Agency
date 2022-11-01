package edu.elpeanuto.tms.servies.dao.daoImpl;

import edu.elpeanuto.tms.model.Order;
import edu.elpeanuto.tms.model.enums.OrderStatus;
import edu.elpeanuto.tms.servies.dao.OrderDAO;
import edu.elpeanuto.tms.servies.dao.db.DBConnection;
import edu.elpeanuto.tms.servies.dao.db.PoolConnectionBuilder;
import edu.elpeanuto.tms.servies.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class that communicate with database (order table)
 * @see edu.elpeanuto.tms.servies.dao.BaseDAO
 * @see edu.elpeanuto.tms.servies.dao.OrderDAO
 */
public class OrderDAOImpl implements OrderDAO {
    private final DBConnection dbConnection;

    public OrderDAOImpl() {
        dbConnection = PoolConnectionBuilder.getInstance();
    }

    public OrderDAOImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dbConnection.getConnection();
    }
    @Override
    public Optional<Order> get(Long id) throws DAOException {
        String getPattern = "SELECT * FROM booking WHERE id=?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(getPattern)
        ) {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Order(id, rs.getLong("userId"), rs.getLong("productId"), rs.getString("status"), rs.getString("date"),
                        rs.getString("name"), rs.getString("phoneNumber"), rs.getString("email"),
                        rs.getString("realName"), rs.getString("realSurName"), rs.getString("gender"),
                        rs.getString("dateOfBirth"), rs.getString("citizenship"), rs.getString("passportSerial"),
                        rs.getString("passportNumber"), rs.getString("passportValidDate"), rs.getInt("totalPrice")));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in get(Long id), params: %s" , id.toString()), e);
        }
    }

    @Override
    public boolean changeStatus(Long id, String status) throws DAOException {
        String changeStatusPattern = "UPDATE booking SET status = ? WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(changeStatusPattern)
        ) {
            stmt.setString(1, status);
            stmt.setLong(2, id);

            int rowCounter = stmt.executeUpdate();

            return rowCounter > 0;
        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in changeStatus(Long id, String status), params: id: %s, status: %s" , id.toString(), status), e);
        }
    }

    @Override
    public Optional<Integer> numOfRegisteredOrders(Long userId) throws DAOException {
        String numRegisteredOrders = "SELECT count(*) FROM booking WHERE userId=? AND status=?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(numRegisteredOrders)
        ) {
            stmt.setLong(1, userId);
            stmt.setString(2, OrderStatus.Registered.name());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in numOfRegisteredOrders(Long userId), params: %s" , userId.toString()), e);
        }
    }

    @Override
    public List<Order> getPaginationByUserId(Integer start, Integer numOfStrings, Long userId) throws DAOException {
        String getPattern = "SELECT * FROM booking WHERE userId = ? LIMIT ?, ?;";

        List<Order> orderList = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(getPattern)
        ) {
            stmt.setLong(1, userId);
            stmt.setInt(2, start);
            stmt.setInt(3, numOfStrings);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orderList.add(setOrder(rs));
            }

            return orderList;

        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in getPaginationByUserId(Integer start, Integer numOfStrings, Long userId), params: start: %s, numOfStrings: %s, userId: %s"
                    , start.toString(), numOfStrings, userId), e);
        }
    }

    @Override
    public List<Order> getPaginationNotProceeded(Integer start, Integer numOfStrings) throws DAOException {
        String getPattern = "SELECT * FROM booking WHERE status = ? or status = ? LIMIT ?, ?;";

        List<Order> orderList = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(getPattern)
        ) {
            stmt.setString(1, OrderStatus.Registered.name());
            stmt.setString(2, OrderStatus.Paid.name());
            stmt.setInt(3, start);
            stmt.setInt(4, numOfStrings);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orderList.add(setOrder(rs));
            }

            return orderList;

        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in getPaginationNotProceeded(Integer start, Integer numOfStrings), params: start: %s, numOfStrings: %s"
                    , start.toString(), numOfStrings), e);
        }
    }

    @Override
    public Optional<Integer> getNumberOfNotesNotProceeded() throws DAOException {
        String numRegisteredOrders = "SELECT COUNT(*) FROM booking WHERE status = ? or status = ?;";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(numRegisteredOrders)
        ) {
            stmt.setString(1, OrderStatus.Registered.name());
            stmt.setString(2, OrderStatus.Paid.name());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new DAOException("SQLException in getNumberOfNotesNotProceeded()", e);
        }
    }

    @Override
    public Optional<Integer> getNumberOfNotesByUserId(Long userId) throws DAOException {
        String numRegisteredOrders = "SELECT count(*) FROM booking WHERE userId=?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(numRegisteredOrders)
        ) {
            stmt.setLong(1, userId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in getNumberOfNotesByUserId(Long userId), params: %s" , userId.toString()), e);
        }
    }

    @Override
    public List<Order> getAll() throws DAOException {
        String getALlPattern = "SELECT * FROM booking";

        List<Order> orderList = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(getALlPattern)
        ) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orderList.add(setOrder(rs));
            }

            return orderList;

        } catch (SQLException e) {
            throw new DAOException("SQLException in getAll()", e);
        }
    }

    @Override
    public boolean save(Order order) throws DAOException {
        String savePattern = "INSERT INTO booking(userID, productId, status , date, name, phoneNumber, email, " +
                "realName, realSurName, gender,dateOfBirth,citizenship,passportSerial,passportNumber,passportValidDate, totalPrice) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(savePattern, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setLong(1, order.getUserId());
            stmt.setLong(2, order.getProductId());
            stmt.setString(3, order.getStatus());
            stmt.setString(4, order.getDate());
            stmt.setString(5, order.getName());
            stmt.setString(6, order.getPhoneNumber());
            stmt.setString(7, order.getEmail());
            stmt.setString(8, order.getRealName());
            stmt.setString(9, order.getRealSurName());
            stmt.setString(10, order.getGender());
            stmt.setString(11, order.getDateOfBirth());
            stmt.setString(12, order.getCitizenship());
            stmt.setString(13, order.getPassportSerial());
            stmt.setString(14, order.getPassportNumber());
            stmt.setString(15, order.getPassportValidDate());
            stmt.setInt(16, order.getTotalPrice());

            int rowCounter = stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getLong(1));
                } else {
                    logger.error("Fatal error in save(Order order), no ID obtained.");
                    throw new RuntimeException();
                }
            }

            return rowCounter > 0;

        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in save(Order order), params: %s" , order.toString()), e);
        }
    }

    @Override
    public boolean update(Order order) {
        return false;
    }

    private Order setOrder(ResultSet rs) throws SQLException {
        return new Order(rs.getLong("id"), rs.getLong("userId"),
                rs.getLong("productId"),
                rs.getString("status"),
                rs.getString("date"),
                rs.getString("name"),
                rs.getString("phoneNumber"),
                rs.getString("email"),
                rs.getString("realName"),
                rs.getString("realSurName"),
                rs.getString("gender"),
                rs.getString("dateOfBirth"),
                rs.getString("citizenship"),
                rs.getString("passportSerial"),
                rs.getString("passportNumber"),
                rs.getString("passportValidDate"),
                rs.getInt("totalPrice")
        );
    }
}
