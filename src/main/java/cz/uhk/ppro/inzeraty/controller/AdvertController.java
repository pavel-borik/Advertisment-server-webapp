package cz.uhk.ppro.inzeraty.controller;

import cz.uhk.ppro.inzeraty.dto.AdvertDto;
import cz.uhk.ppro.inzeraty.model.Advert;
import cz.uhk.ppro.inzeraty.model.Category;
import cz.uhk.ppro.inzeraty.model.Comment;
import cz.uhk.ppro.inzeraty.model.User;
import cz.uhk.ppro.inzeraty.security.AuthenticationProvider;
import cz.uhk.ppro.inzeraty.service.AdvertService;
import cz.uhk.ppro.inzeraty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final AuthenticationProvider authentication;

    private static final String ADVERTFORMVIEW = "saveOrEditAdvert";

    @Autowired
    public AdvertController(AdvertService advertService, UserService userService, AuthenticationProvider authentication) {
        this.advertService = advertService;
        this.userService = userService;
        this.authentication = authentication;
    }

    @RequestMapping(value ="/adverts/{advertId}", method = RequestMethod.GET)
    public ModelAndView showAdvert(@PathVariable("advertId") int advertId, @ModelAttribute("addedComment") Comment comment) {
        ModelAndView mav = new ModelAndView("advertDetail");

        Optional<User> loggedUser = userService.findByUsername(authentication.getAuthentication().getName());
        if(loggedUser.isPresent()) mav.addObject("loggedUserId", loggedUser.get().getId());

        Optional<Advert> advert = advertService.findById(advertId);

        if(advert.isPresent()) {
            mav.addObject("advert", advert.get());
            mav.addObject("images", advert.get().getImages());
            mav.addObject("comments", advert.get().getComments());
        }

        return mav;
    }

    @RequestMapping(value ="/adverts/{advertId}", method = RequestMethod.POST)
    public String addRating(@PathVariable("advertId") int advertId, @ModelAttribute("addedComment") @Valid Comment comment) {
        Optional<User> author = userService.findByUsername(authentication.getAuthentication().getName());
        if(author.isPresent()) advertService.saveComment(comment, author.get(), advertId);
        return "redirect:/adverts/{advertId}";
    }

    @RequestMapping(value = "/adverts/new", method = RequestMethod.GET)
    public ModelAndView showNewAdvertForm(AdvertDto advertDto, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(ADVERTFORMVIEW);

        Optional<User> loggedUser = userService.findByUsername(authentication.getAuthentication().getName());
        if(loggedUser.isPresent()) mav.addObject("loggedUserId", loggedUser.get().getId());

        List<Category> categoryList;
        categoryList = advertService.findAllCategories();
        mav.addObject("categories", categoryList);
        return mav;
    }

    @RequestMapping(value = "/adverts/new", method = RequestMethod.POST)
    public String createNewAdvert(@Valid AdvertDto advertDto, BindingResult result) throws IOException {
        if(result.hasErrors()) {
            return "redirect:/adverts/new?error=true";
        }
        Optional<User> loggedUser = userService.findByUsername(authentication.getAuthentication().getName());
        if(loggedUser.isPresent()) advertService.saveAdvert(advertDto, loggedUser.get());
        return "redirect:/adverts/new/success";
    }

    @RequestMapping(value = "/adverts/{advertId}/edit", method = RequestMethod.GET)
    public String showEditAdvertForm(@PathVariable("advertId") int advertId, @ModelAttribute("advertDto") Advert advert, Model model) {
        Optional<Advert> a = this.advertService.findById(advertId);
        //TODO map a to a DTO object
        if(a.isPresent()) model.addAttribute("advertDto", a);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> loggedUser = userService.findByUsername(authentication.getName());
        if(loggedUser.isPresent()) model.addAttribute("loggedUserId", loggedUser.get().getId());

        List<Category> categoryList;
        categoryList = advertService.findAllCategories();
        model.addAttribute("categories", categoryList);

        return ADVERTFORMVIEW;
    }

    @RequestMapping(value = "/adverts/{advertId}/edit", method = RequestMethod.POST)
    public String processEditAdvert(@Valid AdvertDto advertDto, BindingResult result, @PathVariable("advertId") int advertId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> author = userService.findByUsername(authentication.getName());

        if(result.hasErrors()) {
            return "redirect:/adverts/{advertId}/edit?error=true";
        }

        advertDto.setId(advertId);
        if(author.isPresent()) this.advertService.saveAdvert(advertDto, author.get());
        return "redirect:/adverts/{advertId}";
    }

    @PostMapping(value = "/adverts/{advertId}/delete")
    public String processDeleteAdvert(@PathVariable("advertId") int advertId) {
        advertService.removeAdvert(advertId);
        return "redirect:/";
    }

    @RequestMapping(value = "/adverts/new/success")
    public String showAdvertSuccess() {
        return "advertSuccess";
    }

}

