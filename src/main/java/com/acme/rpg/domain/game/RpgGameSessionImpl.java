package com.acme.rpg.domain.game;

import com.acme.rpg.domain.character.Character;
import com.acme.rpg.domain.character.Enemy;
import com.acme.rpg.domain.character.Level;
import com.acme.rpg.domain.character.PlayerCharacter;
import com.acme.rpg.domain.map.GameMap;
import com.acme.rpg.ui.Color;
import com.acme.rpg.util.ConcurrentUtil;
import com.acme.rpg.util.ConsoleUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Liodegar
 */
public class RpgGameSessionImpl implements RpgGameSession {

    private static final long serialVersionUID = -2647868415080699919L;

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");

    private PlayerCharacter playerCharacter;
    private Enemy enemy;
    private GameMap gameMap;
    private RpgGame rpgGame;

    public RpgGameSessionImpl(PlayerCharacter playerCharacter, Enemy enemy, RpgGame rpgGame) {
        this.playerCharacter = playerCharacter;
        this.enemy = enemy;
        this.rpgGame = rpgGame;
        this.gameMap = new GameMap();
    }

    @Override
    public PlayerCharacter getPlayerCharacter() {
        return playerCharacter;
    }

    @Override
    public Enemy getEnemy() {
        return enemy;
    }

    @Override
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public GameMap getGameMap() {
        return gameMap;
    }

    @Override
    public RpgGame getRpgGame() {
        return rpgGame;
    }

    @Override
    public Character fight() {
        while (playerCharacter.isAlive() && enemy.isAlive()) {
            ConsoleUtil.log("You are going to attack your enemy");
            ConcurrentUtil.sleepForSeconds(1);
            playerCharacter.attack(enemy);
            if (enemy.isAlive()) {
                ConcurrentUtil.sleepForSeconds(1);
                ConsoleUtil.log("Your enemy is going to attack you");
                enemy.attack(playerCharacter);
            }
        }
        if (playerCharacter.isAlive()) {
            ConcurrentUtil.sleepForSeconds(3);
            playerCharacter.addExperience(enemy.getExperience());
            ConsoleUtil.log("You survived the fight and gained the following experience=" + enemy.getExperience());
            ConsoleUtil.log(String.format("Your enemy reduced your health to %s, but don't worry, it will be restored again!", playerCharacter.getHealth()));
            playerCharacter.setHealth(playerCharacter.getMaxHealth());
            if (playerCharacter.getLevel() == Level.getHighestLevel()) {
                ConsoleUtil.log("*********************************");
                ConsoleUtil.log(Color.YELLOW.format("You have gained the highest level"));
                ConsoleUtil.log("*********************************");
                ConcurrentUtil.sleepForSeconds(2);
            } else if (playerCharacter.getExperience() >= Level.getNextLevel(playerCharacter.getLevel()).getExperience()) {
                ConcurrentUtil.sleepForSeconds(2);
                ConsoleUtil.log("***********************************************************");
                ConsoleUtil.log(Color.YELLOW.format("You have enough experience to get the next level"));
                playerCharacter.levelUp();
                ConsoleUtil.log(Color.YELLOW.format("You got the level=" + playerCharacter.getLevel()));
                ConsoleUtil.log("***********************************************************");
            }
            return playerCharacter;
        }
        return enemy;
    }

    @Override
    public String generateSavingFileName() {
        return new StringBuilder(100)
                .append(playerCharacter.getCharacterClass())
                .append("_")
                .append(playerCharacter.getName())
                .append("_Level_")
                .append(playerCharacter.getLevel())
                .append("_")
                .append(LocalDateTime.now().format(DTF))
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RpgGameSessionImpl that = (RpgGameSessionImpl) o;

        if (playerCharacter != null ? !playerCharacter.equals(that.playerCharacter) : that.playerCharacter != null)
            return false;
        if (enemy != null ? !enemy.equals(that.enemy) : that.enemy != null) return false;
        if (gameMap != null ? !gameMap.equals(that.gameMap) : that.gameMap != null) return false;
        return rpgGame != null ? rpgGame.equals(that.rpgGame) : that.rpgGame == null;
    }

    @Override
    public int hashCode() {
        int result = playerCharacter != null ? playerCharacter.hashCode() : 0;
        result = 31 * result + (enemy != null ? enemy.hashCode() : 0);
        result = 31 * result + (gameMap != null ? gameMap.hashCode() : 0);
        result = 31 * result + (rpgGame != null ? rpgGame.hashCode() : 0);
        return result;
    }
}
