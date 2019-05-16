package cat.udl.eps.entsoftarch.webmemoryapi.handler;

import cat.udl.eps.entsoftarch.webmemoryapi.domain.Game;
import cat.udl.eps.entsoftarch.webmemoryapi.domain.Player;
import cat.udl.eps.entsoftarch.webmemoryapi.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class GameEventHandler {
    final Logger logger = LoggerFactory.getLogger(Game.class);

    @Autowired
    GameRepository gameRepository;

    @HandleBeforeCreate
    public void handleGameBeforeCreate(Game game) {
        logger.info("Before creating: {}", game.toString());
    }

    @HandleBeforeSave
    public void handleGameBeforeSave(Game game){
        logger.info("Before updating: {}", game.toString());
    }

    @HandleBeforeDelete
    public void handleGameBeforeDelete(Game game){
        if (game.isGameFinished()) {
            logger.info("Before deleting: {}", game.toString());
        }
        else {
            throw new AuthorizationServiceException("Can't delete game in progress.");
        }
    }

    @HandleBeforeLinkSave
    public void handleGameBeforeLinkSave(Game game, Object obj) { logger.info("Before linking {} to {}", game.toString(), obj.toString()); }

    @HandleAfterCreate
    public void handleGameAfterCreate(Game game) { logger.info("After creating: {}", game.toString()); }

    @HandleAfterSave
    public void handleGameAfterSave(Game game) {
        logger.info("After saving: {}", game.toString());
    }

    @HandleAfterDelete
    public void handleGameAfterDelete(Game game) {
        logger.info("After deleting: {}", game.toString());
    }

    @HandleAfterLinkSave
    public void handleGameAfterLinkSave(Game game, Object obj) { logger.info("After linking {} to {}", game.toString(), obj.toString());}
}