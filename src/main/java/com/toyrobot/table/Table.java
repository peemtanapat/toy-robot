package com.toyrobot.table;

import com.toyrobot.piece.Piece;

public class Table {

    public static final Integer DEFAULT_DIMENSION_X = 5;
    public static final Integer DEFAULT_DIMENSION_Y = 5;

    private final Integer dimensionX;
    private final Integer dimensionY;
    private final Piece[][] spaces;

    public Table() {
        this.dimensionX = DEFAULT_DIMENSION_X;
        this.dimensionY = DEFAULT_DIMENSION_Y;
        spaces = new Piece[DEFAULT_DIMENSION_X][DEFAULT_DIMENSION_Y];
    }

    public Table(Integer dimensionX, Integer dimensionY) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        spaces = new Piece[dimensionX][dimensionY];
    }

    public Piece[][] getSpaces() {
        return spaces;
    }

    public Integer getDimensionX() {
        return dimensionX;
    }

    public Integer getDimensionY() {
        return dimensionY;
    }

    public Integer getTopBorder() {
        return dimensionY - 1;
    }

    public Integer getBottomBorder() {
        return 0;
    }

    public Integer getRightBorder() {
        return dimensionX - 1;
    }

    public Integer getLeftBorder() {
        return 0;
    }
}
