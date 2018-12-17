package com.acme.rpg.ui;

import com.acme.rpg.domain.character.Character;
import com.acme.rpg.domain.game.RpgGameSession;
import com.acme.rpg.service.GameService;
import com.acme.rpg.util.ApplicationContext;
import com.acme.rpg.util.ConcurrentUtil;
import com.acme.rpg.util.ConsoleUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Liodegar
 */
public class FightMenuImpl extends AbstractMenuImpl {

    private GameService gameService = ApplicationContext.getDefaultImplFromInterface(GameService.class);
    private RpgGameSession rpgGameSession;

    //menu 7

    public FightMenuImpl(Menu menu, RpgGameSession rpgGameSession) {
        this.rpgGameSession = rpgGameSession;
        setMenuContent(buildContent());
        setPreviousMenus(menu);
    }


    @Override
    public MenuContent buildContent() {
        String title = "Fighting menu";
        rpgGameSession.getGameMap().setInitialState();
        //rpgGameSession.setEnemy(rpgGameSession.getRpgGame().getRandomEnemy());
        return new TextContentImpl(title, buildDetailInfos("Fighting menu", rpgGameSession), rpgGameSession, Collections.emptyList());
    }


    @Override
    protected List<Menu> buildNextMenus() {
        return Arrays.asList(new GameSessionMenuImpl(this, rpgGameSession), null);
    }

    @Override
    public void processMenu() {
        printMenu();
        Character winner = gameService.fight(rpgGameSession);
        printStats(rpgGameSession);
        if (winner.equals(rpgGameSession.getPlayerCharacter()) ){
            ConsoleUtil.log("Congratulations! You have won the match. Your current level=" +rpgGameSession.getPlayerCharacter().getLevel());
            rpgGameSession.setEnemy(rpgGameSession.getRpgGame().getRandomEnemy());
            //System.out.println("next enemy = " + rpgGameSession.getEnemy());
            ConsoleUtil.log("Be careful in your next fight. Your next enemy is " + rpgGameSession.getEnemy().getMainAttributes());
            ConcurrentUtil.sleepForSeconds(5);
            setNextMenus(buildNextMenus());
            getNextMenu(1).processMenu();
        } else {
            ConsoleUtil.log("You are dead!");
            ConcurrentUtil.sleepForSeconds(5);
            ConsoleUtil.log("Game over");
            ConcurrentUtil.sleepForSeconds(5);
            setNextMenus(Arrays.asList(getRootMenu()));
            getNextMenu(1).processMenu();
        }
    }

}
