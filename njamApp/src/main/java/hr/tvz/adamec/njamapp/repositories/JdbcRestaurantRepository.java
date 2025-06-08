package hr.tvz.adamec.njamapp.repositories;

import hr.tvz.adamec.njamapp.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@Primary
public class JdbcRestaurantRepository implements IRestaurantRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public JdbcRestaurantRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("restaurants")
                .usingGeneratedKeyColumns("id");
    }

    private static final RowMapper<Restaurant> ROW_MAPPER = new RowMapper<>() {
        @Override
        public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Restaurant(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getLong("telephone_number"),
                    rs.getString("email"),
                    rs.getInt("working_hours"),
                    rs.getBoolean("currently_open"),
                    rs.getInt("average_delivery_time"),
                    rs.getDouble("average_rating"),
                    rs.getFloat("max_number_of_orders"),
                    rs.getInt("michelin_star"),
                    rs.getString("description")
            );
        }
    };

    @Override
    public List<Restaurant> findAll() {
        return jdbcTemplate.query("SELECT * FROM restaurants", ROW_MAPPER);
    }

    @Override
    public Optional<Restaurant> findRestaurantById(Long id) {
        List<Restaurant> results = jdbcTemplate.query("SELECT * FROM restaurants WHERE id = ?", ROW_MAPPER, id);
        return results.stream().findFirst();
    }

    @Override
    public Optional<Restaurant> findRestaurantByName(String name) {
        List<Restaurant> results = jdbcTemplate.query("SELECT * FROM restaurants WHERE name = ?", ROW_MAPPER, name);
        return results.stream().findFirst();
    }

    @Override
    public Optional<Restaurant> saveRestaurant(Restaurant restaurant) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", restaurant.getName());
        parameters.put("address", restaurant.getAddress());
        parameters.put("telephone_number", restaurant.getTelephoneNumber());
        parameters.put("email", restaurant.getEmail());
        parameters.put("working_hours", restaurant.getWorkingHours());
        parameters.put("currently_open", restaurant.getCurrentlyOpen());
        parameters.put("average_delivery_time", restaurant.getAverageDeliveryTime());
        parameters.put("average_rating", restaurant.getAverageRating());
        parameters.put("max_number_of_orders", restaurant.getMaxNumberOfOrders());
        parameters.put("michelin_star", restaurant.getMichelinStar());
        parameters.put("description", restaurant.getDescription());

        try {
            Number generatedId = simpleJdbcInsert.executeAndReturnKey(parameters);
            restaurant.setId(generatedId.longValue());
            return Optional.of(restaurant);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Restaurant> updateRestaurant(Restaurant restaurant) {
        int updated = jdbcTemplate.update("""
                UPDATE restaurants SET 
                    name = ?, address = ?, telephone_number = ?, email = ?, 
                    working_hours = ?, currently_open = ?, average_delivery_time = ?, 
                    average_rating = ?, max_number_of_orders = ?, michelin_star = ?, 
                    description = ?
                WHERE id = ?
                """,
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getTelephoneNumber(),
                restaurant.getEmail(),
                restaurant.getWorkingHours(),
                restaurant.getCurrentlyOpen(),
                restaurant.getAverageDeliveryTime(),
                restaurant.getAverageRating(),
                restaurant.getMaxNumberOfOrders(),
                restaurant.getMichelinStar(),
                restaurant.getDescription(),
                restaurant.getId()
        );
        return updated > 0 ? Optional.of(restaurant) : Optional.empty();
    }

    @Override
    public void deleteRestaurant(Long id) {
        jdbcTemplate.update("DELETE FROM restaurants WHERE id = ?", id);
    }
}
