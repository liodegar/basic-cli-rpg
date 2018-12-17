package com.acme.rpg.ui;

import com.acme.rpg.domain.game.RpgGameSession;
import com.acme.rpg.util.ConsoleUtil;
import com.acme.rpg.util.StringUtils;

import java.util.List;

/**
 * Created by Liodegar
 */
public class TextContentImpl extends AbstractMenuContent {

    private List<MenuItem> menuItems;

    private RpgGameSession rpgGameSession;

    private String defaultMessage;

    public TextContentImpl() {
    }

    public TextContentImpl(String title, List<MenuItem> menuItems, List<String> bottomMessages) {
        super(Color.BOLD.format(title), bottomMessages);
        this.menuItems = menuItems;
    }

    public TextContentImpl(String title, List<MenuItem> menuItems, List<String> bottomMessages, String defaultMessage) {
        super(Color.BOLD.format(title), bottomMessages);
        this.menuItems = menuItems;
        this.defaultMessage = defaultMessage;
    }

    public TextContentImpl(String title, List<DetailInfo> details, RpgGameSession rpgGameSession, List<String> bottomMessages) {
        super(Color.BOLD.format(title), bottomMessages, details);
        this.rpgGameSession = rpgGameSession;
    }

    @Override
    public void printContent() {
        printTitle();
        printDetails();
        printMenuItems();
        printBottomMessages();
    }


    private void printMenuItems() {
        if (menuItems != null) {
            if (menuItems.isEmpty()) {
                ConsoleUtil.log(defaultMessage);
            }
            for (MenuItem menuItem : menuItems) {
                ConsoleUtil.log(Color.CYAN.format(menuItem.getLine()));
                printDescription(menuItem.getDescription());
            }
        }
    }

    private void printDescription(String description) {
        if (description != null) {
            List<String> descriptionChunks = StringUtils.getLines(description, 60);
            for (String descriptionChunk : descriptionChunks) {
                ConsoleUtil.log(String.format("\t\t%s", descriptionChunk));
            }
        }
    }


    private void printTitle() {
        ConsoleUtil.newLine();
        ConsoleUtil.log(Color.BLUE.format(getTitle()));
        ConsoleUtil.newLine();
    }

    @Override
    public void clearContent() {
        ConsoleUtil.clrScr();
    }
}
