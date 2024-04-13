package com.toyrobot.command;

import com.toyrobot.robot.Direction;
import com.toyrobot.robot.Robot;
import com.toyrobot.table.Table;
import com.toyrobot.table.TableService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveCommandTest {

    final Commander commander = new Commander();
    Table table;
    Robot robotA;
    Command moveCommand;
    TableService tableService;

    @BeforeEach
    public void initialCommand() {
        table = new Table(Table.DEFAULT_DIMENSION_X, Table.DEFAULT_DIMENSION_Y);
        tableService = new TableService(table);

        robotA = new Robot();

        moveCommand = new MoveCommand(robotA, tableService);
    }

    @Test
    @DisplayName("Normal move, must be success")
    void moveCommand_valid_shouldBeSuccess() {
        tableService.placeObject(0,0, robotA);
        robotA.setDirection(Direction.NORTH);

        commander.setCommand(moveCommand);
        assertDoesNotThrow(commander::invoke);

        assertEquals(0, robotA.getX());
        assertEquals(1, robotA.getY());
        assertEquals(Direction.NORTH, robotA.getDirection());
        assertEquals(robotA, table.getSpaces()[0][1]);
        assertTrue(robotA.isOnTable());
    }

    @Test
    @DisplayName("Move but not be on table, must be ignored")
    void moveCommand_notOnTable_mustBeIgnored() {
        commander.setCommand(moveCommand);
        commander.invoke();

        assertFalse(robotA.isOnTable());
        assertNull(robotA.getX());
        assertNull(robotA.getY());
        assertNull(robotA.getDirection());
    }

    @Test
    @DisplayName("Move from top border heading NORTH, must be ignored")
    void moveCommand_moveFromTopBorderHeadingNorth_shouldBeIgnored() {
        tableService.placeObject(0,4, robotA);
        robotA.setDirection(Direction.NORTH);

        commander.setCommand(moveCommand);
        commander.invoke();

        assertEquals(0, robotA.getX());
        assertEquals(4, robotA.getY());
        assertEquals(Direction.NORTH, robotA.getDirection());
        assertEquals(robotA, table.getSpaces()[0][4]);
        assertTrue(robotA.isOnTable());
    }

    @Test
    @DisplayName("Move from bottom border heading SOUTH, must be ignored")
    void moveCommand_moveFromBottomBorderHeadingSouth_mustBeIgnored() {
        robotA.setDirection(Direction.SOUTH);
        tableService.placeObject(0,0, robotA);

        commander.setCommand(moveCommand);
        commander.invoke();

        assertEquals(0, robotA.getX());
        assertEquals(0, robotA.getY());
        assertEquals(Direction.SOUTH, robotA.getDirection());
        assertEquals(robotA, table.getSpaces()[0][0]);
        assertTrue(robotA.isOnTable());
    }

    @Test
    @DisplayName("Move from right border heading EAST, must be ignored")
    void moveCommand_moveFromRightBorderHeadingEast_mustBeIgnored() {
        robotA.setDirection(Direction.EAST);
        tableService.placeObject(4,0, robotA);

        commander.setCommand(moveCommand);
        commander.invoke();

        assertEquals(4, robotA.getX());
        assertEquals(0, robotA.getY());
        assertEquals(Direction.EAST, robotA.getDirection());
        assertEquals(robotA, table.getSpaces()[4][0]);
        assertTrue(robotA.isOnTable());
    }

    @Test
    @DisplayName("Move from right border heading WEST, must be ignored")
    void moveCommand_moveFromLeftBorderHeadingWest_mustBeIgnored() {
        robotA.setDirection(Direction.WEST);
        tableService.placeObject(0,4, robotA);

        commander.setCommand(moveCommand);
        commander.invoke();

        assertEquals(0, robotA.getX());
        assertEquals(4, robotA.getY());
        assertEquals(Direction.WEST, robotA.getDirection());
        assertEquals(robotA, table.getSpaces()[0][4]);
        assertTrue(robotA.isOnTable());
    }
}
