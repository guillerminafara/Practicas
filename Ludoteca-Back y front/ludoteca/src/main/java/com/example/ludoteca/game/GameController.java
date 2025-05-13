package com.example.ludoteca.game;

import com.example.ludoteca.game.model.Game;
import com.example.ludoteca.game.model.GameDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Game", description = "API of Game")
@RequestMapping(value = "/game")
@RestController
@CrossOrigin(origins = "*")

public class GameController {
    @Autowired
    GameService gameService;
    @Autowired
    ModelMapper mapper;

    @Operation(summary = "Find", description = "Method that return a filtered list of Games")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<GameDto> find(@RequestParam(value = "title", required = false) String title,
                              @RequestParam(value = "idCategory", required = false) Long idCategory) {
        List<Game> games = gameService.find(title, idCategory);
        return games.stream().map(e -> mapper.map(e, GameDto.class)).collect(Collectors.toList());
    }

    @Operation(summary = "Save or Update", description = "Method that saves or updates a Game")
    @RequestMapping(path = {"", "/{id}"}, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id,
                     @Valid @RequestBody GameDto dto) {
        gameService.save(id, dto);
    }
}