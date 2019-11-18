package strello.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import strello.model.Task;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcTaskDao implements TaskDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Task> getAllTasks() {
        String SQL_SELECT_STMT = "SELECT * FROM tasks ORDER BY start_date";
        return jdbcTemplate.query(SQL_SELECT_STMT, new JdbcTaskRowMapper());
    }

    @Override
    public List<String> getUniqueAssignees() {
        String SQL_SELECT_STMT = "SELECT DISTINCT assignee FROM tasks order by assignee";
        return jdbcTemplate.query(SQL_SELECT_STMT, (rs, rowNum) -> rs.getString("assignee"));
    }

    @Override
    public List<Task> getFilteredTasks(TaskFilter filter) {

        String SQL_SELECT_STMT = "SELECT * FROM tasks" + filter.getWhereClause() + " ORDER BY start_date";

        return jdbcTemplate.query(
                SQL_SELECT_STMT,
                filter.getQueryArgs(),
                filter.getQueryArgTypes(),
                new JdbcTaskRowMapper());

    }

    @Override
    public void addTask(Task task) {
        String SQL_INSERT_STMT = "INSERT INTO tasks (start_date, end_date, assignee, summary) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(SQL_INSERT_STMT, task.getStartDate(), task.getEndDate(), task.getAssignee(), task.getSummary());
    }
}
