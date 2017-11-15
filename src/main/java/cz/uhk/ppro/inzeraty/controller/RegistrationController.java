package cz.uhk.ppro.inzeraty.controller;

import cz.uhk.ppro.inzeraty.model.Role;
import cz.uhk.ppro.inzeraty.model.User;
import cz.uhk.ppro.inzeraty.repository.UserRepository;
import cz.uhk.ppro.inzeraty.repository.jpa.JpaUserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.sql.Timestamp;


@Controller
public class RegistrationController {

    @Autowired
    UserRepository userRepo = new JpaUserRepositoryImpl();


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user) {
        System.out.println(user.getEmail() + " "+ user.getPassword());
        user.setCreationTime(new Timestamp(System.currentTimeMillis()));
        System.out.println(user.getCreationTime());
        user.setRole(new Role());
        userRepo.save(user);
        return "redirect:registrationSuccess";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(@ModelAttribute("user") User user) {
        return "registration";
    }

    @RequestMapping(value = "/registrationSuccess")
    public String showRegistrationSuccess() {
        return "registrationSuccess";
    }
}
