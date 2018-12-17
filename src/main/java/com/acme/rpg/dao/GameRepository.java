package com.acme.rpg.dao;

import com.acme.rpg.domain.game.RpgGameSession;

import java.nio.file.Path;
import java.util.List;

/**
 * Defines the main contract for the data access layer
 * Created by Liodegar
 */
public interface GameRepository {

    /**
     * Saves an instance of RpgGameSession to the file system
     * @param rpgGameSession the game to be saved
     * @param path The file system path
     * @return the saved full file name
     * @throws DataAccessException if any exception is encountered
     */
    Path saveRpgGameSession(RpgGameSession rpgGameSession, Path path) throws DataAccessException;

    /**
     * Load an instance of RpgGameSession from the file system
     * @param path The file system path
     * @return an instance of RpgGameSession
     * @throws DataAccessException if any exception is encountered
     */
    RpgGameSession loadRpgGameSession(Path path) throws DataAccessException;

    /**
     * Retrieves all the saved games from the file system
     * @return a list of Path objects
     * @throws DataAccessException if any exception is encountered
     */
    List<Path> getSavedFilesFromDirectory() throws DataAccessException;

    /**
     * Gets the saving directory
     * @return a Path object
     * @throws DataAccessException if any exception is encountered
     */
    Path getSavingDirectory() throws DataAccessException;

    /**
     * Gets the extension of the saved files
     * @return a list of Path objects
     * @throws DataAccessException if any exception is encountered
     */
    String getSavedFileExtension() throws DataAccessException;
}
