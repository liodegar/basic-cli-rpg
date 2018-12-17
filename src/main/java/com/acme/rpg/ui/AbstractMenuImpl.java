package com.acme.rpg.ui;

import com.acme.rpg.domain.character.Character;
import com.acme.rpg.domain.character.Enemy;
import com.acme.rpg.domain.character.PlayerCharacter;
import com.acme.rpg.domain.game.RpgGameSession;
import com.acme.rpg.util.ConcurrentUtil;
import com.acme.rpg.util.ConsoleUtil;

import java.util.*;

/**
 * Created by Liodegar
 */
public abstract class AbstractMenuImpl implements Menu {

    public static final List<String> GENERAL_OPTIONS = Collections.unmodifiableList(Arrays.asList("x", "m"));
    public static final List<String> MAP_OPTIONS = Collections.unmodifiableList(Arrays.asList("u", "d", "r", "l"));

    private String header = "Welcome to Multi-Topic RPG (MT-RPG)";
    private MenuContent menuContent;
    private String footer = "MT-RPG Lio Copyright 2018.";
    private List<Menu> nextMenus;
    private Menu previousMenu;
    private Scanner scanner = new Scanner(System.in);
    private String selectedOption;
    private int numberOfOptions;


    public AbstractMenuImpl() {
    }


    @Override
    public List<Menu> getNextMenus() {
        return nextMenus;
    }

    @Override
    public Menu getPreviousMenu() {
        return previousMenu;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void printHeader() {
        ConsoleUtil.log(Color.BOLD.format(Color.GREEN.format("-------------------------------------------------------------------------------")));
        ConsoleUtil.log((Color.BOLD.format(Color.GREEN.format(String.format("----------------------%s----------------------", header)))));
        ConsoleUtil.log((Color.BOLD.format(Color.GREEN.format("-------------------------------------------------------------------------------"))));
    }

    public void setMenuContent(MenuContent menuContent) {
        this.menuContent = menuContent;
    }

    public void setNextMenus(List<Menu> nextMenus) {
        this.nextMenus = nextMenus;
    }

    public void setPreviousMenus(Menu previousMenu) {
        this.previousMenu = previousMenu;
    }


    public void printContent() {
        menuContent.printContent();
    }

    @Override
    public void clearContent() {
        ConsoleUtil.clrScr();
    }


    public void printFooter() {
        ConsoleUtil.log(Color.YELLOW.format("-------------------------------------------------------------------------------"));
        ConsoleUtil.log(Color.YELLOW.format(String.format("-  Options: [x]-> Exit, [m]-> Go to Main menu  -  %s---", footer)));
        ConsoleUtil.log(Color.YELLOW.format("-------------------------------------------------------------------------------"));
    }


    @Override
    public void printMenu() {
        clearContent();
        printHeader();
        printContent();
        printFooter();
    }

    @Override
    public void processMenu() {
        printMenu();
        String option = getScanner().next();
        while (true) {
            if (isValidIntOption(option) && isOptionInRange(option)) {
                setSelectedOption(option);
                setNextMenus(buildNextMenus());
                getNextMenu(Integer.parseInt(option)).processMenu();
                break;

            } else if (isGeneralOption(option)) {
                checkGeneralOption(option);
                break;

            } else {
                ConsoleUtil.log("Introduce a valid option..");
                option = getScanner().next();
            }
        }
    }

    protected boolean isOptionInRange(String value) {
        int option = Integer.parseInt(value);
        return (option > 0 && option <= getNumberOfOptions());
    }

    protected void checkGeneralOption(String option) {
        if ("x".equalsIgnoreCase(option)) {
            ConsoleUtil.log("Bye ...");
            ConsoleUtil.clrScr();
            System.exit(0);
        } else if ("m".equalsIgnoreCase(option)) {
            setSelectedOption(option);
            setNextMenus(Arrays.asList(getRootMenu()));
            getNextMenu(1).processMenu();
        }
    }


    protected void setSelectedOption(String option) {
        this.selectedOption = option;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public int getSelectedOptionAsInt() {
        try {
            return Integer.parseInt(selectedOption);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    protected List<DetailInfo> buildDetailInfos(String title, RpgGameSession rpgGameSession) {
        PlayerCharacter playerCharacter = rpgGameSession.getPlayerCharacter();
        Enemy enemy = rpgGameSession.getEnemy();

        List<DetailInfo> detailInfos = new ArrayList<>();
        detailInfos.add(new DetailInfo("Character class", playerCharacter.getCharacterClass()));
        detailInfos.add(new DetailInfo("Level", playerCharacter.getLevel().getCode()));
        detailInfos.add(new DetailInfo("Experience", String.valueOf(playerCharacter.getExperience())));
        detailInfos.add(new DetailInfo("Health", String.valueOf(playerCharacter.getHealth())));
        setDetailInfo(playerCharacter, detailInfos);
        detailInfos.add(null);
        setDetailInfo(enemy, detailInfos);
        return detailInfos;
    }


    protected void setDetailInfo(Character character, List<DetailInfo> detailInfos) {
        detailInfos.add(new DetailInfo(character.getLabel() + " name", character.getName()));
        detailInfos.add(new DetailInfo(character.getLabel() + " race", character.getRace().getCode()));
        detailInfos.add(new DetailInfo(character.getLabel() + " gender", character.getGender().getCode()));
    }

    protected Menu getRootMenu() {
        Menu previous = this.getPreviousMenu();
        while (previous.getPreviousMenu() != null) {
            previous = previous.getPreviousMenu();
        }
        return previous;
    }


    protected boolean isValidIntOption(String option) {
        return (option != null && option.matches("\\d+"));
    }

    protected boolean isGeneralOption(String option) {
        return isValidOption(option, GENERAL_OPTIONS);
    }

    protected boolean isValidOption(String option, List<String> validOptions) {
        return validOptions.stream().anyMatch(s -> s.equalsIgnoreCase(option));
    }


    protected void printStats(RpgGameSession rpgGameSession) {
        ConsoleUtil.printStats(rpgGameSession);
        ConsoleUtil.log("Be patient. We are starting the fighting match...");
        ConcurrentUtil.sleepForSeconds(10);
    }

    protected Menu getNextMenu(int option) {
        return nextMenus.get(--option);
    }

    protected abstract MenuContent buildContent();

    protected abstract List<Menu> buildNextMenus();

    protected void setNumberOfOptions(int numberOfOptions) {
        this.numberOfOptions = numberOfOptions;
    }

    public int getNumberOfOptions() {
        return numberOfOptions;
    }
}
