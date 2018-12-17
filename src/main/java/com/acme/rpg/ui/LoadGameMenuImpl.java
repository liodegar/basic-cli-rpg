package com.acme.rpg.ui;

import com.acme.rpg.domain.game.RpgGameSession;
import com.acme.rpg.service.GameService;
import com.acme.rpg.util.ApplicationContext;
import com.acme.rpg.util.ConsoleUtil;
import com.acme.rpg.util.logging.RpgLogger;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Created by Liodegar
 */
public class LoadGameMenuImpl extends AbstractMenuImpl {
    public static final RpgLogger logger = RpgLogger.getLogger(LoadGameMenuImpl.class);

    private GameService gameService = ApplicationContext.getDefaultImplFromInterface(GameService.class);
    private List<String> files = gameService.getSavedFilesFromDirectory();
    private RpgGameSession rpgGameSession;

    public LoadGameMenuImpl(Menu menu) {
        setMenuContent(buildContent());
        setPreviousMenus(menu);
    }

    @Override
    public MenuContent buildContent() {
        String title = "Please, choose the saved game from the following list:";

        List<MenuItem> menuItems = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            menuItems.add(new MenuItem(i + 1, files.get(i)));
        }
        setNumberOfOptions(menuItems.size());
        return new TextContentImpl(title, menuItems, Collections.emptyList(), "There is no saved files");
    }

    @Override
    protected List<Menu> buildNextMenus() {
        return Arrays.asList(getPreviousMenu(), new GameSessionMenuImpl(this, rpgGameSession));
    }

    @Override
    public void processMenu() {
        printMenu();
        String option = getScanner().next();
        try {
            while (true) {
                if (isValidIntOption(option) && isOptionInRange(option)) {
                    rpgGameSession = gameService.loadRpgGameSession(Paths.get(files.get(Integer.parseInt(option) - 1)));
                    setNextMenus(buildNextMenus());
                    getNextMenu(2).processMenu();
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
            logger.error("Exception processing LoadGame menu=" + rpgGameSession, e);
        }
    }


}
