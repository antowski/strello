package strello.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import strello.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcTaskDao implements TaskDao {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Task> getTasks() {

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(
                1,
                LocalDate.of(2015 , 6, 1),
                LocalDate.of(2015,6,2),
                "Жора Спит",
                "Создать Strello"));
        tasks.add(new Task(
                2,
                LocalDate.of(2017 , 6, 1),
                LocalDate.of(2018,6,2),
                "Вася сторожит",
                "Жору"));

        return tasks;
    }
}
