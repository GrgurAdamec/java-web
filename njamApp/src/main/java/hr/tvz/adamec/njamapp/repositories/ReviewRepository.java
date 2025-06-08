package hr.tvz.adamec.njamapp.repositories;

import hr.tvz.adamec.njamapp.model.Restaurant;
import hr.tvz.adamec.njamapp.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ReviewRepository implements IReviewRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public ReviewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("reviews")
                .usingGeneratedKeyColumns("id");
    }

    private static final RowMapper<Review> ROW_MAPPER = new RowMapper<>() {
        @Override
        public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Review(
                    rs.getLong("id"),
                    rs.getLong("restaurant_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getLong("grade"),
                    rs.getDate("date").toLocalDate()
            );
        }
    };


    @Override
    public List<Review> getALlReviewsByRestaurantId(Long id) {
        return jdbcTemplate.query("SELECT * FROM reviews WHERE restaurant_id = ?", ROW_MAPPER, id);
    }
}
