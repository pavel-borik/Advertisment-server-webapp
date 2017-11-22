package cz.uhk.ppro.inzeraty.controller;

import cz.uhk.ppro.inzeraty.model.Advert;
import cz.uhk.ppro.inzeraty.model.User;
import cz.uhk.ppro.inzeraty.repository.AdvertRepository;
import cz.uhk.ppro.inzeraty.repository.UserRepository;
import cz.uhk.ppro.inzeraty.repository.jpa.JpaAdvertRepositoryImpl;
import cz.uhk.ppro.inzeraty.repository.jpa.JpaUserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;

@Controller
public class AdvertController {

    @Autowired
    AdvertRepository advertRepo = new JpaAdvertRepositoryImpl();

    @Autowired
    UserRepository userRepo = new JpaUserRepositoryImpl();

    @RequestMapping(value = "/advert", method = RequestMethod.POST)
    public String create(@ModelAttribute("advert") Advert advert) {
        System.out.println(advert.getName() + " "+ advert.getDescription());
        advert.setTimestamp(new Timestamp(System.currentTimeMillis()));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        advert.setUser(userRepo.findByUsername(currentPrincipalName).get());
        advert.setTimestamp(new Timestamp(System.currentTimeMillis()));
        advertRepo.save(advert);
        return "redirect:advertSuccess";
    }

    @RequestMapping(value = "/advert", method = RequestMethod.GET)
    public String showAdvertForm(@ModelAttribute("advert") Advert advert) {
        return "advert";
    }

    @RequestMapping(value = "/advertSuccess")
    public String showAdvertSuccess() {
        return "advertSuccess";
    }
}

