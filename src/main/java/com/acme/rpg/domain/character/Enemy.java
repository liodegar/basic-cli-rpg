package com.acme.rpg.domain.character;

import com.acme.rpg.util.RandomUtil;

/**
 * Template for creating enemies
 */
public abstract class Enemy extends Character {
    public Enemy() {
    }

    public Enemy(String name, Race race, Gender gender) {
        super(name, race, gender);
        setHealth(100);
        setMaxHealth(100);
        setLevel(Level.ONE);
        setExperience(RandomUtil.nextInt(0, 50));
    }

    @Override
    public void attack(Character player) {
        if (!(player instanceof PlayerCharacter)) {
            throw new IllegalArgumentException("An enemy can fight only with players. Invalid player=" + player);
        }
        baseAttack(player);
    }

}
