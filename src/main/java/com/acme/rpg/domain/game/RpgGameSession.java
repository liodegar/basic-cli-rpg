package com.acme.rpg.domain.game;

import com.acme.rpg.domain.IDomainObject;
import com.acme.rpg.domain.character.Enemy;
import com.acme.rpg.domain.character.PlayerCharacter;
import com.acme.rpg.domain.character.Character;
import com.acme.rpg.domain.map.GameMap;

import java.nio.file.Path;

/**
 * Defines the contract of a RPG game session
 * Created by Liodegar
 */
public interface RpgGameSession extends IDomainObject {

    PlayerCharacter getPlayerCharacter();

    Enemy getEnemy();

    void setEnemy(Enemy enemy);

    GameMap getGameMap();

    RpgGame getRpgGame();

    Character fight();

    String generateSavingFileName();
}
