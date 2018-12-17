package com.acme.rpg.service;

import com.acme.rpg.dao.DataAccessException;
import com.acme.rpg.domain.character.Character;
import com.acme.rpg.domain.character.Enemy;
import com.acme.rpg.domain.character.PlayerCharacter;
import com.acme.rpg.domain.game.RpgGame;
import com.acme.rpg.domain.game.RpgGameSession;
import com.acme.rpg.domain.game.RpgTopic;

import java.nio.file.Path;
import java.util.List;

/**
 * Defines the contract of the RPG game
 * Created by Liodegar
 */
public interface GameService {

    List<RpgTopic> getRpgTopics() throws BusinessException;

    List<RpgGame> getRpgGamesByTopic(RpgTopic rpgTopic) throws BusinessException;

    Character fight(RpgGameSession rpgGameSession) throws BusinessException;

    /**
     * Saves an instance of RpgGameSession to the file system
     *
     * @param rpgGameSession the game to be saved
     * @return the saved full file name
     * @throws BusinessException if any exception is encountered
     */
    Path saveRpgGameSession(RpgGameSession rpgGameSession) throws BusinessException;

    /**
     * Load an instance of RpgGameSession from the file system
     * @param path The file system path
     * @return an instance of RpgGameSession
     * @throws BusinessException if any exception is encountered
     */
    RpgGameSession loadRpgGameSession(Path path) throws BusinessException;

    /**
     * Retrieves all the saved games from the file system
     * @return a list of Path objects
     * @throws BusinessException if any exception is encountered
     */
    List<String> getSavedFilesFromDirectory() throws BusinessException;
}
