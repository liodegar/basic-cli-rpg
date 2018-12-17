package com.acme.rpg.domain.character;

/**
 * Created by Liodegar
 */
public class BuddhistMonk extends PlayerCharacter {

    private static final long serialVersionUID = -2253878411654809321L;

    public BuddhistMonk() {
        this(null, null, null);
    }

    public BuddhistMonk(String name, Race race, Gender gender) {
        super(name, race, gender);
        setStrength(70);
        setDexterity(80);
        setConstitution(70);
        setIntelligence(90);
        setWisdom(90);
        setCharisma(80);
    }

    @Override
    public int getAttack() {
        return (getIntelligence() + getWisdom()) / 2;
    }

    @Override
    public int getDefense() {
        return (getConstitution() + getDexterity() + getIntelligence()) / 3;
    }

    @Override
    public int getDamage() {
        return (getIntelligence() + getWisdom()) / (2 * 10);
    }

    @Override
    public double getIncreaseLevelFactor() {
        return 1.05;
    }
}
