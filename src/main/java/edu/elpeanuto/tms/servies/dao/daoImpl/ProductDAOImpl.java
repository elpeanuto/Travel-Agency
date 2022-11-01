package edu.elpeanuto.tms.servies.dao.daoImpl;

import edu.elpeanuto.tms.model.Product;
import edu.elpeanuto.tms.servies.dao.ProductDAO;
import edu.elpeanuto.tms.servies.dao.db.DBConnection;
import edu.elpeanuto.tms.servies.dao.db.PoolConnectionBuilder;
import edu.elpeanuto.tms.servies.dto.ProductFilterDTO;
import edu.elpeanuto.tms.servies.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class that communicate with database (product table)
 *
 * @see edu.elpeanuto.tms.servies.dao.BaseDAO
 * @see edu.elpeanuto.tms.servies.dao.ProductDAO
 */
public class ProductDAOImpl implements ProductDAO {
    private final DBConnection dbConnection;

    public ProductDAOImpl() {
        dbConnection = PoolConnectionBuilder.getInstance();
    }

    public ProductDAOImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dbConnection.getConnection();
    }

    @Override
    public Optional<Product> get(Long id) throws DAOException {
        String getPattern = "SELECT * FROM product WHERE id=?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(getPattern)
        ) {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(setProduct(rs));
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in get(Long id), params: %s", id.toString()), e);
        }
    }

    @Override
    public List<Product> getAll() throws DAOException {
        String getALlPattern = "SELECT * FROM product";

        List<Product> productList = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(getALlPattern)
        ) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                productList.add(setProduct(rs));
            }

            return productList;
        } catch (SQLException e) {
            throw new DAOException("SQLException in getAll");
        }
    }

    @Override
    public boolean save(Product product) throws DAOException {
        String savePattern = "INSERT INTO product(name, description, category, type, price, active, hotelName," +
                " arrivalDate, departureDate, arrivalPlace, departurePlace, country, city, foodInPrice, flightInPrice, amountOfDays," +
                " numberOfTourists, hotelType) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(savePattern)
        ) {
            stmtSetParams(product, stmt);
            stmt.setString(18, product.getHotelType());

            int rowCounter = stmt.executeUpdate();

            return rowCounter > 0;
        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in save(Product product), params: %s", product.toString()), e);
        }
    }


    @Override
    public boolean update(Product product) throws DAOException {
        String updatePattern = "UPDATE product SET name=?, description=?, category=?, type=?, price=?," +
                " active=?, hotelName=?, arrivalDate=?, departureDate=?, arrivalPlace=?, departurePlace=?," +
                "country=?, city=?, foodInPrice=?, flightInPrice=?, amountOfDays=?, numberOfTourists=? WHERE id=?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(updatePattern)
        ) {
            stmtSetParams(product, stmt);
            stmt.setLong(18, product.getId());

            int rowCounter = stmt.executeUpdate();

            return rowCounter > 0;
        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in update(Product product), params: %s", product.toString()), e);
        }
    }

    @Override
    public List<Product> search(ProductFilterDTO filter, Integer start, Integer numOfString, String type) throws DAOException {
        String searchPattern = configureSearchQuery(filter);

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(searchPattern)
        ) {
            int parameterPosition = 1;

            stmt.setInt(parameterPosition++, filter.getMinPrice());
            stmt.setInt(parameterPosition++, filter.getMaxPrice());
            stmt.setString(parameterPosition++, type);

            if (filter.getCategory() != null && !filter.getCategory().equals(ProductFilterDTO.CATEGORY.All.name()))
                stmt.setString(parameterPosition++, filter.getCategory());

            if (filter.getHotelType() != null && !filter.getHotelType().equals(ProductFilterDTO.HOTEL_TYPE.All.name()))
                stmt.setString(parameterPosition++, filter.getHotelType());

            if (filter.getNumberOfTourists() != null)
                stmt.setInt(parameterPosition++, filter.getNumberOfTourists());

            if (filter.getSearchPattern() != null) {
                stmt.setString(parameterPosition++, "%" + filter.getSearchPattern() + "%");
            }

            stmt.setInt(parameterPosition++, start);
            stmt.setInt(parameterPosition, numOfString);

            ResultSet rs = stmt.executeQuery();

            return fillSearchList(rs);
        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in search(ProductFilterDTO filter), params: %s \n Query: %s"
                    , filter, searchPattern), e);
        }
    }

    @Override
    public List<Product> getPagination(Integer start, Integer numOfStrings) throws DAOException {
        String getALlPattern = "SELECT * FROM product LIMIT ?, ?";

        List<Product> productList = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(getALlPattern)
        ) {
            stmt.setInt(1, start);
            stmt.setInt(2, numOfStrings);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                productList.add(setProduct(rs));
            }

            return productList;
        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in getPagination(Integer start, Integer numOfStrings), params: start: %s, numOfStrings: %s"
                    , start.toString(), numOfStrings), e);
        }
    }

    @Override
    public Optional<Integer> getNumberOfNotes() throws DAOException {
        String numRegisteredOrders = "SELECT COUNT(*) FROM product";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(numRegisteredOrders)
        ) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new DAOException("SQLException in getNumberOfNotes()", e);
        }
    }

    @Override
    public boolean promote(Long id, String type) throws DAOException {
        String promotePattern = "UPDATE product SET type = ? WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(promotePattern)
        ) {
            stmt.setString(1, type);
            stmt.setLong(2, id);

            int rowCounter = stmt.executeUpdate();

            return rowCounter > 0;
        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in promote(Long id, String type), params: id: %s type: %s", id.toString(), type), e);
        }
    }

    @Override
    public Optional<Integer> minPrice() throws DAOException {
        String minPricePattern = "SELECT MIN(price) FROM product";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(minPricePattern)
        ) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new DAOException("SQLException in minPrice()", e);
        }
    }

    @Override
    public Optional<Integer> maxPrice() throws DAOException {
        String maxPricePattern = "SELECT MAX(price) FROM product";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(maxPricePattern)
        ) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new DAOException("SQLException in maxPrice()", e);
        }
    }

    @Override
    public Optional<Integer> getNumberOfNotes(ProductFilterDTO filter) throws DAOException {
        String productGetByPattern = configureGetNumOfNotesQuery(filter);

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(productGetByPattern)
        ) {
            int parameterPosition = 1;

            stmt.setInt(parameterPosition++, filter.getMinPrice());
            stmt.setInt(parameterPosition++, filter.getMaxPrice());

            if (filter.getType() != null)
                stmt.setString(parameterPosition++, filter.getType());

            if (filter.getCategory() != null && !filter.getCategory().equals(ProductFilterDTO.CATEGORY.All.name()))
                stmt.setString(parameterPosition++, filter.getCategory());

            if (filter.getHotelType() != null && !filter.getHotelType().equals(ProductFilterDTO.HOTEL_TYPE.All.name()))
                stmt.setString(parameterPosition++, filter.getHotelType());

            if (filter.getNumberOfTourists() != null)
                stmt.setInt(parameterPosition++, filter.getNumberOfTourists());

            if (filter.getSearchPattern() != null) {
                stmt.setString(parameterPosition, "%" + filter.getSearchPattern() + "%");
            }
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new DAOException(String.format("SQLException in getNumberOfNotesFilter(ProductFilterDTO filter), params: filter: %s", filter), e);
        }
    }

    private String configureGetNumOfNotesQuery(ProductFilterDTO filter){
        StringBuilder productGetByPattern = new StringBuilder();

        productGetByPattern.append("SELECT count(*) from product WHERE active = 'yes' and price >= ? and price <= ?");

        if (filter.getType() != null)
            productGetByPattern.append(" and type = ?");

        if (filter.getCategory() != null && !filter.getCategory().equals(ProductFilterDTO.CATEGORY.All.name())){
            productGetByPattern.append(" and category = ?");
        }

        if (filter.getHotelType() != null && !filter.getHotelType().equals(ProductFilterDTO.HOTEL_TYPE.All.name()))
            productGetByPattern.append(" and hotelType = ?");

        if (filter.getNumberOfTourists() != null)
            productGetByPattern.append(" and numberOfTourists = ?");


        if (filter.getSearchPattern() != null) {
            productGetByPattern.append(" and city like ?");
        }

        productGetByPattern.append(";");

        return productGetByPattern.toString();
    }

    private Product setProduct(ResultSet rs) throws SQLException {
        return new Product(rs.getLong("id"), rs.getString("name"), rs.getString("description"),
                rs.getString("category"), rs.getString("type"), rs.getInt("price"), rs.getString("active"),
                rs.getString("hotelName"), rs.getString("hotelType"), rs.getString("arrivalDate"), rs.getString("departureDate"),
                rs.getString("arrivalPlace"), rs.getString("departurePlace"), rs.getString("country"),
                rs.getString("city"), rs.getString("foodInPrice"), rs.getString("flightInPrice"),
                rs.getInt("amountOfDays"), rs.getInt("numberOfTourists"));
    }


    private void stmtSetParams(Product product, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, product.getName());
        stmt.setString(2, product.getDescription());
        stmt.setString(3, product.getCategory());
        stmt.setString(4, product.getType());
        stmt.setInt(5, product.getPrice());
        stmt.setString(6, product.getActive());
        stmt.setString(7, product.getHotelName());
        stmt.setString(8, product.getArrivalDate());
        stmt.setString(9, product.getDepartureDate());
        stmt.setString(10, product.getArrivalPlace());
        stmt.setString(11, product.getDeparturePlace());
        stmt.setString(12, product.getCountry());
        stmt.setString(13, product.getCity());
        stmt.setString(14, product.getFoodInPrice());
        stmt.setString(15, product.getFlightInPrice());
        stmt.setInt(16, product.getAmountOfDays());
        stmt.setInt(17, product.getNumberOfTourists());
    }

    private String configureSearchQuery(ProductFilterDTO filter) {
        StringBuilder productGetByPattern = new StringBuilder();

        productGetByPattern.append("SELECT * from product WHERE active = 'yes' and price >= ? and price <= ? and type = ?");

        if (filter.getCategory() != null && !filter.getCategory().equals(ProductFilterDTO.HOTEL_TYPE.All.name()))
            productGetByPattern.append(" and category = ?");

        if (filter.getHotelType() != null && !filter.getHotelType().equals(ProductFilterDTO.HOTEL_TYPE.All.name()))
            productGetByPattern.append(" and hotelType = ?");

        if (filter.getNumberOfTourists() != null)
            productGetByPattern.append(" and numberOfTourists = ?");


        if (filter.getSearchPattern() != null) {
            productGetByPattern.append(" and city like ?");
        }

        productGetByPattern.append(" LIMIT ?, ?;");

        return productGetByPattern.toString();
    }

    private List<Product> fillSearchList(ResultSet rs) throws SQLException {
        List<Product> productList = new ArrayList<>();

        while (rs.next()) {
            productList.add(setProduct(rs));
        }

        return productList;
    }
}
