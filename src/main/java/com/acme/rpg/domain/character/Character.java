package com.acme.rpg.domain.character;

import com.acme.rpg.domain.IDomainObject;
import com.acme.rpg.ui.Color;
import com.acme.rpg.util.ConcurrentUtil;
import com.acme.rpg.util.ConsoleUtil;
import com.acme.rpg.util.RandomUtil;

/**
 * Base class for RPG characters, for both player and enemies.
 */
public abstract class Character implements IDomainObject {

    private static final long serialVersionUID = -3260221744490555391L;
    private String name;
    private String characterClass;
    private int health;
    private int maxHealth;
    private int experience;
    private Race race;
    private Gender gender;
    private Level level;
    //Characteristics
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    public Character() {
    }

    public Character(String name, Race race, Gender gender) {
        this.name = name;
        this.race = race;
        this.gender = gender;
        this.characterClass = this.getClass().getSimpleName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void addExperience(int experience) {
        this.experience = this.experience + experience;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getCharacterClass() {
        return this.characterClass;
    }

    public void levelUp() {
        maxHealth = (int) (maxHealth * getIncreaseLevelFactor());
        health = maxHealth;
        strength = (int) (strength * getIncreaseLevelFactor());
        dexterity = (int) (dexterity * getIncreaseLevelFactor());
        constitution = (int) (constitution * getIncreaseLevelFactor());
        intelligence = (int) (intelligence * getIncreaseLevelFactor());
        wisdom = (int) (wisdom * getIncreaseLevelFactor());
        charisma = (int) (charisma * getIncreaseLevelFactor());
        this.level = Level.getNextLevel(level);
    }

    protected void baseAttack(Character other) {
        ConsoleUtil.log(getColorForPlayer().format(this.getName() + " is attacking " + other.getName()));
        ConcurrentUtil.sleepForMilliSeconds(300);
        int calculatedHittingLikelihood = RandomUtil.nextInt(0, other.getDefense());
        if ((calculatedHittingLikelihood + this.getAttack()) > other.getDefense()) {
            ConcurrentUtil.sleepForMilliSeconds(300);
            ConsoleUtil.log(getColorForPlayer().format(other.getName() + " has been hit!"));
            ConsoleUtil.log(getColorForPlayer().format(other.getName() + " received following damage=" + this.getDamage()));
            other.setHealth(other.getHealth() - this.getDamage());
        } else {
            ConcurrentUtil.sleepForMilliSeconds(300);
            ConsoleUtil.log("You missed the attack");
        }

    }

    private Color getColorForPlayer() {
        return this instanceof PlayerCharacter ? Color.GREEN : Color.RED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Character character = (Character) o;

        if (health != character.health) return false;
        if (maxHealth != character.maxHealth) return false;
        if (experience != character.experience) return false;
        if (strength != character.strength) return false;
        if (dexterity != character.dexterity) return false;
        if (constitution != character.constitution) return false;
        if (intelligence != character.intelligence) return false;
        if (wisdom != character.wisdom) return false;
        if (charisma != character.charisma) return false;
        if (name != null ? !name.equals(character.name) : character.name != null) return false;
        if (race != character.race) return false;
        if (gender != character.gender) return false;
        return level == character.level;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + health;
        result = 31 * result + maxHealth;
        result = 31 * result + experience;
        result = 31 * result + (race != null ? race.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + strength;
        result = 31 * result + dexterity;
        result = 31 * result + constitution;
        result = 31 * result + intelligence;
        result = 31 * result + wisdom;
        result = 31 * result + charisma;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Character{");
        sb.append("name='").append(name).append('\'');
        sb.append(", health=").append(health);
        sb.append(", maxHealth=").append(maxHealth);
        sb.append(", experience=").append(experience);
        sb.append(", race=").append(race);
        sb.append(", gender=").append(gender);
        sb.append(", level=").append(level);
        sb.append(", strength=").append(strength);
        sb.append(", dexterity=").append(dexterity);
        sb.append(", constitution=").append(constitution);
        sb.append(", intelligence=").append(intelligence);
        sb.append(", wisdom=").append(wisdom);
        sb.append(", charisma=").append(charisma);
        sb.append('}');
        return sb.toString();
    }

    public String getMainAttributes() {
        final StringBuilder sb = new StringBuilder(getLabel());
        sb.append("{name='").append(name).append('\'');
        sb.append(", race=").append(race);
        sb.append(", gender=").append(gender);
        sb.append('}');
        return sb.toString();
    }

    public abstract String getLabel();

    //calculated properties based on the characteristics
    public abstract int getAttack();

    public abstract int getDefense();

    public abstract int getDamage();

    public abstract void attack(Character other);

    public abstract double getIncreaseLevelFactor();
}
