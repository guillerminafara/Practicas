package com.example.ludoteca.game;

import com.example.ludoteca.author.AuthorService;
import com.example.ludoteca.category.CategoryService;
import com.example.ludoteca.common.criteria.SearchCriteria;
import com.example.ludoteca.game.model.Game;
import com.example.ludoteca.game.model.GameDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GameServiceImpl implements GameService {
    @Autowired
    GameRepository gameRepository;
    @Autowired
    AuthorService authorService;

    @Autowired
    CategoryService categoryService;

    @Override
    public List<Game> find(String title, Long idCategory) {
        GameSpecification titleSpec = new GameSpecification(
                new SearchCriteria("title", ":", title));
        GameSpecification categorySpec = new GameSpecification(
                new SearchCriteria("category.id", ":", idCategory));
        Specification<Game> spec = Specification.where(titleSpec).and(categorySpec);
        return this.gameRepository.findAll(spec);
    }

    @Override
    public void save(Long id, GameDto dto) {
        Game game;
        if (id == null) {
            game = new Game();
        } else {
            game = this.gameRepository.findById(id).orElse(null);
        }
        BeanUtils.copyProperties(dto, game, "id", "author", "category");
        game.setAuthor(authorService.get(dto.getAuthor().getId()));
        game.setCategory(categoryService.get(dto.getCategory().getId()));
        this.gameRepository.save(game);
    }

    @Override
    public Game getGame(Long id) {
        return this.gameRepository.findById(id).orElse(null);
    }
}
