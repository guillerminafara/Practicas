package com.example.ludoteca.game;

import com.example.ludoteca.game.model.Game;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepository extends CrudRepository<Game,Long>, JpaSpecificationExecutor<Game> {
    @Override
    @EntityGraph(attributePaths = {"category", "author"})
    List<Game> findAll(Specification<Game> spec);
}
