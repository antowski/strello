package strello.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import strello.model.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class JdbcTaskDao implements TaskDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Task> getTasks() {
        String SQL_SELECT_ALL_TASKS = "SELECT * FROM tasks";
        return jdbcTemplate.query(SQL_SELECT_ALL_TASKS, new JdbcTaskRowMapper());
    }

    @Override
    public List<String> getUniqueAssignees() {
        String SQL_SELECT_ALL_TASKS = "SELECT DISTINCT assignee FROM tasks order by assignee";
        return jdbcTemplate.query(SQL_SELECT_ALL_TASKS, (rs, rowNum) -> rs.getString("assignee"));
    }

    @Override
    public List<Task> getFilteredTasks(TaskFilter filter) {
        List<Task> tasks = Stream
                .of(new Task(
                        1,
                        LocalDate.of(2015 , 6, 1),
                        LocalDate.of(2015, 6, 1), "" +
                        "Петя", "" +
                        ""))
                .collect(Collectors.toList());
        return tasks;
    }
}
