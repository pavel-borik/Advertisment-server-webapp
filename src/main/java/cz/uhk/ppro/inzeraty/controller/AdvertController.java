package cz.uhk.ppro.inzeraty.controller;

import cz.uhk.ppro.inzeraty.model.Advert;
import cz.uhk.ppro.inzeraty.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdvertController {

    @RequestMapping(value = "/advert", method = RequestMethod.POST)
    public String create(@ModelAttribute("advert") Advert advert) {
        System.out.println(advert.getName() + " "+ advert.getDescription());
        return "redirect:advertSuccess";
    }

    @RequestMapping(value = "/advert", method = RequestMethod.GET)
    public String showAdvert(@ModelAttribute("advert") Advert advert) {
        return "advert";
    }

    @RequestMapping(value = "/advertSuccess")
    public String showAdvertSuccess() {
        return "advertSuccess";
    }
}

