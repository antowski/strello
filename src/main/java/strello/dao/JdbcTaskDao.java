package strello.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import strello.model.Task;

import java.util.List;

@Repository
public class JdbcTaskDao implements TaskDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Task> getTasks() {
        String SQL_SELECT_ALL_TASKS = "SELECT * FROM TASKS";
        return jdbcTemplate.query(SQL_SELECT_ALL_TASKS, new JdbcTaskRowMapper());
    }
}
