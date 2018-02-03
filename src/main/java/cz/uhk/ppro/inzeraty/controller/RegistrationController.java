package cz.uhk.ppro.inzeraty.controller;

import cz.uhk.ppro.inzeraty.dto.UserDto;
import cz.uhk.ppro.inzeraty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    RegistrationController(UserService userService) {this.userService = userService;}

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(@ModelAttribute("user") UserDto user) {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerNewUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result) {
        if(result.hasErrors() || !userDto.getPassword().equals(userDto.getPasswordRepeat()))
            return "redirect:registration?unsuccesful";

        if(userService.findByUsername(userDto.getUsername()).isPresent() == false){
            userService.createNewUser(userDto);
            return "redirect:registration/registrationSuccess";
        }

        return "redirect:registration?unsuccesful";
    }

    @RequestMapping(value = "/registration/registrationSuccess",  method = RequestMethod.GET)
    public String showRegistrationSuccess() {
        return "registrationSuccess";
    }
}
