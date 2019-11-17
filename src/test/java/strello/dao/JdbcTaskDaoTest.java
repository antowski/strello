package strello.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import strello.model.Task;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

public class JdbcTaskDaoTest {

    private JdbcTaskDao dao;

    @Before
    public void setup() {

        // arrange
        DataSource db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("schema.sql")
                .build();

        String SQL_INSERT_STMT =
                "INSERT INTO tasks (start_date, end_date, assignee) VALUES " +
                        "('2000-01-01', '2005-01-01', 'Вася'), " +
                        "('2003-01-01', '2008-01-01', 'Петя'), " +
                        "('2002-01-01', '2006-01-01', 'Вася');";

        JdbcTemplate template = new JdbcTemplate(db);
        template.execute(SQL_INSERT_STMT);

        this.dao = new JdbcTaskDao();
        dao.setDataSource(db);

    }

    @Test
    public void testEmptyFilter() {
        assertEquals(dao.getAllTasks(), dao.getFilteredTasks(new TaskFilter()));
    }

    @Test
    public void testFilterByAssignee() {

        // arrange
        TaskFilter filter = new TaskFilter();
        filter.addCondition(TaskFilterField.ASSIGNEE, "Петя");

        // act
        List<Task> tasks = dao.getFilteredTasks(filter);

        // assert
        assertEquals(1, tasks.size());
        assertEquals("Петя", tasks.get(0).getAssignee());

    }

    @Test
    public void testFilterByDates() {

        // arrange
        TaskFilter filter = new TaskFilter();
        filter.addCondition(TaskFilterField.START_DATE, LocalDate.of(2000 , 1, 1));
        filter.addCondition(TaskFilterField.END_DATE, LocalDate.of(2006 , 1, 1));

        // act
        List<Task> tasks = dao.getFilteredTasks(filter);

        // assert
        assertEquals(2, tasks.size());
        assertEquals(LocalDate.of(2000 , 1, 1), tasks.get(0).getStartDate());
        assertEquals(LocalDate.of(2002 , 1, 1), tasks.get(1).getStartDate());

    }

    @Test
    public void testComplexFilter() {

        // arrange
        TaskFilter filter = new TaskFilter();
        filter.addCondition(TaskFilterField.ASSIGNEE, "Вася");
        filter.addCondition(TaskFilterField.START_DATE, LocalDate.of(2001 , 6, 1));
        filter.addCondition(TaskFilterField.END_DATE, LocalDate.of(2010 , 6, 1));

        // act
        List<Task> tasks = dao.getFilteredTasks(filter);

        // assert
        assertEquals(1, tasks.size());
        assertEquals("Вася", tasks.get(0).getAssignee());
        assertEquals(LocalDate.of(2002 , 1, 1), tasks.get(0).getStartDate());

    }

}