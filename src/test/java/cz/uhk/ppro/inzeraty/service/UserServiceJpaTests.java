package cz.uhk.ppro.inzeraty.service;

import cz.uhk.ppro.inzeraty.InzeratyApplication;
import cz.uhk.ppro.inzeraty.model.User;
import cz.uhk.ppro.inzeraty.model.UserDTO;
import cz.uhk.ppro.inzeraty.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={InzeratyApplication.class})
@SpringBootTest
public class UserServiceJpaTests {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void shouldInsertUsers() {
        Collection<User> users = this.userService.findUsersByLastName("Smith");
        int found = users.size();

        UserDTO userDto = new UserDTO();

        userDto.setUsername(UUID.randomUUID().toString());
        userDto.setFirstname("John");
        userDto.setSurname("Smith");
        userDto.setPassword("1234");
        userDto.setPasswordRepeat("1234");
        userDto.setEmail("test@test.cz");
        userDto.setPhoneNumber("737737737");
        this.userService.createNewUser(userDto);

        users = this.userService.findUsersByLastName("Smith");

        assertThat(users.size()).isEqualTo(found + 1);
    }

}
