package com.example.ludoteca.game;

import com.example.ludoteca.game.model.Game;
import com.example.ludoteca.game.model.GameDto;

import java.util.List;

public interface GameService {
    List<Game> find (String title, Long idCategory);
    void save(Long id, GameDto dto);
    Game getGame(Long id);
}
