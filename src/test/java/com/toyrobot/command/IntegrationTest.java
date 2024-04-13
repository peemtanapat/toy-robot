package com.toyrobot.command;

import com.toyrobot.robot.Direction;
import com.toyrobot.robot.Robot;
import com.toyrobot.table.Table;
import com.toyrobot.table.TableService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Robot robot;
    private CommandReader commandReader;

    @BeforeEach
    void initial() {
        System.setOut(new PrintStream(outputStreamCaptor));

        Table table = new Table();
        robot = new Robot();

        TableService tableService = new TableService(table);

        Command placeCommand = new PlaceCommand(robot, tableService);
        Command moveCommand = new MoveCommand(robot, tableService);
        Command leftCommand = new LeftCommand(robot);
        Command rightCommand = new RightCommand(robot);
        Command reportCommand = new ReportCommand(robot);

        commandReader = new CommandReader();
        commandReader.setPlaceCommand(placeCommand);
        commandReader.setMoveCommand(moveCommand);
        commandReader.setLeftCommand(leftCommand);
        commandReader.setRightCommand(rightCommand);
        commandReader.setReportCommand(reportCommand);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Valid PLACE at first line")
    void integration_placeAtFirstLine() {
        List<String> commandLines = new ArrayList<>();
        commandLines.add("PLACE 0,1,NORTH");
        commandLines.add("MOVE");
        commandLines.add("REPORT");

        for (String line: commandLines) {
            commandReader.read(line, robot);
        }

        String output = outputStreamCaptor.toString().trim();

        assertTrue(robot.isOnTable());
        assertEquals(0, robot.getX());
        assertEquals(2, robot.getY());
        assertEquals(Direction.NORTH, robot.getDirection());
        assertTrue(output.contains("x=0, y=2, direction=NORTH"));
    }

    @Test
    @DisplayName("Valid PLACE at last line")
    void integration_validPlaceAtLastLine() {
        List<String> commandLines = new ArrayList<>();
        commandLines.add("PLACE 0,5");
        commandLines.add("MOVE");
        commandLines.add("LEFT");
        commandLines.add("RIGHT");
        commandLines.add("MOVE");
        commandLines.add("REPORT");
        commandLines.add("PLACE 1,3,NORTH");

        for (String line: commandLines) {
            commandReader.read(line, robot);
        }

        assertTrue(robot.isOnTable());
        assertEquals(1, robot.getX());
        assertEquals(3, robot.getY());
        assertEquals(Direction.NORTH, robot.getDirection());
    }

    @Test
    @DisplayName("Multiple valid PLACE commands")
    void integration_multipleValidPlaceCommands() {
        List<String> commandLines = new ArrayList<>();
        commandLines.add("PLACE 0,5");
        commandLines.add("PLACE 0,0,NORTH");
        commandLines.add("MOVE");
        commandLines.add("PLACE 0,1,EAST");
        commandLines.add("LEFT");
        commandLines.add("RIGHT");
        commandLines.add("PLACE 4,4,SOUTH");
        commandLines.add("MOVE");
        commandLines.add("REPORT");
        commandLines.add("PLACE 1,3,NORTH");
        commandLines.add("REPORT");

        for (String line: commandLines) {
            commandReader.read(line, robot);
        }

        String output = outputStreamCaptor.toString().trim();

        assertTrue(robot.isOnTable());
        assertEquals(1, robot.getX());
        assertEquals(3, robot.getY());
        assertEquals(Direction.NORTH, robot.getDirection());
        assertTrue(output.contains("x=1, y=3, direction=NORTH"));
    }

    @Test
    @DisplayName("No any valid PLACE command")
    void integration_noAnyValidPlaceCommand() {
        List<String> commandLines = new ArrayList<>();
        commandLines.add("PLACE 1,");
        commandLines.add("MOVE");
        commandLines.add("MOVE");
        commandLines.add("LEFT");
        commandLines.add("a b c d e f g h");
        commandLines.add("PLACE 1,5,NORTH");
        commandLines.add("PLACE 1,4,NORT");
        commandLines.add("PLACE 1,NORT");
        commandLines.add("PLACE NORT");
        commandLines.add("PLACE 1");
        commandLines.add("PLA 1,4,NORTH");
        commandLines.add("ppppp");
        commandLines.add("REPORT");
        commandLines.add("RIGHT");
        commandLines.add("MOVE");
        commandLines.add("REPORT");

        for (String line: commandLines) {
            commandReader.read(line, robot);
        }

        assertFalse(robot.isOnTable());
        assertNull(robot.getX());
        assertNull(robot.getY());
        assertNull(robot.getDirection());
    }

    @Test
    @DisplayName("Contain empty lines")
    void integration_containEmptyLines() {
        List<String> commandLines = new ArrayList<>();
        commandLines.add("PLACE 1,2,SOUTH");
        commandLines.add("");
        commandLines.add("MOVE");
        commandLines.add("");
        commandLines.add("REPORT");

        for (String line: commandLines) {
            commandReader.read(line, robot);
        }

        String output = outputStreamCaptor.toString().trim();

        assertTrue(robot.isOnTable());
        assertEquals(1, robot.getX());
        assertEquals(1, robot.getY());
        assertEquals(Direction.SOUTH, robot.getDirection());
        assertTrue(output.contains("x=1, y=1, direction=SOUTH"));
    }
}
