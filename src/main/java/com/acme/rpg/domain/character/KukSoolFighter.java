package com.acme.rpg.domain.character;

/**
 * Created by Liodegar
 */
public class KukSoolFighter extends PlayerCharacter {

    public KukSoolFighter() {
        this(null, null, null);
    }

    public KukSoolFighter(String name, Race race, Gender gender) {
        super(name, race, gender);
        setStrength(90);
        setDexterity(95);
        setConstitution(95);
        setIntelligence(70);
        setWisdom(60);
        setCharisma(60);
    }

    private static final long serialVersionUID = 5790629805924137294L;

    @Override
    public int getAttack() {
        return (getStrength() + getDexterity()) / 2;
    }

    @Override
    public int getDefense() {
        return (getConstitution() + getDexterity()) / 2;
    }

    @Override
    public int getDamage() {
        return (getStrength() + getDexterity()) / (2 * 10);
    }

    @Override
    public double getIncreaseLevelFactor() {
        return 1.2;
    }
}
