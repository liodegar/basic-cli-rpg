package com.acme.rpg.domain.game;

import com.acme.rpg.domain.IDomainObject;
import com.acme.rpg.domain.character.Enemy;
import com.acme.rpg.domain.character.PlayerCharacter;

import java.util.List;

/**
 * Defines the general contract of a RPG game
 * Created by Liodegar
 */
public interface RpgGame extends IDomainObject {

    /**
     * Get the RPG name
     * @return the ame
     */
    String getName();

    /**
     * Gets the specific topic of this game
     * @return the topic
     */
    RpgTopic getTopic();

    /**
     * The game storyline
     *
     * @return the storyline
     */
    String getStoryLine();

    /**
     * Gets a list of the available player characters for this game
     *
     * @return a list of PlayerCharacter instances
     */
    List<PlayerCharacter> getAvailablePlayerCharacters();

    /**
     * Gets a list of the available enemies for this game
     *
     * @return a list of Enemy instances
     */
    List<Enemy> getAvailableEnemies();


    /**
     * Gets a random instance of Enemy
     * @return a fresh instance of Enemy
     */
    Enemy getRandomEnemy();
}
