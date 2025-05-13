package com.example.ludoteca.author;

import com.example.ludoteca.author.model.Author;
import com.example.ludoteca.author.model.AuthorDto;
import com.example.ludoteca.author.model.AuthorSearchDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuthorService {
    Page<Author> findPage(AuthorSearchDto dto);

    Author get(Long id);

    List<Author> findAll();

    void save(Long id, AuthorDto dto);

    void delete(Long id) throws Exception;
}
