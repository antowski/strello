package strello.mvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import strello.config.SpringRootConfig;
import strello.config.SpringWebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = SpringRootConfig.class),
        @ContextConfiguration(classes = SpringWebConfig.class),
})
public class CreateTaskControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new CreateTaskController()).build();
    }

    @Test
    public void testCreateNewTask() throws Exception {

        mockMvc.perform(get("/newTask"))
                .andExpect(status().isOk())
                .andExpect(view().name("taskEdit"));
    }
}