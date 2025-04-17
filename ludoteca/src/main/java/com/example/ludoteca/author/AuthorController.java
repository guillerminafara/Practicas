package com.example.ludoteca.author;

import com.example.ludoteca.author.model.Author;
import com.example.ludoteca.author.model.AuthorDto;
import com.example.ludoteca.author.model.AuthorSearchDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Tag(name = "Author", description = "API of Author")
@RequestMapping(value = "/author")
@RestController
@CrossOrigin(origins = "*")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @Autowired
    ModelMapper mapper;

    @RequestMapping(path = "", method = RequestMethod.POST)
    @Operation(summary = "Find Page", description = "Method that return a page of Authors")
    public Page<AuthorDto> findPage(@RequestBody AuthorSearchDto dto) {
        Page<Author> page = this.authorService.findPage(dto);
        return new PageImpl<>(page.getContent().stream().map(e -> mapper.map(e, AuthorDto.class)).collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }

    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody AuthorDto dto) {
        this.authorService.save(id, dto);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @Operation(summary = "Delete", description = "Method that deletes a Author")
    public void delete(@PathVariable("id") Long id) throws Exception {
        this.authorService.delete(id);
    }
}