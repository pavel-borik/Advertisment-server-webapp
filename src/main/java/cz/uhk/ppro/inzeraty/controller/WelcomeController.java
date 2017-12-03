package cz.uhk.ppro.inzeraty.controller;

import cz.uhk.ppro.inzeraty.model.Advert;
import cz.uhk.ppro.inzeraty.service.AdvertService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WelcomeController {
    private final AdvertService advertService;

    public WelcomeController(AdvertService advertService) {
        this.advertService = advertService;
    }

    @RequestMapping("/")
    public String welcome() {
        return "index";
    }


}
