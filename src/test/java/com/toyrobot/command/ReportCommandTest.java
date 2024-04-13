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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    final Commander commander = new Commander();
    Table table;
    Robot robotA;
    Command reportCommand;
    TableService tableService;

    @BeforeEach
    public void initialCommand() {
        System.setOut(new PrintStream(outputStreamCaptor));

        table = new Table(Table.DEFAULT_DIMENSION_X, Table.DEFAULT_DIMENSION_Y);
        tableService = new TableService(table);

        robotA = new Robot();

        reportCommand = new ReportCommand(robotA);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Valid robot, must be reported with correct output")
    void reportValidRobot_outputMustBeCorrect() {
        tableService.placeObject(0,0, robotA);
        robotA.setDirection(Direction.NORTH);

        commander.setCommand(reportCommand);
        assertDoesNotThrow(commander::invoke);

        String output = outputStreamCaptor.toString().trim();

        assertTrue(output.contains("x=0, y=0, direction=NORTH"));
        assertDoesNotThrow(reportCommand::execute);
    }

    @Test
    @DisplayName("Robot that not on table, must be ignored, output is Empty")
    void reportRobotThatNotOnTable_mustBeIgnored() {
        commander.setCommand(reportCommand);
        commander.invoke();

        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.isEmpty());
    }
}
