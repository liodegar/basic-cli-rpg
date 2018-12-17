package com.acme.rpg.ui;

import com.acme.rpg.domain.character.Enemy;
import com.acme.rpg.domain.character.Gender;
import com.acme.rpg.domain.character.PlayerCharacter;
import com.acme.rpg.domain.character.Race;
import com.acme.rpg.domain.game.RpgGame;
import com.acme.rpg.domain.game.RpgGameSession;
import com.acme.rpg.domain.game.RpgGameSessionImpl;
import com.acme.rpg.util.ConcurrentUtil;
import com.acme.rpg.util.ConsoleUtil;
import com.acme.rpg.util.logging.RpgLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Liodegar
 */
public class CharacterMenuImpl extends AbstractMenuImpl {

    private static final RpgLogger logger = RpgLogger.getLogger(CharacterMenuImpl.class);

    private RpgGameSession rpgGameSession;

    private RpgGame rpgGame;

    private List<PlayerCharacter> playerCharacters;

    public CharacterMenuImpl(Menu menu, RpgGame rpgGame) {
        this.rpgGame = rpgGame;
        setMenuContent(buildContent());
        setPreviousMenus(menu);
    }


    @Override
    public MenuContent buildContent() {
        String title = "Please, choose one character, from the following list:";
        playerCharacters = rpgGame.getAvailablePlayerCharacters();
        List<MenuItem> menuItems = new ArrayList<>();
        for (int i = 0; i < playerCharacters.size(); i++) {
            PlayerCharacter playerCharacter = playerCharacters.get(i);
            menuItems.add(new MenuItem(i + 1, playerCharacter.getCharacterClass()));
        }
        return new TextContentImpl(title, menuItems, Collections.emptyList());
    }

    @Override
    protected List<Menu> buildNextMenus() {
        return Arrays.asList(new GameSessionMenuImpl(this, rpgGameSession));
    }

    @Override
    public void processMenu() {
        printMenu();
        String option = getScanner().next();
        try {
            while (true) {

                if (isValidIntOption(option)) {
                    PlayerCharacter playerCharacter = playerCharacters.get(Integer.parseInt(option) - 1);
                    ConsoleUtil.log("Introduce a character name..");
                    String characterName = getScanner().next();
                    playerCharacter.setName(characterName);

                    setRace(playerCharacter);
                    setGender(playerCharacter);
                    Enemy enemy = getEnemy();

                    rpgGameSession = new RpgGameSessionImpl(playerCharacter, enemy, rpgGame);
                    printStats(rpgGameSession);
                    setNextMenus(buildNextMenus());
                    getNextMenu(1).processMenu();
                    break;
                } else if (isGeneralOption(option)) {
                    checkGeneralOption(option);
                    break;

                } else {
                    ConsoleUtil.log("Introduce a valid option..");
                    option = getScanner().next();
                }
            }
        } catch (Exception e) {
            logger.error("Exception processing character menu=" + rpgGameSession, e);
        }

    }

    private Enemy getEnemy() {
        ConsoleUtil.log("The game is selecting a suitable enemy to you...");
        ConcurrentUtil.sleepForSeconds(1);
        Enemy enemy = rpgGame.getRandomEnemy();
        ConsoleUtil.log("The selected enemy is: " + enemy.getMainAttributes());
        ConcurrentUtil.sleepForSeconds(3);
        return enemy;
    }

    private void setRace(PlayerCharacter playerCharacter) {
        ConsoleUtil.log("Select the character race from the following list:");
        printMenuItems(getRaceMenuItems());
        String option = getScanner().next();
        while (true) {
            if (isValidIntOption(option)) {
                int race = Integer.parseInt(option);
                playerCharacter.setRace(Race.getFromValue(race));
                break;
            } else {
                ConsoleUtil.log("Introduce a valid option..");
                option = getScanner().next();
            }
        }
    }

    private void setGender(PlayerCharacter playerCharacter) {
        ConsoleUtil.log("Select the character gender from the following list:");
        printMenuItems(getSexMenuItems());
        String option = getScanner().next();
        while (true) {
            if (isValidIntOption(option)) {
                int gender = Integer.parseInt(option);
                playerCharacter.setGender(Gender.getFromValue(gender));
                break;
            } else {
                ConsoleUtil.log("Introduce a valid option..");
                option = getScanner().next();
            }
        }
    }

    private void printMenuItems(List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems) {
            ConsoleUtil.log(Color.CYAN.format(menuItem.getLine()));
        }
    }

    private List<MenuItem> getRaceMenuItems() {
        return Race.getRaces().stream()
                .map(race -> new MenuItem(race.getId(), race.getCode())).collect(Collectors.toList());
    }

    private List<MenuItem> getSexMenuItems() {
        return Gender.getGenders().stream()
                .map(race -> new MenuItem(race.getId(), race.getCode())).collect(Collectors.toList());
    }

}
