package com.acme.rpg.domain.game;

import com.acme.rpg.domain.IDomainObject;

/**
 * General contract for RPG topics
 * Created by Liodegar
 */
public interface RpgTopic extends IDomainObject {

    /**
     * The topic genre
     *
     * @return the genre
     */
    String getGenre();


    /**
     * The RPG topic description
     *
     * @return the description
     */
    String getDescription();

}


