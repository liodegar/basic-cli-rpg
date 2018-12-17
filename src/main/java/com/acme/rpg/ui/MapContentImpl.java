package com.acme.rpg.ui;

import com.acme.rpg.domain.character.Level;
import com.acme.rpg.domain.game.RpgGameSession;
import com.acme.rpg.domain.map.ElementType;
import com.acme.rpg.util.ConsoleUtil;

import java.util.List;

/**
 * Created by Liodegar
 */
public class MapContentImpl extends AbstractMenuContent {

    private RpgGameSession rpgGameSession;

    public MapContentImpl() {
    }

    public MapContentImpl(String title, List<DetailInfo> details, RpgGameSession rpgGameSession, List<String> bottomMessages) {
        super(Color.BOLD.format(title), bottomMessages, details);
        this.rpgGameSession = rpgGameSession;
    }

    @Override
    public void printContent() {
        printTitle();
        printDetails();
        printGameMap();
        printBottomMessages();
    }

    private void printGameMap() {
        if (rpgGameSession.getPlayerCharacter().getLevel() == Level.getHighestLevel()) {
            ConsoleUtil.newLine();
            ConsoleUtil.log("------------------------------------------------------------------------");
            ConsoleUtil.log(Color.YELLOW.format("You have reached the highest level. You can exit or save this game."));
            ConsoleUtil.log("------------------------------------------------------------------------");
            ConsoleUtil.newLine();
            ConsoleUtil.printStats(rpgGameSession);
        } else {
            ElementType[][] map = rpgGameSession.getGameMap().getMap();
            for (ElementType[] array : map) {
                for (ElementType elementType : array) {
                    ConsoleUtil.logWithoutNewLine(elementType.getCharacter());
                    ConsoleUtil.logWithoutNewLine(" ");
                }
                ConsoleUtil.newLine();
            }
        }
    }


    private void printTitle() {
        ConsoleUtil.newLine();
        ConsoleUtil.log(Color.BLUE.format(getTitle()));
        ConsoleUtil.newLine();
    }


}
