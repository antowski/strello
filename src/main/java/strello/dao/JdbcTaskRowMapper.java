package strello.dao;

import org.springframework.jdbc.core.RowMapper;
import strello.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTaskRowMapper implements RowMapper {

    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Task(
                rs.getLong("id"),
                rs.getTimestamp("start_date").toLocalDateTime().toLocalDate(),
                rs.getTimestamp("end_date").toLocalDateTime().toLocalDate(),
                rs.getString("assignee"),
                rs.getString("summary")
        );
    }
}

