package com.acme.rpg.ui;

import com.acme.rpg.domain.game.RpgGame;
import com.acme.rpg.domain.game.RpgTopic;
import com.acme.rpg.service.GameService;
import com.acme.rpg.util.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Liodegar
 */
public class GameMenuImpl extends AbstractMenuImpl {

    private GameService gameService = ApplicationContext.getDefaultImplFromInterface(GameService.class);
    private List<RpgGame> rpgGames;
    private RpgTopic rpgTopic;

    public GameMenuImpl(Menu menu, RpgTopic rpgTopic) {
        this.rpgTopic = rpgTopic;
        setMenuContent(buildContent());
        setPreviousMenus(menu);
    }


    @Override
    public MenuContent buildContent() {
        String title = "Please, choose one game from the following list:";
        rpgGames = gameService.getRpgGamesByTopic(rpgTopic);
        List<MenuItem> menuItems = new ArrayList<>();
        for (int i = 0; i < rpgGames.size(); i++) {
            RpgGame rpgGame = rpgGames.get(i);
            menuItems.add(new MenuItem(i + 1, rpgGame.getName(), rpgGame.getStoryLine()));
        }
        setNumberOfOptions(menuItems.size());
        return new TextContentImpl(title, menuItems, Collections.emptyList());
    }

    @Override
    protected List<Menu> buildNextMenus() {
        return Arrays.asList(new CharacterMenuImpl(this, rpgGames.get(getSelectedOptionAsInt() - 1)));
    }

    protected Menu getNextMenu(int option) {
        return getNextMenus().get(0);
    }
}
