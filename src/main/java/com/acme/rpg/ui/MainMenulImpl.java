package com.acme.rpg.ui;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Liodegar
 */
public class MainMenulImpl extends AbstractMenuImpl {

    public MainMenulImpl() {
        setMenuContent(buildContent());
        setNextMenus(buildNextMenus());
        setPreviousMenus(null); //Not required, but for better readability. This is the root menu
    }


    @Override
    public MenuContent buildContent() {
        String title = "Main menu: please, select one of the options.";
        List<MenuItem> menuItems = Arrays.asList(new MenuItem(1, "Start game"),
                new MenuItem(2, "Load game"));
        setNumberOfOptions(menuItems.size());
        return new TextContentImpl(title, menuItems, Collections.emptyList());
    }

    @Override
    protected List<Menu> buildNextMenus() {
        return Arrays.asList(new TopicMenuImpl(this), new LoadGameMenuImpl(this));
    }

}