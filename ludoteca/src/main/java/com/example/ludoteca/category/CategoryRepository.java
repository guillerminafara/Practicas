package com.example.ludoteca.category;

import com.example.ludoteca.category.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
