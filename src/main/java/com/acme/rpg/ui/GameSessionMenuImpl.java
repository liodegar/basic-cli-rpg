package com.acme.rpg.ui;

import com.acme.rpg.domain.game.RpgGameSession;
import com.acme.rpg.domain.map.Direction;
import com.acme.rpg.service.GameService;
import com.acme.rpg.util.ApplicationContext;
import com.acme.rpg.util.ConcurrentUtil;
import com.acme.rpg.util.ConsoleUtil;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Liodegar
 */
public class GameSessionMenuImpl extends AbstractMenuImpl {
    private GameService gameService = ApplicationContext.getDefaultImplFromInterface(GameService.class);
    private RpgGameSession rpgGameSession;

    //menu 6

    public GameSessionMenuImpl(Menu menu, RpgGameSession rpgGameSession) {
        super();
        this.rpgGameSession = rpgGameSession;
        setMenuContent(buildContent());
        setPreviousMenus(menu);
    }


    @Override
    public MenuContent buildContent() {
        String title = "Game menu";
        return new MapContentImpl(title,
                buildDetailInfos("Game menu", rpgGameSession), rpgGameSession,
                Arrays.asList("Map legends: You are [x], Your enemy is [$]",
                        "Map Options: [u]-> Up, [d]-> Down, [r]-> Right, [l]-> Left", "If you want to save and exit, press [s]"));
    }


    @Override
    protected List<Menu> buildNextMenus() {
        return Arrays.asList(new FightMenuImpl(this, rpgGameSession));
    }

    @Override
    public void processMenu() {
        String option = null;
        while (true) {
            printMenu();
            option = getScanner().next();
            setSelectedOption(option);
            if (isValidOption(option, MAP_OPTIONS)) {
                rpgGameSession.getGameMap().updateMap(Direction.getFromCode(option));
                if (rpgGameSession.getGameMap().getPlayerPosition().equals(rpgGameSession.getGameMap().getEnemyPosition())) {
                    ConsoleUtil.log("Start fighting...");
                    setNextMenus(buildNextMenus());
                    getNextMenu(1).processMenu();
                    break;
                }
            } else if (isGeneralOption(option)) {
                checkGeneralOption(option);
                break;
            } else if ("s".equalsIgnoreCase(option)) {
                ConsoleUtil.log("Saving game...");
                Path path = gameService.saveRpgGameSession(rpgGameSession);
                ConsoleUtil.log("Game saved properly as " + path.getName(1));
                ConcurrentUtil.sleepForSeconds(5);
                setNextMenus(Arrays.asList(getRootMenu()));
                getNextMenu(1).processMenu();
                break;
            } else {
                ConsoleUtil.log("Introduce a valid option..");
            }
        }
    }


}
