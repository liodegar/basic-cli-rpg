package com.acme.rpg.domain.character;

import com.acme.rpg.util.RandomUtil;

public class GenericEnemy extends Enemy {

    private static final long serialVersionUID = -4566208111103982421L;

    public GenericEnemy() {
        this(null, null, null);
    }

    public GenericEnemy(String name, Race race, Gender gender) {
        super(name, race, gender);
        setStrength(RandomUtil.nextInt(70, 90));
        setDexterity(RandomUtil.nextInt(60, 80));
        setConstitution(RandomUtil.nextInt(80, 100));
        setIntelligence(RandomUtil.nextInt(50, 90));
        setWisdom(RandomUtil.nextInt(50, 90));
        setCharisma(RandomUtil.nextInt(50, 80));
    }

    @Override
    public String getLabel() {
        return "Enemy";
    }

    @Override
    public int getAttack() {
        return (getStrength() + getDexterity() + getIntelligence()) /  3;
    }

    @Override
    public int getDefense() {
        return (getConstitution() + getDexterity()) / 2;
    }

    @Override
    public int getDamage() {
        return (getStrength() + getDexterity() + getWisdom() + getCharisma()) / (4 * 10);
    }

    @Override
    public double getIncreaseLevelFactor() {
        return 1;
    }
}
