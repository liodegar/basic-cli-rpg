package com.acme.rpg.domain.character;

/**
 * Created by Liodegar
 */
public class GungdoArcher extends PlayerCharacter {

    public GungdoArcher() {
        this(null, null, null);
    }

    public GungdoArcher(String name, Race race, Gender gender) {
        super(name, race, gender);
        setStrength(60);
        setDexterity(90);
        setConstitution(70);
        setIntelligence(95);
        setWisdom(80);
        setCharisma(90);
    }

    private static final long serialVersionUID = -2232018931029942351L;

    @Override
    public int getAttack() {
        return (getCharisma() + getWisdom()) / 2;
    }

    @Override
    public int getDefense() {
        return (getConstitution() + getDexterity() + getIntelligence() + getWisdom()) / 4;
    }

    @Override
    public int getDamage() {
        return (getCharisma() + getWisdom()) / (2 * 10);
    }

    @Override
    public double getIncreaseLevelFactor() {
        return 1.1;
    }
}
