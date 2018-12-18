package com.acme.rpg.domain.character;

/**
 * Base class of all player characters
 */
public abstract class PlayerCharacter extends Character {
    public PlayerCharacter() {
    }

    public PlayerCharacter(String name, Race race, Gender gender) {
        super(name, race, gender);
        setHealth(100);
        setMaxHealth(100);
        setLevel(Level.ONE);
    }

    @Override
    public void attack(Character enemy) {
        if (!(enemy instanceof Enemy)) {
            throw new IllegalArgumentException("A player can fight only with enemies. Invalid enemy=" + enemy);
        }
        baseAttack(enemy);
    }

    @Override
    public String getLabel() {
        return "Character";
    }
}

