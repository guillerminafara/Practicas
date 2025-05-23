package com.example.ludoteca.game.model;

import com.example.ludoteca.author.model.AuthorDto;
import com.example.ludoteca.category.model.CategoryDto;
import jakarta.validation.constraints.NotBlank;

public class GameDto {
    private Long id;
    @NotBlank(message= "No se admiten carácteres en blanco")
    private String title;
    private String age;
    private CategoryDto category;
    private AuthorDto author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }
}
