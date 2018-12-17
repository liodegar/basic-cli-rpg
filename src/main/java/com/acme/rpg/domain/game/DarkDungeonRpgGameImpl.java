package com.acme.rpg.domain.game;

import com.acme.rpg.domain.character.*;

import java.util.Arrays;

/**
 * Created by Liodegar
 */
public class DarkDungeonRpgGameImpl extends AbstractRpgGameImpl {

    public DarkDungeonRpgGameImpl() {
        super("Dark Dungeon", new FantasyTopicImpl(),
                "In this game you need to fight against different enemies for the freedom of the Kabudary kingdom.",
                Arrays.asList(new Mage(), new Rogue(), new Warrior()),
                Arrays.asList(new GenericEnemy("Daggerfall", Race.ORC, Gender.MALE),
                        new GenericEnemy("Skyrim", Race.ORC, Gender.FEMALE),
                        new GenericEnemy("Noramouc", Race.DWARF, Gender.MALE),
                        new GenericEnemy("Dalogron", Race.HUMAN, Gender.MALE)));
    }


}
