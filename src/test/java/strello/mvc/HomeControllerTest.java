package strello.mvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import strello.model.Task;
import strello.service.StrelloService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
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
    public void testModelReturned() throws Exception {

        // arrange

        List<Task> tasks = Arrays.asList(new Task(), new Task(), new Task());

        // service mock svc returns prepared List<Tasks>
        StrelloService svc = mock(StrelloService.class);
        when(svc.getAllTasks()).thenReturn(tasks);

        HomeController controller = new HomeController();
        controller.setService(svc);

        // act
        ModelMap model = new ModelMap();
        String viewName = controller.home(model);

        // assert
        assertEquals("home", viewName);
        assertNotNull("Homepage model should have 'tasks' property", model.get("tasks"));
        assertSame(tasks, model.get("tasks"));
        verify(svc).getAllTasks();

    }

}