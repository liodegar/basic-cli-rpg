package com.acme.rpg.domain.map;

import com.acme.rpg.domain.IDomainObject;

/**
 * Immutable class to model a specific position in the game map
 * Created by Liodegar
 */
public final class Position implements IDomainObject {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position updatePosition(Direction direction) {

        switch (direction) {
            case RIGHT:
                return new Position(this.getX() < GameMap.DEFAULT_SIZE - 2 ? this.getX() + 1 : this.getX(), this.getY());

            case LEFT:
                return new Position(this.getX() >= 2 ? this.getX() - 1 : this.getX(), this.getY());

            case UP:
                return new Position(this.getX(), this.getY() >= 2 ? this.getY() - 1 : this.getY());

            default: //DOWN
                return new Position(this.getX(), this.getY() < GameMap.DEFAULT_SIZE - 2 ? this.getY() + 1 : this.getY());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Position{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }
}
