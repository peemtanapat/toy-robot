package com.toyrobot.table;

import com.toyrobot.piece.Piece;
import com.toyrobot.robot.Robot;

public class TableService {

    private final Table table;

    public TableService(Table table) {
        this.table = table;
    }

    public void placeObject(int x, int y, Piece object) {
        table.getSpaces()[x][y] = object;
        if (object instanceof Robot) {
            object.setNewPosition(x, y);
        }
    }

    public void removeObject(int x, int y) {
        table.getSpaces()[x][y] = null;
    }

    public Table getTable() {
        return table;
    }
}
