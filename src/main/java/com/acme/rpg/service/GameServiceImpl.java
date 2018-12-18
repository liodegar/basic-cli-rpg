package com.acme.rpg.service;

import com.acme.rpg.dao.GameRepository;
import com.acme.rpg.domain.character.Character;
import com.acme.rpg.domain.character.Enemy;
import com.acme.rpg.domain.character.PlayerCharacter;
import com.acme.rpg.domain.game.RpgGame;
import com.acme.rpg.domain.game.RpgGameSession;
import com.acme.rpg.domain.game.RpgTopic;
import com.acme.rpg.util.ApplicationContext;
import com.acme.rpg.util.ConfigurationProperty;
import com.acme.rpg.util.ReflectionUtil;
import com.acme.rpg.util.logging.RpgLogger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Default impl of GameService interface
 * Created by Liodegar
 */
public class GameServiceImpl implements GameService {

    public static final RpgLogger logger = RpgLogger.getLogger(GameServiceImpl.class);

    private GameRepository gameRepository = ApplicationContext.getDefaultImplFromInterface(GameRepository.class);


    @Override
    public List<RpgTopic> getRpgTopics() throws BusinessException {
        try {
            return ConfigurationProperty.getTopicClassNames()
                    .stream().map(s -> (RpgTopic) ReflectionUtil.newInstance(s))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Exception encountered during RPG topics retrieval");
            throw new BusinessException("Exception encountered during RPG topics retrieval", e);
        }
    }

    @Override
    public List<RpgGame> getRpgGamesByTopic(RpgTopic rpgTopic) throws BusinessException {
        try {
            String prefix = rpgTopic.getClass().getSimpleName();
            return ConfigurationProperty.getValuesFromKeyWithPrefix(prefix)
                    .stream().map(s -> (RpgGame) ReflectionUtil.newInstance(s))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Exception encountered during RPG games retrieval. " + rpgTopic);
            throw new BusinessException("Exception encountered during RPG games retrieval " + rpgTopic, e);
        }
    }

    @Override
    public Character fight(RpgGameSession rpgGameSession) throws BusinessException {
        PlayerCharacter player = rpgGameSession.getPlayerCharacter();
        Enemy enemy = rpgGameSession.getEnemy();
        try {
            return rpgGameSession.fight();
        } catch (Exception e) {
            logger.error(String.format("Exception encountered during the fight between %s and %s", player, enemy));
            throw new BusinessException(String.format("Exception encountered during the fight between %s and %s", player, enemy), e);
        }

    }

    /**
     * Saves an instance of RpgGameSession to the file system
     *
     * @param rpgGameSession the game to be saved
     * @return the saved full file name
     * @throws BusinessException if any exception is encountered
     */
    @Override
    public Path saveRpgGameSession(RpgGameSession rpgGameSession) throws BusinessException {
        String fileName = null;
        try {
            fileName = rpgGameSession.generateSavingFileName();
            return gameRepository.saveRpgGameSession(rpgGameSession, Paths.get(fileName));
        } catch (Exception e) {
            logger.error("Exception encountered saving the in path=" + fileName + rpgGameSession, e);
            throw new BusinessException("Exception encountered saving the in path=" + fileName + rpgGameSession, e);
        }
    }

    /**
     * Load an instance of RpgGameSession from the file system
     *
     * @param path The file system path
     * @return an instance of RpgGameSession
     * @throws BusinessException if any exception is encountered
     */
    @Override
    public RpgGameSession loadRpgGameSession(Path path) throws BusinessException {
        try {
            return gameRepository.loadRpgGameSession(path);
        } catch (Exception e) {
            logger.error("Exception encountered saving the in path=" + path, e);
            throw new BusinessException("Exception encountered saving the in path=" + path, e);
        }
    }

    /**
     * Retrieves all the saved games from the file system
     *
     * @return a list of Path objects
     * @throws BusinessException if any exception is encountered
     */
    @Override
    public List<String> getSavedFilesFromDirectory() throws BusinessException {
        try {
            return gameRepository.getSavedFilesFromDirectory()
                    .stream().map(path -> path.toFile().getName())
                    .map(s -> s.substring(0, s.indexOf(".")))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Exception encountered retrieving the saved files", e);
            return Collections.emptyList();
        }
    }
}
