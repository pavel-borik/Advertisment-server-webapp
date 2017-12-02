package cz.uhk.ppro.inzeraty.controller;

import cz.uhk.ppro.inzeraty.model.Advert;
import cz.uhk.ppro.inzeraty.model.Category;
import cz.uhk.ppro.inzeraty.model.User;
import cz.uhk.ppro.inzeraty.service.AdvertService;
import cz.uhk.ppro.inzeraty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdvertController {
    private final AdvertService advertService;
    private final UserService userService;

    @Autowired
    public AdvertController(AdvertService advertService, UserService userService) {
        this.advertService = advertService;
        this.userService = userService;
    }


    @RequestMapping(value = "/advert/new", method = RequestMethod.POST)
    public String create(@ModelAttribute("advert") Advert advert) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.findByUsername(currentPrincipalName).get();
        advertService.saveAdvert(advert, user);
        return "redirect:advertSuccess";
    }

    @RequestMapping(value = "/advert/new", method = RequestMethod.GET)
    public ModelAndView showAdvertForm(@ModelAttribute("advert") Advert advert, ModelMap modelMap) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("advert");
        List<Category> categoryList;
        categoryList = advertService.findAllCategories();
        modelMap.put("categories", categoryList);
        return mav;
    }

    @RequestMapping(value = "/advertSuccess")
    public String showAdvertSuccess() {
        return "advertSuccess";
    }
}

