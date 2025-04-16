package com.example.ludoteca.category;

import com.example.ludoteca.category.model.Category;
import com.example.ludoteca.category.model.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return (List<Category>) this.categoryRepository.findAll();
    }

    @Override
    public void save(Long id, CategoryDto dto) {
        Category category;
        if (id == null) {
            category = new Category();
        } else {
            category = this.categoryRepository.findById(id).orElse(null);
        }
        category.setName(dto.getName());
        this.categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) throws Exception {
        if (this.categoryRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not exist");

        }
        this.categoryRepository.deleteById(id);
    }
}
