package com.acme.rpg.ui;

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
public class TopicMenuImpl extends AbstractMenuImpl {

    private GameService gameService = ApplicationContext.getDefaultImplFromInterface(GameService.class);

    private List<RpgTopic> rpgTopics = gameService.getRpgTopics();

    public TopicMenuImpl(Menu menu) {
        setMenuContent(buildContent());
        setPreviousMenus(menu);
    }

    @Override
    public MenuContent buildContent() {
        String title = "Please, choose the RPG topic, from the following list:";
        List<MenuItem> menuItems = new ArrayList<>();
        for (int i = 0; i < rpgTopics.size(); i++) {
            RpgTopic rpgTopic = rpgTopics.get(i);
            menuItems.add(new MenuItem(i + 1, rpgTopic.getGenre(), rpgTopic.getDescription()));
        }
        setNumberOfOptions(menuItems.size());
        return new TextContentImpl(title, menuItems, Collections.emptyList());
    }

    @Override
    protected List<Menu> buildNextMenus() {
        return Arrays.asList(new GameMenuImpl(this, rpgTopics.get(getSelectedOptionAsInt() - 1)));
    }

    protected Menu getNextMenu(int option) {
        return getNextMenus().get(0);
    }

}
