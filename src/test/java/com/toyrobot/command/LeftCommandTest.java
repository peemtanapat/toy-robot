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

public class LeftCommandTest {

    final Commander commander = new Commander();
    Table table;
    Robot robotA;
    Command leftCommand;
    TableService tableService;

    @BeforeEach
    public void initialCommand() {
        table = new Table(Table.DEFAULT_DIMENSION_X, Table.DEFAULT_DIMENSION_Y);
        tableService = new TableService(table);

        robotA = new Robot();

        leftCommand = new LeftCommand(robotA);
    }

    @Test
    @DisplayName("Rotate left but not be on table, must be ignored")
    void leftCommand_robotNotOnTable_mustBeIgnored() {
        commander.setCommand(leftCommand);
        commander.invoke();

        assertFalse(robotA.isOnTable());
        assertNull(robotA.getX());
        assertNull(robotA.getY());
        assertNull(robotA.getDirection());
    }

    @Test
    @DisplayName("Valid left rotate, must be success")
    void leftCommand_valid_shouldBeSuccess() {
        tableService.placeObject(0,1, robotA);
        robotA.setDirection(Direction.NORTH);

        commander.setCommand(leftCommand);

        assertDoesNotThrow(commander::invoke);
        assertEquals(Direction.WEST, robotA.getDirection());
        assertEquals(robotA, table.getSpaces()[0][1]);
        assertTrue(robotA.isOnTable());

        assertDoesNotThrow(commander::invoke);
        assertEquals(Direction.SOUTH, robotA.getDirection());
        assertEquals(robotA, table.getSpaces()[0][1]);
        assertTrue(robotA.isOnTable());

        assertDoesNotThrow(commander::invoke);
        assertEquals(robotA, table.getSpaces()[0][1]);
        assertTrue(robotA.isOnTable());
        assertEquals(Direction.EAST, robotA.getDirection());

        assertDoesNotThrow(commander::invoke);
        assertEquals(robotA, table.getSpaces()[0][1]);
        assertTrue(robotA.isOnTable());
        assertEquals(Direction.NORTH, robotA.getDirection());
    }
}
