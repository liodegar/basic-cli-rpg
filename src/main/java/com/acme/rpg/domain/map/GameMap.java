package com.acme.rpg.domain.map;

import com.acme.rpg.domain.IDomainObject;
import com.acme.rpg.util.RandomUtil;

import java.util.Arrays;

/**
 * Created by Liodegar
 */
public class GameMap implements IDomainObject {

    private static final long serialVersionUID = -4364810565741848465L;
    public static final int DEFAULT_SIZE = 10;
    private ElementType[][] map = new ElementType[DEFAULT_SIZE][DEFAULT_SIZE];
    private Position playerPosition;
    private Position enemyPosition;



    public GameMap() {
        setInitialState();
    }

    public void setInitialState() {
        resetMap();
        setPlayerPosition(new Position(1, DEFAULT_SIZE - 2));
        setRandomEnemyPosition();
    }

    private Position getEnemyRandomPosition() {
        return new Position(RandomUtil.nextInt(DEFAULT_SIZE/2, DEFAULT_SIZE - 2), RandomUtil.nextInt(DEFAULT_SIZE/2, DEFAULT_SIZE - 2));
    }

    private void setRandomEnemyPosition() {
        this.enemyPosition = getEnemyRandomPosition();
        setEnemyPosition();
    }
    private void setEnemyPosition() {
        map[enemyPosition.getY()][enemyPosition.getX()] = ElementType.ENEMY;
    }

    private void setPlayerPosition(Position position) {
        playerPosition = position;
        map[playerPosition.getY()][playerPosition.getX()] = ElementType.PLAYER;
    }


    public void updateMap(Direction direction) {
        resetMap();
        setPlayerPosition(playerPosition.updatePosition(direction));
        setEnemyPosition();
    }


    public ElementType getElementType(Position position) {
        return map[position.getY()][position.getX()];
    }

    public Position getPlayerPosition() {
        return playerPosition;
    }

    public Position getEnemyPosition() {
        return enemyPosition;
    }

    private void resetMap() {
        for (ElementType[] array : map) {
            Arrays.fill(array, ElementType.WALL);
        }
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[i].length - 1; j++) {
                map[i][j] = ElementType.EMPTY;
            }
        }
    }

    public ElementType[][] getMap() {
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameMap gameMap = (GameMap) o;

        if (!Arrays.deepEquals(map, gameMap.map)) return false;
        if (playerPosition != null ? !playerPosition.equals(gameMap.playerPosition) : gameMap.playerPosition != null)
            return false;
        return enemyPosition != null ? enemyPosition.equals(gameMap.enemyPosition) : gameMap.enemyPosition == null;
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(map);
        result = 31 * result + (playerPosition != null ? playerPosition.hashCode() : 0);
        result = 31 * result + (enemyPosition != null ? enemyPosition.hashCode() : 0);
        return result;
    }
}
