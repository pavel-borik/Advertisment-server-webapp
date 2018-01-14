package cz.uhk.ppro.inzeraty.controller;

import cz.uhk.ppro.inzeraty.InzeratyApplication;
import cz.uhk.ppro.inzeraty.configuration.WebSecurityConfig;
import cz.uhk.ppro.inzeraty.model.User;
import cz.uhk.ppro.inzeraty.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.Registration;
import java.sql.Timestamp;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={InzeratyApplication.class})
@WebMvcTest(value = RegistrationController.class, secure = false)
public class RegistrationControllerTests {
    private static final int TEST_USER_ID = 1;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    private User user;

    @Before
    public void setup() {
        user = new User();
        user.setUsername("");
        user.setFirstname("John");
        user.setSurname("Doe");
        user.setPassword("1234");
        user.setEmail("test@test.cz");
        user.setPhone("737737737");
        user.setCreationTime(new Timestamp(System.currentTimeMillis()));

        given(this.userService.findById(TEST_USER_ID)).willReturn(java.util.Optional.ofNullable(user));
    }

    @Test
    public void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("registration"));
    }
}
