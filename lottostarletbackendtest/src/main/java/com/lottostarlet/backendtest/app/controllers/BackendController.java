package com.lottostarlet.backendtest.app.controllers;

import com.lottostarlet.backendtest.app.domainlogic.BackendService;
import com.lottostarlet.backendtest.app.exceptions.GameDoesNotExistException;
import com.lottostarlet.backendtest.app.model.ErrorResponse;
import com.lottostarlet.backendtest.app.model.GameResult;
import com.lottostarlet.backendtest.app.model.Round;
import com.lottostarlet.backendtest.app.persistence.GameResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/result")
public class BackendController {

    private GameResultRepository gameRepo;
    private BackendService service;

    @Autowired
    public BackendController(BackendService service, GameResultRepository gameRepo) {
        this.service = service;
        this.gameRepo = gameRepo;
    }

    /**
     * Controller method that takes care of the game play and stores the results into db.
     * Listens to: POST /api/result
     *
     * @return {@link HttpHeaders} with path to the created game in the location header tag
     */
    @RequestMapping(method = RequestMethod.POST, value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders play() {
        List<Round> rounds = service.playGame();
        GameResult game = new GameResult();
        game.setRounds(rounds);
        gameRepo.save(game);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkTo(BackendController.class).slash(game.getId()).toUri());

        return headers;
    }

    /**
     * Controller method that returns all {@link GameResult}s
     * Listens to: GET /api/result
     *
     * @return all {@link GameResult}s
     */
    @RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<GameResult> getAllGameResults() {
        return gameRepo.findAll();
    }

    /**
     * Controller method that returns a requested {@link GameResult}
     * Listens to: GET /api/result/{id}
     *
     * @param id of the requested {@link GameResult}
     * @return the requested {@link GameResult}
     * @throws GameDoesNotExistException if the requested {@link GameResult} does not exist
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    GameResult getGame(@PathVariable String id) throws GameDoesNotExistException {
        if(gameRepo.findById(id)== null){
            throw new GameDoesNotExistException(id);
        }

        return gameRepo.findById(id);
    }


}
