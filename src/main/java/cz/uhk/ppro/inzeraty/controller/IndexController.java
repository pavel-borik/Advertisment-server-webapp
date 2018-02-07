package cz.uhk.ppro.inzeraty.controller;

import cz.uhk.ppro.inzeraty.model.Advert;
import cz.uhk.ppro.inzeraty.model.Category;
import cz.uhk.ppro.inzeraty.model.User;
import cz.uhk.ppro.inzeraty.security.AuthenticationProvider;
import cz.uhk.ppro.inzeraty.service.AdvertService;
import cz.uhk.ppro.inzeraty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {
    private final UserService userService;
    private final AdvertService advertService;
    private final AuthenticationProvider authentication;

    @Autowired
    public IndexController(UserService userService, AdvertService advertService, AuthenticationProvider authenticationProvider) {
        this.userService = userService;
        this.advertService = advertService;
        this.authentication = authenticationProvider;
    }

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String showIndex( @ModelAttribute("advert") Advert advert, ModelMap modelMap, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/index");
        if (authentication.getAuthentication().isAuthenticated()) {
            Optional<User> user = userService.findByUsername(authentication.getAuthentication().getName());
            if(user.isPresent()) {
                modelMap.put("loggedUserId", user.get().getId());
            }
        }

        PagedListHolder pagedListHolder = new PagedListHolder(advertService.findAdverts());
        int page = ServletRequestUtils.getIntParameter(request,"p",0);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(5);
        modelMap.put("pagedListHolder", pagedListHolder);

        List<Category> categoryList;
        categoryList = advertService.findAllCategories();
        modelMap.put("categories", categoryList );
        return "index";
    }

    @RequestMapping(value ="/adverts/categories/{categoryId}", method = RequestMethod.GET)
    public String showAdvertsInCategory(@PathVariable("categoryId") int categoryId, @ModelAttribute("advert") Advert advert, ModelMap modelMap, HttpServletRequest request){
        if (authentication.getAuthentication().isAuthenticated()) {
            Optional<User> user = userService.findByUsername(authentication.getAuthentication().getName());
            if(user.isPresent()) {
                modelMap.put("loggedUserId", user.get().getId());
            }
        }

        PagedListHolder pagedListHolder = new PagedListHolder(advertService.findAdvertsInCategory(categoryId));
        int page = ServletRequestUtils.getIntParameter(request,"p",0);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(5);
        modelMap.put("pagedListHolder", pagedListHolder);

        List<Category> categoryList;
        categoryList = advertService.findAllCategories();
        modelMap.put("categories", categoryList );
        return "index";
    }
}
