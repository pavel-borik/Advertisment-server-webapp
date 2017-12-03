package cz.uhk.ppro.inzeraty.controller;

import cz.uhk.ppro.inzeraty.model.Advert;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class WelcomeController {
    UserService userService;
    private final AdvertService advertService;

    @Autowired
    public WelcomeController(UserService userService, AdvertService advertService) {
        this.userService = userService;
        this.advertService = advertService;
    }

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public ModelAndView welcome(@ModelAttribute("advert") Advert advert, ModelMap modelMap) {
        ModelAndView mav = new ModelAndView("/index");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Optional<User> user = userService.findByUsername(authentication.getName());
            if(user.isPresent()) {
                modelMap.put("userId", user.get().getId());
            }
        }

        List<Advert> advertList;
        advertList = advertService.findAdverts();
        mav.addObject("adverts", advertList);
        return mav;
    }
}
