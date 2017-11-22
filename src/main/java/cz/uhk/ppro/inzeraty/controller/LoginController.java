package cz.uhk.ppro.inzeraty.controller;

import cz.uhk.ppro.inzeraty.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, @ModelAttribute("user") User user) {
        try {
            request.login(user.getUsername(), user.getPassword());
        } catch (ServletException e) {
            e.printStackTrace();
        }


        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(@ModelAttribute("user") User user) {
        return "login";
    }
}
