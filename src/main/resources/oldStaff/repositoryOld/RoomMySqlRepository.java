/*
package repositoryOld;

import com.hotel.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class RoomMySqlRepository implements RoomRepository {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public RoomMySqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Map<String, Object>> findAll() {

        String sql = "SELECT * FROM rooms";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public Optional<Object> findById(Long id) {
        String sql = "SELECT * FROM rooms WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, new RoomRowMapper()));

    }

    @Override
    public Room save(Room room) {
        //INSERT INTO `rooms` (`price`, `roomNumber`, `roomType`,  `numberOfBeds` ) VALUES ('1000', '12', 'type A',  '2' );
        String sql = "INSERT INTO `rooms` (`price`, `roomNumber`, `roomType`,  `numberOfBeds` ) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, room.getPrice(), room.getRoomNumber(), room.getRoomType(), room.getNumberOfBeds());
        System.out.println("saved");
        return room;
    }

    @Override
    public void delete(Room room) {
        String sql = "DELETE FROM rooms WHERE id = ?";
        jdbcTemplate.update(sql, room.getId());
    }
}
*/
