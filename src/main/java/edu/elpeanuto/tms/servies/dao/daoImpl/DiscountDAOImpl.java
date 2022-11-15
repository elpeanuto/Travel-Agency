package edu.elpeanuto.tms.servies.dao.daoImpl;

import edu.elpeanuto.tms.model.Discount;
import edu.elpeanuto.tms.servies.dao.DiscountDAO;
import edu.elpeanuto.tms.servies.dao.db.DBConnection;
import edu.elpeanuto.tms.servies.dao.db.PoolConnectionBuilder;
import edu.elpeanuto.tms.servies.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class that communicate with database (discount table)
 * @see edu.elpeanuto.tms.servies.dao.BaseDAO
 * @see edu.elpeanuto.tms.servies.dao.DiscountDAO
 */
public class DiscountDAOImpl implements DiscountDAO {
    private final DBConnection dbConnection;

    public DiscountDAOImpl() {
        dbConnection = PoolConnectionBuilder.getInstance();
    }

    public DiscountDAOImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dbConnection.getConnection();
    }

    @Override
    public Optional<Discount> get(Long id) throws DAOException {
        String getPattern = "SELECT * FROM discount WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(getPattern)
        ) {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Discount(id, rs.getInt("step"), rs.getInt("max")));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in get(Long id), params: %s" , id.toString()), e);
        }
    }

    @Override
    public List<Discount> getAll() throws DAOException {
        String getAllPattern = "SELECT * FROM discount";

        List<Discount> list = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(getAllPattern)
        ) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Discount(null, rs.getInt("step"), rs.getInt("max")));
            }

            return list;
        } catch (SQLException e) {
            throw new DAOException("SQLException in getAll");
        }
    }

    @Override
    public boolean save(Discount discount) throws DAOException {
        String savePattern = "INSERT INTO discount(step, max) values(?, ?)";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(savePattern)
        ) {
            stmt.setInt(1, discount.getStep());
            stmt.setInt(2, discount.getMax());

            int rowCounter = stmt.executeUpdate();

            return rowCounter > 0;
        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in save(Discount discount), params: %s" , discount.toString()), e);
        }
    }

    @Override
    public boolean update(Discount discount) throws DAOException {
        String DISCOUNT_UPDATE_PATTERN = "UPDATE discount SET step=?, max=? WHERE id=?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DISCOUNT_UPDATE_PATTERN)
        ) {
            stmt.setInt(1, discount.getStep());
            stmt.setInt(2, discount.getMax());
            stmt.setLong(3, discount.getId());

            int rowCounter = stmt.executeUpdate();

            return rowCounter > 0;
        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in update(Discount discount), params: %s" , discount.toString()), e);
        }
    }
}
