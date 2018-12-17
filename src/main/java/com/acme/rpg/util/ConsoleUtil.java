package com.acme.rpg.util;

import com.acme.rpg.domain.character.Enemy;
import com.acme.rpg.domain.character.PlayerCharacter;
import com.acme.rpg.domain.game.RpgGameSession;
import com.acme.rpg.ui.Color;

/**
 * Created by Liodegar
 */
public final class ConsoleUtil {

    /**
     * To avoid instantiation from outside
     */
    private ConsoleUtil() {
    }

    public static void clrScr() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {
            //Do nothing
        }
    }

    public static void clrScr(int size) {
        for (int i = 0; i < size; i++) {
            newLine();
        }
    }

    public static void log(String msg) {
        System.out.println(msg);
    }

    public static void logWithoutNewLine(String msg) {
        System.out.print(msg);
    }

    public static void newLine() {
        System.out.println();
    }

    public static void printStats(RpgGameSession rpgGameSession){
        PlayerCharacter player = rpgGameSession.getPlayerCharacter();
        Enemy enemy = rpgGameSession.getEnemy();
        String leftAlignFormat = "| %-18s | %-5d | %-18s | %-5d |";
        String headerFormat = "| %-18s | Value | %-18s | Value |";
        ConsoleUtil.newLine();
        ConsoleUtil.log(Color.GREEN.format(String.format("+---------------------------------------------------------+")));
        ConsoleUtil.log(Color.GREEN.format(String.format("|-----------------  Fighters statistics  -----------------|")));
        ConsoleUtil.log(Color.GREEN.format(String.format("+--------------------+-------+--------------------+-------+")));
        ConsoleUtil.log(Color.GREEN.format(String.format(headerFormat, player.getName(), enemy.getName())));
        ConsoleUtil.log(Color.GREEN.format(String.format("+--------------------+-------+--------------------+-------+")));
        ConsoleUtil.log(String.format(leftAlignFormat, "Health",  player.getHealth(), "Health",  enemy.getHealth()));
        ConsoleUtil.log(String.format(leftAlignFormat, "Max Health",  player.getMaxHealth(), "MaxHealth",  enemy.getMaxHealth()));
        ConsoleUtil.log(String.format(leftAlignFormat, "Experience",  player.getExperience(), "Experience",  enemy.getExperience()));
        ConsoleUtil.log(String.format(leftAlignFormat, "Strength",  player.getStrength(), "Strength",  enemy.getStrength()));
        ConsoleUtil.log(String.format(leftAlignFormat, "Dexterity",  player.getDexterity(), "Dexterity",  enemy.getDexterity()));
        ConsoleUtil.log(String.format(leftAlignFormat, "Constitution",  player.getConstitution(), "Constitution",  enemy.getConstitution()));
        ConsoleUtil.log(String.format(leftAlignFormat, "Intelligence",  player.getIntelligence(), "Intelligence",  enemy.getIntelligence()));
        ConsoleUtil.log(String.format(leftAlignFormat, "Wisdom",  player.getWisdom(), "Wisdom",  enemy.getWisdom()));
        ConsoleUtil.log(String.format(leftAlignFormat, "Charisma",  player.getCharisma(), "Charisma",  enemy.getCharisma()));
        ConsoleUtil.log(String.format("+--------------------+-------+--------------------+-------+%n"));
    }

}
