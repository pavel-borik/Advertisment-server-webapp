package cz.uhk.ppro.inzeraty.util;

import cz.uhk.ppro.inzeraty.model.Category;
import cz.uhk.ppro.inzeraty.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryStringToCategoryConverter implements Converter<String, Category> {
    private AdvertService advertService;

    @Autowired
    public CategoryStringToCategoryConverter(AdvertService advertService) {
        this.advertService = advertService;
    }

    @Override
    public Category convert(String s) {
        return advertService.findCategoryByName(s);
    }
}
