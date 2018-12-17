package com.acme.rpg.util;

import com.acme.rpg.domain.game.DarkDungeonRpgGameImpl;
import com.acme.rpg.domain.game.FantasyTopicImpl;
import com.acme.rpg.domain.game.KukSoolRpgGameImpl;
import com.acme.rpg.domain.game.MartialArtTopicImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Validates the proper functionality of the ReflectionUtil class
 * Created by Liodegar
 */
public class ReflectionUtilTest {

    @Test
    public void testNewInstanceFromClassName() {
        //JDK classes
        assertEquals(new String(), ReflectionUtil.newInstance("java.lang.String"));
        assertEquals(new ArrayList(), ReflectionUtil.newInstance("java.util.ArrayList"));
        assertEquals(new HashMap(), ReflectionUtil.newInstance("java.util.HashMap"));

        //project classses
        assertEquals(new FantasyTopicImpl(), ReflectionUtil.newInstance("com.acme.rpg.domain.game.FantasyTopicImpl"));
        assertEquals(new MartialArtTopicImpl(), ReflectionUtil.newInstance("com.acme.rpg.domain.game.MartialArtTopicImpl"));
        assertEquals(new KukSoolRpgGameImpl(), ReflectionUtil.newInstance("com.acme.rpg.domain.game.KukSoolRpgGameImpl"));
        assertEquals(new DarkDungeonRpgGameImpl(), ReflectionUtil.newInstance("com.acme.rpg.domain.game.DarkDungeonRpgGameImpl"));
    }

    @Test
    public void newInstanceFromClass() {
        //JDK classes
        assertEquals(new String(), ReflectionUtil.newInstance(java.lang.String.class));
        assertEquals(new ArrayList(), ReflectionUtil.newInstance(java.util.ArrayList.class));
        assertEquals(new HashMap(), ReflectionUtil.newInstance(java.util.HashMap.class));

        //project classses
        assertEquals(new FantasyTopicImpl(), ReflectionUtil.newInstance(com.acme.rpg.domain.game.FantasyTopicImpl.class));
        assertEquals(new MartialArtTopicImpl(), ReflectionUtil.newInstance(com.acme.rpg.domain.game.MartialArtTopicImpl.class));
        assertEquals(new KukSoolRpgGameImpl(), ReflectionUtil.newInstance(com.acme.rpg.domain.game.KukSoolRpgGameImpl.class));
        assertEquals(new DarkDungeonRpgGameImpl(), ReflectionUtil.newInstance(com.acme.rpg.domain.game.DarkDungeonRpgGameImpl.class));
    }

    @Test
    public void testGetClass() {
        //JDK classes
        assertEquals(String.class, ReflectionUtil.getClass("java.lang.String"));
        assertEquals(ArrayList.class, ReflectionUtil.getClass("java.util.ArrayList"));
        assertEquals(HashMap.class, ReflectionUtil.getClass("java.util.HashMap"));

        //project classses
        assertEquals(FantasyTopicImpl.class, ReflectionUtil.getClass("com.acme.rpg.domain.game.FantasyTopicImpl"));
        assertEquals(MartialArtTopicImpl.class, ReflectionUtil.getClass("com.acme.rpg.domain.game.MartialArtTopicImpl"));
        assertEquals(KukSoolRpgGameImpl.class, ReflectionUtil.getClass("com.acme.rpg.domain.game.KukSoolRpgGameImpl"));
        assertEquals(DarkDungeonRpgGameImpl.class, ReflectionUtil.getClass("com.acme.rpg.domain.game.DarkDungeonRpgGameImpl"));
    }
}