package com.acme.rpg.service;

import com.acme.rpg.dao.GameRepository;
import com.acme.rpg.domain.character.*;
import com.acme.rpg.domain.character.Character;
import com.acme.rpg.domain.game.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.AdditionalAnswers.returnsSecondArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests the proper functionality of GameService
 * Created by Liodegar
 */

public class GameServiceImplTest {

    @Mock
    private GameRepository repository;

    @InjectMocks
    private GameService systemUnderTest = new GameServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRpgTopics() {
        //when
        List<RpgTopic> rpgTopicList = systemUnderTest.getRpgTopics();

        //validate
        assertNotNull(rpgTopicList);
        assertFalse(rpgTopicList.isEmpty());
        assertTrue(rpgTopicList.contains(new FantasyTopicImpl()));
        assertTrue(rpgTopicList.contains(new MartialArtTopicImpl()));
    }

    @Test
    public void testGetRpgGamesByFantasyTopic() {
        //when
        List<RpgGame> rpgGames = systemUnderTest.getRpgGamesByTopic(new FantasyTopicImpl());

        //validate
        assertNotNull(rpgGames);
        assertFalse(rpgGames.isEmpty());
        assertTrue(rpgGames.contains(new DarkDungeonRpgGameImpl()));
    }

    @Test
    public void testGetRpgGamesByMartialArtTopic() {
        //when
        List<RpgGame> rpgGames = systemUnderTest.getRpgGamesByTopic(new MartialArtTopicImpl());

        //validate
        assertNotNull(rpgGames);
        assertFalse(rpgGames.isEmpty());
        assertTrue(rpgGames.contains(new KukSoolRpgGameImpl()));
    }

    /**
     * Tests the fight between the first player against the first enemy of the all differen games
     */
    @Test
    public void testFight() {
        //given conditions
        List<RpgTopic> rpgTopicList = systemUnderTest.getRpgTopics();
        for (RpgTopic rpgTopic : rpgTopicList) {
            List<RpgGame> rpgGames = systemUnderTest.getRpgGamesByTopic(rpgTopic);
            for (RpgGame rpgGame : rpgGames) {

                PlayerCharacter playerCharacter = rpgGame.getAvailablePlayerCharacters().get(0);
                int initialPlayerExperience = playerCharacter.getExperience();
                int initialPlayerHealth = playerCharacter.getHealth();

                Enemy enemy = rpgGame.getAvailableEnemies().get(0);
                int initialEnemyExperience = enemy.getExperience();
                int initialEnemyHealth = enemy.getHealth();

                RpgGameSession rpgGameSession = new RpgGameSessionImpl(playerCharacter, enemy, rpgGame);

                //when
                Character winner = systemUnderTest.fight(rpgGameSession);

                //validate
                assertNotNull(winner);
                assertFalse(rpgGames.isEmpty());
                assertEquals(100, initialPlayerHealth);
                assertEquals(100, initialEnemyHealth);

                if (winner.equals(playerCharacter)) {
                    assertTrue(initialPlayerExperience < winner.getExperience());
                    assertTrue(initialPlayerHealth > winner.getHealth());
                    assertTrue(0 > enemy.getHealth());
                    assertEquals(initialEnemyExperience, winner.getExperience());
                } else {
                    assertTrue(initialEnemyHealth < winner.getHealth());
                    assertTrue(0 > playerCharacter.getHealth());
                    assertEquals(initialEnemyExperience, winner.getExperience());
                }
            }
        }
    }

    @Test
    public void testSaveRpgGameSession() {
        //given conditions
        RpgGameSession rpgGameSession = getRpgGameSession();

        //when
        doAnswer(returnsSecondArg()).when(repository).saveRpgGameSession(any(RpgGameSession.class), any(Path.class));
        //validate
        Path result = systemUnderTest.saveRpgGameSession(rpgGameSession);
        //then
        assertEquals(Paths.get(rpgGameSession.generateSavingFileName()), result);
    }

    private RpgGameSession getRpgGameSession() {
        PlayerCharacter playerCharacter = new Mage("The Wizard", Race.ELF, Gender.HERMAPHODITE);
        Enemy enemy = new GenericEnemy("Chavez the Destructor", Race.HUMAN, Gender.MALE);
        return new RpgGameSessionImpl(playerCharacter, enemy, new DarkDungeonRpgGameImpl());
    }

    @Test
    public void testLoadRpgGameSession() {
        //given conditions
        RpgGameSession rpgGameSession = getRpgGameSession();
        //when
        doReturn(rpgGameSession).when(repository).loadRpgGameSession(any(Path.class));
        //validate
        RpgGameSession result = systemUnderTest.loadRpgGameSession(Paths.get("directory"));
        //then
        assertEquals(rpgGameSession, result);
    }

    @Test
    public void getSavedFilesFromDirectory() {
        //given conditions
        List<Path> paths = Arrays.asList(Paths.get("Mage_Chavez_Level_FIVE_2018-12-15_22_03_00.rpg"),
                Paths.get("Mage_Chavez_Level_ONE_2018-12-14_22_03_00.rpg"),
                Paths.get("Mage_Invencible_Level_FIVE_2018-12-17_22_03_00.rpg"));

        List<String> expected = Arrays.asList("Mage_Chavez_Level_FIVE_2018-12-15_22_03_00",
                "Mage_Chavez_Level_ONE_2018-12-14_22_03_00",
                "Mage_Invencible_Level_FIVE_2018-12-17_22_03_00");

        //when
        doReturn(paths).when(repository).getSavedFilesFromDirectory();
        //validate
        List<String> result = systemUnderTest.getSavedFilesFromDirectory();
        //then
        assertEquals(expected, result);
    }
}