package com.acme.rpg.domain.game;

/**
 * Base class of a RpgTopic
 * Created by Liodegar
 */
public abstract class AbstractRpgTopicImpl implements RpgTopic {

    private String genre;

    private String description;

    public AbstractRpgTopicImpl() {
    }

    public AbstractRpgTopicImpl(String genre, String description) {
        this.genre = genre;
        this.description = description;
    }

    /**
     * The topic genre
     *
     * @return the genre
     */
    @Override
    public String getGenre() {
        return genre;
    }

    /**
     * The RPG topic description
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractRpgTopicImpl that = (AbstractRpgTopicImpl) o;

        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = genre != null ? genre.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
