package dao;

import edu.elpeanuto.tms.model.Product;
import edu.elpeanuto.tms.model.enums.Bool;
import edu.elpeanuto.tms.model.enums.HotelType;
import edu.elpeanuto.tms.model.enums.ProductCategory;
import edu.elpeanuto.tms.model.enums.ProductType;
import edu.elpeanuto.tms.servies.dao.ProductDAO;
import edu.elpeanuto.tms.servies.dao.daoImpl.ProductDAOImpl;
import edu.elpeanuto.tms.servies.dao.db.DBConnection;
import edu.elpeanuto.tms.servies.dao.db.TestConnection;
import edu.elpeanuto.tms.servies.dto.ProductFilterDTO;
import edu.elpeanuto.tms.servies.exception.DAOException;
import edu.elpeanuto.tms.servies.exception.NoEntityException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductDAOImplTest {
    private static ProductDAO productDAO;

    private static DBConnection dbConnection;

    @BeforeAll
    public static void startUp() {
        dbConnection = new TestConnection();
        productDAO = new ProductDAOImpl(dbConnection);
    }

    @BeforeEach
    public void setUp() throws DAOException {
        try (Connection con = dbConnection.getConnection();
             Statement stmt = con.createStatement()
        ) {
            stmt.executeUpdate("DROP TABLE IF EXISTS products");
            stmt.executeUpdate("CREATE TABLE products" +
                    "(" +
                    "    id                 INT PRIMARY KEY AUTO_INCREMENT," +
                    "    name               VARCHAR(20)           NOT NULL," +
                    "    description        VARCHAR(300)          NOT NULL," +
                    "    category           VARCHAR(20)           NOT NULL," +
                    "    type               VARCHAR(20)           NOT NULL," +
                    "    price              INT                   NOT NULL," +
                    "    active             VARCHAR(5)            NOT NULL," +
                    "    hotel_name         VARCHAR(50)           NOT NULL," +
                    "    hotel_type         VARCHAR(20)           NOT NULL," +
                    "    arrival_date       DATE                  NOT NULL," +
                    "    departure_date     DATE                  NOT NULL," +
                    "    arrival_place      VARCHAR(50)           NOT NULL," +
                    "    departure_place    VARCHAR(50)           NOT NULL," +
                    "    country            VARCHAR(20)           NOT NULL," +
                    "    city               VARCHAR(20)           NOT NULL," +
                    "    food_in_price      VARCHAR(5)            NOT NULL," +
                    "    flight_in_price    VARCHAR(5)            NOT NULL," +
                    "    amount_of_days     INTEGER               NOT NULL," +
                    "    number_of_tourists INTEGER               NOT NULL" +
                    ");");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Test
    public void test1() throws DAOException {
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 100, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 100, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 100, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));

        assertEquals(3, productDAO.getAll().size());
    }

    @Test
    public void test2() throws DAOException, NoEntityException {
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 100, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name2", "description", ProductCategory.Rest, ProductType.Ordinary, 100, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));

        assertEquals("name", productDAO.get(1L).orElseThrow(NoEntityException::new).getName());
        assertEquals("name2", productDAO.get(2L).orElseThrow(NoEntityException::new).getName());
    }

    @Test
    public void test3() throws DAOException, NoEntityException {
        productDAO.save(new Product(null, "name2", "description", ProductCategory.Rest, ProductType.Ordinary, 100, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 100, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));

        assertEquals("name2", productDAO.get(1L).orElseThrow(NoEntityException::new).getName());
    }

    @Test
    public void test4() throws DAOException, NoEntityException {
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 100, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));

        assertEquals("name", productDAO.get(1L).orElseThrow(NoEntityException::new).getName());
    }

    @Test
    public void test5() throws DAOException, NoEntityException {
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 200, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 400, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 800, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));

        assertEquals(200, productDAO.minPrice().orElseThrow(NoEntityException::new));
        assertEquals(800, productDAO.maxPrice().orElseThrow(NoEntityException::new));
    }

    @Test
    public void test6() throws DAOException, NoEntityException {
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 200, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 400, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 800, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));

        assertEquals(3, productDAO.getNumberOfNotes().orElseThrow(NoEntityException::new));
    }

    @Test
    public void test7() throws DAOException {
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 200, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 400, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 800, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 400, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 800, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));

        assertEquals(3, productDAO.search(new ProductFilterDTO(ProductFilterDTO.CATEGORY.All, ProductFilterDTO.HOTEL_TYPE.All, null, 1, 200, 800), 0, 3, ProductType.Ordinary).size());
        assertEquals(2, productDAO.search(new ProductFilterDTO(ProductFilterDTO.CATEGORY.All, ProductFilterDTO.HOTEL_TYPE.All, null, 1, 200, 800), 3, 3, ProductType.Ordinary).size());
    }

    @Test
    public void test8() throws DAOException {
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 200, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 400, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 800, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 400, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 800, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));

        assertEquals(3, productDAO.getPagination(0, 3).size());
        assertEquals(2, productDAO.getPagination(3, 3).size());
    }

    @Test
    public void test9() throws DAOException, NoEntityException {
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 800, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));

        productDAO.promote(1L, ProductType.Hot);
        assertEquals(ProductType.Hot, productDAO.get(1L).orElseThrow(NoEntityException::new).getType());

    }

    @Test
    public void test10() throws DAOException, NoEntityException {
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 200, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 400, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 800, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 400, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));
        productDAO.save(new Product(null, "name", "description", ProductCategory.Rest, ProductType.Ordinary, 800, Bool.Yes, "hotelName", HotelType.Hostel, "2004-5-3",
                "2003-6-4", "arrivalPlace", "departurePlace", "country", "city", Bool.Yes, Bool.Yes, 1, 1));


        assertEquals(3, productDAO.getNumberOfNotes(new ProductFilterDTO(ProductFilterDTO.CATEGORY.All, ProductFilterDTO.HOTEL_TYPE.All, null, 1, 200, 400)).orElseThrow(NoEntityException::new));

    }
}
