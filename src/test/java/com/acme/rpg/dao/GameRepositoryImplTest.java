package com.acme.rpg.dao;

import com.acme.rpg.domain.character.*;
import com.acme.rpg.domain.game.DarkDungeonRpgGameImpl;
import com.acme.rpg.domain.game.RpgGameSession;
import com.acme.rpg.domain.game.RpgGameSessionImpl;
import com.acme.rpg.service.GameService;
import com.acme.rpg.service.GameServiceImpl;
import com.acme.rpg.util.ConfigurationProperty;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.acme.rpg.dao.GameRepositoryImpl.DEFAULT_DIR;
import static com.acme.rpg.dao.GameRepositoryImpl.DEFAULT_DIR_KEY;
import static org.junit.Assert.*;

/**
 * Validates the functionality of GameRepositoryImpl
 * Created by Liodegar
 */
public class GameRepositoryImplTest {

    public static final String PATH = "GameRepositoryImplTest";

    private GameRepository systemUnderTest = new GameRepositoryImpl();


    private RpgGameSession getRpgGameSession() {
        PlayerCharacter playerCharacter = new Mage("The Wizard", Race.ELF, Gender.HERMAPHODITE);
        Enemy enemy = new GenericEnemy("Chavez the Destructor", Race.HUMAN, Gender.MALE);
        return new RpgGameSessionImpl(playerCharacter, enemy, new DarkDungeonRpgGameImpl());
    }


    @Test
    public void testSaveAndLoadRpgGameSession() {
        //given conditions
        RpgGameSession rpgGameSession = getRpgGameSession();
        String extension = systemUnderTest.getSavedFileExtension();
        String fileName = "file1";
        Path path = Paths.get(fileName);

        //validate
        Path savingPath = systemUnderTest.getSavingDirectory();
        Path pathResult = systemUnderTest.saveRpgGameSession(rpgGameSession, path);
        RpgGameSession rpgGameSessionResult = systemUnderTest.loadRpgGameSession(path);

        //then
        assertNotNull(pathResult);
        assertNotNull(rpgGameSessionResult);
        assertEquals(Paths.get(savingPath + "/" + fileName + extension), pathResult);
        assertEquals(rpgGameSession, rpgGameSessionResult);
    }


    @Test
    public void testGetSavedFilesFromDirectory() {
        //validate
        List<Path> pathsResult = systemUnderTest.getSavedFilesFromDirectory();
        assertNotNull(pathsResult);
    }

    @Test
    public void testGetSavingDirectory() {
        //validate
        Path savingDirectory = systemUnderTest.getSavingDirectory();
        String directoryFromProperty = ConfigurationProperty.getString(GameRepositoryImpl.DEFAULT_DIR_KEY);

        //then
        assertNotNull(savingDirectory);
        if (directoryFromProperty == null) {
            assertEquals(GameRepositoryImpl.DEFAULT_DIR, savingDirectory);
        } else {
            assertEquals(Paths.get(directoryFromProperty), savingDirectory);
        }
    }

    @Test
    public void testGetSavedFileExtension() {
        //validate
        String extension = systemUnderTest.getSavedFileExtension();
        String extFromProperty = ConfigurationProperty.getString(GameRepositoryImpl.DEFAULT_EXT_KEY);

        //then
        assertNotNull(extension);
        if (extFromProperty == null) {
            assertEquals(".rpg", extension);
        } else {
            assertEquals(extFromProperty, extension);
        }
    }
}