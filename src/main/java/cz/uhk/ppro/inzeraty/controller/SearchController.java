package cz.uhk.ppro.inzeraty.controller;

import cz.uhk.ppro.inzeraty.model.Category;
import cz.uhk.ppro.inzeraty.model.User;
import cz.uhk.ppro.inzeraty.repository.jpa.SearchRepository;
import cz.uhk.ppro.inzeraty.service.AdvertService;
import cz.uhk.ppro.inzeraty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class SearchController {

    private final SearchRepository searchRepository;
    private final AdvertService advertService;
    private final UserService userService;

    @Autowired
    public SearchController(SearchRepository searchRepository, AdvertService advertService, UserService userService) {
        this.searchRepository = searchRepository;
        this.advertService = advertService;
        this.userService = userService;
    }

    @RequestMapping("/search")
    public String search(String q, Model model, HttpServletRequest request) {
        List searchResults = null;

        try {
            searchResults = searchRepository.search(q);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PagedListHolder pagedListHolder = new PagedListHolder(searchResults);
        int page = ServletRequestUtils.getIntParameter(request,"p",0);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(5);
        model.addAttribute("pagedListHolder", pagedListHolder);

        List<Category> categoryList;
        categoryList = advertService.findAllCategories();
        model.addAttribute("categories", categoryList );

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Optional<User> user = userService.findByUsername(authentication.getName());
            if(user.isPresent()) {
                model.addAttribute("loggedUserId", user.get().getId());
            }
        }

        return "search";

    }
}
