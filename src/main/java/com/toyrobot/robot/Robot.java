package com.toyrobot.robot;

import com.toyrobot.piece.Piece;

public class Robot extends Piece implements RobotAction {

    private Integer x = null;
    private Integer y = null;
    private Direction direction;

    public Robot() {}

    public Robot(Integer x, Integer y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public void setNewPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isOnTable() {
        return x != null && y != null;
    }

    @Override
    public void rotateLeft() {
        switch (direction) {
            case NORTH:
                setDirection(Direction.WEST);
                break;
            case WEST:
                setDirection(Direction.SOUTH);
                break;
            case SOUTH:
                setDirection(Direction.EAST);
                break;
            case EAST:
                setDirection(Direction.NORTH);
                break;
        }
    }

    @Override
    public void rotateRight() {
        switch (direction) {
            case NORTH:
                setDirection(Direction.EAST);
                break;
            case EAST:
                setDirection(Direction.SOUTH);
                break;
            case SOUTH:
                setDirection(Direction.WEST);
                break;
            case WEST:
                setDirection(Direction.NORTH);
                break;
        }
    }

    @Override
    public String getCurrentPosition() {
        return "x=" + x + ", y=" + y + ", direction=" + direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
