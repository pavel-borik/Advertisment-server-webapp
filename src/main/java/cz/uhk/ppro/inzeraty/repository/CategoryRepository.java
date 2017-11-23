package cz.uhk.ppro.inzeraty.repository;

import cz.uhk.ppro.inzeraty.model.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();
    Category findByName(String name);
}
