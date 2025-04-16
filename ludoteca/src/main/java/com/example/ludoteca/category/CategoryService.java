package com.example.ludoteca.category;

import com.example.ludoteca.category.model.Category;
import com.example.ludoteca.category.model.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    void save(Long id, CategoryDto dto);

    void delete(Long id) throws Exception;
}
