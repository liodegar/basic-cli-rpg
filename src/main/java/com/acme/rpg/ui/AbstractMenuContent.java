package com.acme.rpg.ui;

import com.acme.rpg.util.ConsoleUtil;

import java.util.Collections;
import java.util.List;

/**
 * Created by Liodegar
 */
public abstract class AbstractMenuContent implements MenuContent {

    private String title;

    private List<String> bottomMessages;

    private List<DetailInfo> details;

    public AbstractMenuContent() {
    }

    public AbstractMenuContent(String title, List<String> bottomMessages) {
        this(title, bottomMessages, Collections.emptyList());
    }

    public AbstractMenuContent(String title, List<String> bottomMessages, List<DetailInfo> details) {
        this.title = title;
        this.bottomMessages = bottomMessages;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getBottomMessages() {
        return bottomMessages;
    }

    @Override
    public void clearContent() {
        ConsoleUtil.clrScr();
    }

    protected void printBottomMessages() {
        if (!bottomMessages.isEmpty()) {
            ConsoleUtil.newLine();
            //ConsoleUtil.log("-----------------------------------------");
        }
        for (String msg : bottomMessages) {
            ConsoleUtil.log(Color.GREEN.format(msg));
        }
        ConsoleUtil.newLine();
    }


    protected void printDetails() {
        for (DetailInfo detailInfo : details) {
            if (detailInfo == null) {
                ConsoleUtil.newLine();
            } else {
                ConsoleUtil.log(Color.CYAN.format(detailInfo.getKey() + "=") + detailInfo.getValue());
            }
        }
    }

}
