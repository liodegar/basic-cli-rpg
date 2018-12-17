package com.acme.rpg.util;

import com.acme.rpg.dao.GameRepository;
import com.acme.rpg.domain.character.Enemy;
import com.acme.rpg.domain.character.PlayerCharacter;
import com.acme.rpg.domain.game.*;
import com.acme.rpg.service.GameService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests the proper functionality of the ApplicationContext class
 * Created by Liodegar
 */
public class ApplicationContextTest {

    @Test
    public void getFromClassName() {
        //JDK classes
        String initial = ApplicationContext.get("java.lang.String");
        String second = ApplicationContext.get("java.lang.String");
        assertEquals(initial, second);
        assertSame(initial, second);

        //project classses
        assertEquals((FantasyTopicImpl) ApplicationContext.get("com.acme.rpg.domain.game.FantasyTopicImpl"), ApplicationContext.get("com.acme.rpg.domain.game.FantasyTopicImpl"));
        assertSame(ApplicationContext.get("com.acme.rpg.domain.game.FantasyTopicImpl"), ApplicationContext.get("com.acme.rpg.domain.game.FantasyTopicImpl"));

        assertEquals((MartialArtTopicImpl) ApplicationContext.get("com.acme.rpg.domain.game.MartialArtTopicImpl"), ApplicationContext.get("com.acme.rpg.domain.game.MartialArtTopicImpl"));
        assertSame(ApplicationContext.get("com.acme.rpg.domain.game.MartialArtTopicImpl"), ApplicationContext.get("com.acme.rpg.domain.game.MartialArtTopicImpl"));

        assertEquals((KukSoolRpgGameImpl) ApplicationContext.get("com.acme.rpg.domain.game.KukSoolRpgGameImpl"), ApplicationContext.get("com.acme.rpg.domain.game.KukSoolRpgGameImpl"));
        assertSame(ApplicationContext.get("com.acme.rpg.domain.game.KukSoolRpgGameImpl"), ApplicationContext.get("com.acme.rpg.domain.game.KukSoolRpgGameImpl"));

        assertEquals((DarkDungeonRpgGameImpl) ApplicationContext.get("com.acme.rpg.domain.game.DarkDungeonRpgGameImpl"), ApplicationContext.get("com.acme.rpg.domain.game.DarkDungeonRpgGameImpl"));
        assertSame(ApplicationContext.get("com.acme.rpg.domain.game.DarkDungeonRpgGameImpl"), ApplicationContext.get("com.acme.rpg.domain.game.DarkDungeonRpgGameImpl"));
    }

    @Test
    public void testGetFromClass() {
        //JDK classes
        assertEquals(ApplicationContext.get(String.class), ApplicationContext.get(String.class));
        assertSame(ApplicationContext.get(String.class), ApplicationContext.get(String.class));

        //project classes
        assertEquals(ApplicationContext.get(FantasyTopicImpl.class), ApplicationContext.get(FantasyTopicImpl.class));
        assertSame(ApplicationContext.get(FantasyTopicImpl.class), ApplicationContext.get(FantasyTopicImpl.class));

        assertEquals(ApplicationContext.get(MartialArtTopicImpl.class), ApplicationContext.get(MartialArtTopicImpl.class));
        assertSame(ApplicationContext.get(MartialArtTopicImpl.class), ApplicationContext.get(MartialArtTopicImpl.class));

        assertEquals(ApplicationContext.get(KukSoolRpgGameImpl.class), ApplicationContext.get(KukSoolRpgGameImpl.class));
        assertSame(ApplicationContext.get(KukSoolRpgGameImpl.class), ApplicationContext.get(KukSoolRpgGameImpl.class));

        assertEquals(ApplicationContext.get(DarkDungeonRpgGameImpl.class), ApplicationContext.get(DarkDungeonRpgGameImpl.class));
        assertSame(ApplicationContext.get(DarkDungeonRpgGameImpl.class), ApplicationContext.get(DarkDungeonRpgGameImpl.class));
    }


    @Test
    public void testGetDefaultImpl() {
        //project interfaces with default impl
        assertEquals(ApplicationContext.getDefaultImplFromInterface(GameRepository.class), ApplicationContext.getDefaultImplFromInterface(GameRepository.class));
        assertSame(ApplicationContext.getDefaultImplFromInterface(GameRepository.class), ApplicationContext.getDefaultImplFromInterface(GameRepository.class));

        assertEquals(ApplicationContext.getDefaultImplFromInterface(GameService.class), ApplicationContext.getDefaultImplFromInterface(GameService.class));
        assertSame(ApplicationContext.getDefaultImplFromInterface(GameService.class), ApplicationContext.getDefaultImplFromInterface(GameService.class));
    }

    @Test
    public void testGetDefaultImplWithMissingImpl() {
        //RpgGame doesn't have a subclass called "ListImpl"
        assertNull(ApplicationContext.getDefaultImplFromInterface(RpgGame.class));

        //PlayerCharacter doesn't have a subclass called "PlayerCharacterImpl"
        assertNull(ApplicationContext.getDefaultImplFromInterface(PlayerCharacter.class));

        //Enemy doesn't have a subclass called "EnemyImpl"
        assertNull(ApplicationContext.getDefaultImplFromInterface(Enemy.class));
    }
}