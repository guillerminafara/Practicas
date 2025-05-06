package com.example.ludoteca.author;

import com.example.ludoteca.author.model.Author;
import com.example.ludoteca.author.model.AuthorDto;
import com.example.ludoteca.author.model.AuthorSearchDto;

import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Author> findAll() {

        return (List<Author>) this.authorRepository.findAll();
    }
    @Override
    public Author get(Long id) {
        return this.authorRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Long id, AuthorDto data) {
        Author author;
        if (id == null) {
            author = new Author();
        } else {
            author = this.get(id);
        }
        BeanUtils.copyProperties(data, author, "id");
        this.authorRepository.save(author);
    }

    @Override
    public void delete(Long id) throws Exception {
        if (this.get(id)== null) {
            throw new Exception("Not Exists");

        }
        this.authorRepository.deleteById(id);
    }
}
