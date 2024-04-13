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

@DisplayName("Unit tests for PLACE command")
public class PlaceCommandTest {

    final Commander commander = new Commander();
    Table table;
    Robot robotA;
    Command placeCommand;

    @BeforeEach
    public void initialCommand() {
        table = new Table(Table.DEFAULT_DIMENSION_X, Table.DEFAULT_DIMENSION_Y);
        robotA = new Robot();
        TableService tableService = new TableService(table);

        placeCommand = new PlaceCommand(robotA, tableService);
    }

    @Test
    @DisplayName("valid command")
    void whenPlaceCommand_validLine_mustBeSuccess() {
        placeCommand.setParameters("1", "2", "NORTH");
        commander.setCommand(placeCommand);
        commander.invoke();

        assertEquals(1, robotA.getX());
        assertEquals(2, robotA.getY());
        assertEquals(Direction.NORTH, robotA.getDirection());
        assertEquals(table.getSpaces()[1][2], robotA);
        assertTrue(robotA.isOnTable());
        assertDoesNotThrow(placeCommand::execute);
    }

    @Test
    @DisplayName("Negative position x and/or y, must be ignored")
    void whenPlaceCommand_positionNegative_mustBeIgnored() {
        placeCommand.setParameters("-1", "2", "NORTH");
        commander.setCommand(placeCommand);
        commander.invoke();
        assertFalse(robotA.isOnTable());

        placeCommand.setParameters("1", "-2", "NORTH");
        commander.setCommand(placeCommand);
        commander.invoke();
        assertFalse(robotA.isOnTable());

        placeCommand.setParameters("-1", "-2", "NORTH");
        commander.setCommand(placeCommand);
        commander.invoke();
        assertFalse(robotA.isOnTable());
    }

    @Test
    @DisplayName("Position x or y out of table scope, must be ignored")
    void whenPlaceCommand_thatOutOfTableScope_mustBeIgnored() {
        placeCommand.setParameters("1", "5", "NORTH");
        commander.setCommand(placeCommand);
        commander.invoke();

        assertFalse(robotA.isOnTable());
        assertNull(robotA.getX());
        assertNull(robotA.getY());
        assertNull(robotA.getDirection());
    }

    @Test
    @DisplayName("Non-number position x and/or y, must be ignored")
    void whenPlaceCommand_withNonNumber_mustBeIgnored() {
        placeCommand.setParameters("one", "2", "NORTH");
        commander.setCommand(placeCommand);
        commander.invoke();

        assertFalse(robotA.isOnTable());
        assertNull(robotA.getX());
        assertNull(robotA.getY());
        assertNull(robotA.getDirection());
    }

    @Test
    @DisplayName("Typo Direction, must be ignored")
    void whenPlaceCommand_withWrongDirection_mustBeIgnored() {
        placeCommand.setParameters("1", "2", "NORT");
        commander.setCommand(placeCommand);
        commander.invoke();

        assertFalse(robotA.isOnTable());
        assertNull(robotA.getX());
        assertNull(robotA.getY());
        assertNull(robotA.getDirection());
    }

    @Test
    @DisplayName("With no direction, must be ignored")
    void whenPlaceCommand_withNoDirection_mustBeIgnored() {
        placeCommand.setParameters("1", "5");
        commander.setCommand(placeCommand);
        commander.invoke();

        assertFalse(robotA.isOnTable());
        assertNull(robotA.getX());
        assertNull(robotA.getY());
        assertNull(robotA.getDirection());
    }

    @Test
    @DisplayName("Without y parameter, must be ignored")
    void whenPlaceCommand_withNoY_mustBeIgnored() {
        placeCommand.setParameters("1", "NORTH");
        commander.setCommand(placeCommand);
        commander.invoke();

        assertFalse(robotA.isOnTable());
        assertNull(robotA.getX());
        assertNull(robotA.getY());
        assertNull(robotA.getDirection());
    }

    @Test
    @DisplayName("Has only one parameter, must be ignored")
    void whenPlaceCommand_withOnlyOneParameter_mustBeIgnored() {
        placeCommand.setParameters("1");
        commander.setCommand(placeCommand);
        commander.invoke();

        assertFalse(robotA.isOnTable());
        assertNull(robotA.getX());
        assertNull(robotA.getY());
        assertNull(robotA.getDirection());
    }

    @Test
    @DisplayName("No parameters, must be ignored")
    void whenPlaceCommand_withOutParameters_mustBeIgnored() {
        placeCommand.setParameters("1");
        commander.setCommand(placeCommand);
        commander.invoke();

        assertFalse(robotA.isOnTable());
        assertNull(robotA.getX());
        assertNull(robotA.getY());
        assertNull(robotA.getDirection());
    }
}
