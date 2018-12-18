package com.acme.rpg.dao;

import com.acme.rpg.domain.game.RpgGameSession;
import com.acme.rpg.util.ConfigurationProperty;
import com.acme.rpg.util.logging.RpgLogger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Default impl of the GameRepository interface
 * Created by Liodegar
 */
public class GameRepositoryImpl implements GameRepository {

    public static final RpgLogger logger = RpgLogger.getLogger(GameRepositoryImpl.class);

    protected static final String DEFAULT_EXT = ".rpg";
    protected static final String DEFAULT_DIR = "rpgSavedGames";

    protected static final String DEFAULT_EXT_KEY = "rpg.game.saving.file.extension";
    protected static final String DEFAULT_DIR_KEY = "rpg.game.saving.directory";


    /**
     * Saves an instance of RpgGameSession to the file system
     *
     * @param rpgGameSession the game to be saved
     * @param path           The file system path
     * @return the saved full file name
     * @throws DataAccessException if any exception is encountered
     */
    @Override
    public Path saveRpgGameSession(RpgGameSession rpgGameSession, Path path) throws DataAccessException {
        String fileName = getFullFileName(path);
        createFileIfNotExists();
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(rpgGameSession);
            return Paths.get(fileName);
        } catch (Exception e) {
            logger.error("Exception encountered saving game to " + fileName);
            throw new DataAccessException("Exception encountered saving game to " + fileName, e);
        }
    }

    /**
     * Creates the directory if it doesn't exist
     * @throws DataAccessException  if any exception is encountered
     */
    private void createFileIfNotExists() throws DataAccessException {
        Path dir = null;
        try {
            dir = getSavingDirectory();
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
        } catch (Exception e) {
            logger.error("Exception creating the following directory=" + dir);
            throw new DataAccessException("Exception creating the following directory=" + dir);
        }
    }

    private String getFullFileName(Path path) {
        return new StringBuilder(100)
                .append(getSavingDirectory())
                .append("/")
                .append(path.getFileName().toString())
                .append(getSavedFileExtension()).toString();
    }

    /**
     * Load an instance of RpgGameSession from the file system
     *
     * @param path The file system path
     * @return an instance of RpgGameSession
     * @throws DataAccessException if any exception is encountered
     */
    @Override
    public RpgGameSession loadRpgGameSession(Path path) throws DataAccessException {
        try (FileInputStream fileInputStream = new FileInputStream(getFullFileName(path))) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (RpgGameSession) objectInputStream.readObject();
        } catch (Exception e) {
            logger.error("Exception encountered loading game from " + getSavingDirectory());
            throw new DataAccessException("Exception encountered loading game from " + getSavingDirectory(), e);
        }
    }

    /**
     * Retrieves all the saved games from the file system
     *
     * @return a list of Path objects
     * @throws DataAccessException if any exception is encountered
     */
    @Override
    public List<Path> getSavedFilesFromDirectory() throws DataAccessException {
        try (Stream<Path> paths = Files.walk(getSavingDirectory())) {
            return paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(getSavedFileExtension()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Exception encountered during saved RPG games retrieval from " + getSavingDirectory());
            return Collections.emptyList();
        }
    }

    /**
     * Gets the saving directory
     *
     * @return a Path object
     * @throws DataAccessException if any exception is encountered
     */
    @Override
    public Path getSavingDirectory() throws DataAccessException {
        try {
            return Paths.get(ConfigurationProperty.getString(DEFAULT_DIR_KEY, DEFAULT_DIR));
        } catch (Exception e) {
            logger.error("Exception encountered getting the saving directory");
            throw new DataAccessException("Exception encountered getting the saving directory", e);
        }
    }

    /**
     * Gets the extension of the saved files
     *
     * @return a list of Path objects
     * @throws DataAccessException if any exception is encountered
     */
    @Override
    public String getSavedFileExtension() throws DataAccessException {
        try {
            return ConfigurationProperty.getString(DEFAULT_EXT_KEY, DEFAULT_EXT);
        } catch (Exception e) {
            logger.error("Exception encountered getting the saving files extension");
            throw new DataAccessException("Exception encountered getting the saving files extension", e);
        }
    }
}
