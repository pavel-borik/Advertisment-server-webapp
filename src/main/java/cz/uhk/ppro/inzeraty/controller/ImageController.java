package cz.uhk.ppro.inzeraty.controller;

import cz.uhk.ppro.inzeraty.model.Advert;
import cz.uhk.ppro.inzeraty.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/advertImage")
public class ImageController {

    private final AdvertService advertService;

    @Autowired
    public ImageController(AdvertService advertService) {
        this.advertService = advertService;

    }

    @RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
    public void showImage(@RequestParam("advertId") int advertId, HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {

        Optional<Advert> a = advertService.findById(advertId);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        if(a.isPresent()) response.getOutputStream().write(a.get().getImage());

        response.getOutputStream().close();
    }
}