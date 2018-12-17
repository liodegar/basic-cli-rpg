package com.acme.rpg.domain.game;

import com.acme.rpg.domain.character.Enemy;
import com.acme.rpg.domain.character.GenericEnemy;
import com.acme.rpg.domain.character.PlayerCharacter;
import com.acme.rpg.util.ConcurrentUtil;
import com.acme.rpg.util.RandomUtil;

import java.util.List;

/**
 * Created by Liodegar
 */
public abstract class AbstractRpgGameImpl implements RpgGame {

    private static final long serialVersionUID = 7852098267988119923L;
    private String name;
    private RpgTopic rpgTopic;
    private String storyLine;
    private List<PlayerCharacter> playerCharacters;
    private List<Enemy> enemies;

    public AbstractRpgGameImpl(String name, RpgTopic rpgTopic, String storyLine, List<PlayerCharacter> playerCharacters,
                               List<Enemy> enemies) {
        this.name = name;
        this.rpgTopic = rpgTopic;
        this.storyLine = storyLine;
        this.playerCharacters = playerCharacters;
        this.enemies = enemies;
    }

    /**
     * Get the RPG name
     *
     * @return the ame
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the specific topic of this game
     *
     * @return the topic
     */
    @Override
    public RpgTopic getTopic() {
        return rpgTopic;
    }

    /**
     * The game storyline
     *
     * @return the storyline
     */
    @Override
    public String getStoryLine() {
        return storyLine;
    }

    /**
     * Gets a list of the available player characters for this game
     *
     * @return a list of PlayerCharacter instances
     */
    @Override
    public List<PlayerCharacter> getAvailablePlayerCharacters() {
        return playerCharacters;
    }

    /**
     * Gets a list of the available enemies for this game
     *
     * @return a list of Enemy instances
     */
    @Override
    public List<Enemy> getAvailableEnemies() {
        return enemies;
    }


    @Override
    public Enemy getRandomEnemy() {
        int random = RandomUtil.nextInt(0, getAvailableEnemies().size() - 1);
        Enemy enemy = getAvailableEnemies().get(random);
        return new GenericEnemy(enemy.getName(), enemy.getRace(), enemy.getGender());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractRpgGameImpl that = (AbstractRpgGameImpl) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return rpgTopic != null ? rpgTopic.equals(that.rpgTopic) : that.rpgTopic == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (rpgTopic != null ? rpgTopic.hashCode() : 0);
        return result;
    }
}
