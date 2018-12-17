package com.acme.rpg.ui;

import java.util.List;

/**
 * Created by Liodegar
 */
public interface Menu {
    List<Menu> getNextMenus();
    Menu getPreviousMenu();
    void clearContent();
    void printMenu();
    void processMenu();
}
