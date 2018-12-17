package com.acme.rpg.domain.game;

import com.acme.rpg.domain.character.*;

import java.util.Arrays;

/**
 * Created by Liodegar
 */
public class KukSoolRpgGameImpl extends AbstractRpgGameImpl {
    public KukSoolRpgGameImpl() {
        super("Kuk Sool Fighting", new MartialArtTopicImpl(),
                "The peace of the Hwando Fortress is at risk due to the arrival of some villainous strangers. " +
                        "You must fight them to ensure the safety of the fortress.",
                Arrays.asList(new Mage(), new Rogue(), new Warrior()),
                Arrays.asList(new GenericEnemy("BlackNinja", Race.HUMAN, Gender.MALE),
                        new GenericEnemy("Yakuza", Race.MUTANT, Gender.FEMALE),
                        new GenericEnemy("Ronin", Race.ROBOT, Gender.MALE)));
    }
}


