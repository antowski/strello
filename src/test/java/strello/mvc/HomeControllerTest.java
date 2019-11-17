package strello.mvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import strello.config.SpringRootConfig;
import strello.config.SpringWebConfig;
import strello.dao.JdbcTaskDao;
import strello.model.Task;
import strello.service.StrelloService;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = SpringRootConfig.class),
        @ContextConfiguration(classes = SpringWebConfig.class),
})
public class HomeControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        //this.mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testHomePage() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));

    }

    @Test
    public void testModelWithTasksReturned() throws Exception {

        // arrange

        List<Task> tasks = Arrays.asList(new Task(), new Task(), new Task());

        // service mock svc returns prepared List<Tasks>
        StrelloService svc = mock(StrelloService.class);
        when(svc.getAllTasks()).thenReturn(tasks);

        HomeController controller = new HomeController();
        controller.setService(svc);

        // act
        ModelMap model = new ModelMap();
        String viewName = controller.getTasksByFilter(model, "");

        // assert
        assertEquals("home", viewName);
        assertNotNull("Homepage model should have 'tasks' property", model.get("tasks"));
        assertSame(tasks, model.get("tasks"));
        verify(svc).getAllTasks();

    }

    @Test
    public void testModelWithAssigneesReturned() throws Exception {

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
                        "('2000-01-01', '2000-01-01', 'Вася'), " +
                        "('2000-01-01', '2000-01-01', 'Петя'), " +
                        "('2000-01-01', '2000-01-01', 'Аня'), " +
                        "('2000-01-01', '2000-01-01', 'Вася');";

        JdbcTemplate template = new JdbcTemplate(db);
        template.execute(SQL_INSERT_STMT);

        JdbcTaskDao dao = new JdbcTaskDao();
        dao.setDataSource(db);

        HomeController controller = new HomeController();
        controller.setService(new StrelloService(dao));

        // act
        ModelMap model = new ModelMap();
        String viewName = controller.getTasksByFilter(model, "");

        // assert
        assertNotNull("Homepage model should have 'assignees' property", model.get("assignees"));
        List<String> assignees = Arrays.asList("Аня", "Вася", "Петя");
        assertEquals(assignees, model.get("assignees"));

    }

}
