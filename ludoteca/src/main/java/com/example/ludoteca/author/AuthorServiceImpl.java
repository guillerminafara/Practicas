package com.example.ludoteca.author;

import com.example.ludoteca.author.model.Author;
import com.example.ludoteca.author.model.AuthorDto;
import com.example.ludoteca.author.model.AuthorSearchDto;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Page<Author> findPage(AuthorSearchDto dto) {
        return this.authorRepository.findAll(dto.getPageable().getPageable());
    }

    @Override
    public void save(Long id, AuthorDto data) {
        Author author;
        if (id == null) {
            author = new Author();
        } else {
            author = this.authorRepository.findById(id).orElse(null);
        }
        BeanUtils.copyProperties(data, author, "id");
    }

    @Override
    public void delete(Long id) throws Exception {
        if (this.authorRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not Exists");

        }
        this.authorRepository.deleteById(id);
    }
}
