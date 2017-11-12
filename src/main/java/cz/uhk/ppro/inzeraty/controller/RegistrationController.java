package cz.uhk.ppro.inzeraty.controller;

import cz.uhk.ppro.inzeraty.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user) {
        System.out.println(user.getEmail() + " "+ user.getPassword());
        return "redirect:registrationSuccess";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistration(@ModelAttribute("user") User user) {
        return "registration";
    }

    @RequestMapping(value = "/registrationSuccess")
    public String showRegistrationSuccess() {
        return "registrationSuccess";
    }
}
