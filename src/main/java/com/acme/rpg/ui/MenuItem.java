package com.acme.rpg.ui;

/**
 * Created by Liodegar
 */
public class MenuItem {
    private int option;
    private String text;
    private String description;


    public MenuItem(int option, String text) {
        this.option = option;
        this.text = text;
    }

    public MenuItem(int option, String text, String description) {
        this.option = option;
        this.text = text;
        this.description = description;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLine() {
        return String.format("\t [%s] - %s", option, text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItem menuItem = (MenuItem) o;

        if (option != menuItem.option) return false;
        return text != null ? text.equals(menuItem.text) : menuItem.text == null;
    }

    @Override
    public int hashCode() {
        int result = option;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MenuItem{");
        sb.append("option=").append(option);
        sb.append(", text='").append(text).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
