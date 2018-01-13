package cz.uhk.ppro.inzeraty.controller;

import cz.uhk.ppro.inzeraty.model.Advert;
import cz.uhk.ppro.inzeraty.model.Category;
import cz.uhk.ppro.inzeraty.model.Comment;
import cz.uhk.ppro.inzeraty.model.User;
import cz.uhk.ppro.inzeraty.service.AdvertService;
import cz.uhk.ppro.inzeraty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class AdvertController {
    private final AdvertService advertService;
    private final UserService userService;

    @Autowired
    public AdvertController(AdvertService advertService, UserService userService) {
        this.advertService = advertService;
        this.userService = userService;
    }

    @RequestMapping(value ="/adverts/{advertId}", method = RequestMethod.GET)
    public ModelAndView showAdvert(@PathVariable("advertId") int advertId, @ModelAttribute("addedComment") Comment comment) {
        ModelAndView mav = new ModelAndView("advertDetail");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> loggedUser = userService.findByUsername(authentication.getName());
        if(loggedUser.isPresent()) mav.addObject("userId", loggedUser.get().getId());

        Optional<Advert> advert = advertService.findById(advertId);
        mav.addObject("comments", advert.get().getComments());
        if(advert.isPresent()) mav.addObject("advert", advert.get());

        return mav;
    }

    @RequestMapping(value ="/adverts/{advertId}", method = RequestMethod.POST)
    public String addRating(@PathVariable("advertId") int advertId, @ModelAttribute("addedComment") Comment comment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> author = userService.findByUsername(authentication.getName());
        if(author.isPresent()) advertService.saveComment(comment, author.get(), advertId);
        return "redirect:/adverts/{advertId}";
    }

    @RequestMapping(value = "/adverts/new", method = RequestMethod.GET)
    public ModelAndView showNewAdvertForm(@ModelAttribute("advert") Advert advert, ModelMap modelMap) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("advert");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> loggedUser = userService.findByUsername(authentication.getName());
        if(loggedUser.isPresent()) modelMap.addAttribute("userId", loggedUser.get().getId());

        List<Category> categoryList;
        categoryList = advertService.findAllCategories();
        modelMap.put("categories", categoryList);
        return mav;
    }

    @RequestMapping(value = "/adverts/new", method = RequestMethod.POST)
    public String createNewAdvert(@ModelAttribute("advert") Advert advert) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> loggedUser = userService.findByUsername(authentication.getName());
        if(loggedUser.isPresent()) advertService.saveAdvert(advert, loggedUser.get());
        return "redirect:advertSuccess";
    }

    @RequestMapping(value = "/adverts/{advertId}/edit", method = RequestMethod.GET)
    public String showEditAdvertForm(@PathVariable("advertId") int advertId, Model model) {
        Optional<Advert> a = this.advertService.findById(advertId);
        if(a.isPresent()) model.addAttribute("advert", a);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> loggedUser = userService.findByUsername(authentication.getName());
        if(loggedUser.isPresent()) model.addAttribute("userId", loggedUser.get().getId());

        List<Category> categoryList;
        categoryList = advertService.findAllCategories();
        model.addAttribute("categories", categoryList);

        return "advert";
    }

    @RequestMapping(value = "/adverts/{advertId}/edit", method = RequestMethod.POST)
    public String processEditAdvert(@Valid Advert advert, BindingResult result, @PathVariable("advertId") int advertId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> author = userService.findByUsername(authentication.getName());

        if(result.hasErrors()) {
            return "redirect:/adverts/{advertId}/edit?error=true";
        }

        advert.setId(advertId);
        if(author.isPresent()) this.advertService.saveAdvert(advert, author.get());
        return "redirect:/adverts/{advertId}";
    }

    @RequestMapping(value = "/adverts/advertSuccess")
    public String showAdvertSuccess() {
        return "advertSuccess";
    }

}

