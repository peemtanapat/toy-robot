package com.toyrobot.platform;

import com.toyrobot.command.Command;
import com.toyrobot.command.CommandReader;
import com.toyrobot.command.LeftCommand;
import com.toyrobot.command.MoveCommand;
import com.toyrobot.command.PlaceCommand;
import com.toyrobot.command.ReportCommand;
import com.toyrobot.command.RightCommand;
import com.toyrobot.robot.Robot;
import com.toyrobot.table.Table;
import com.toyrobot.table.TableService;

import java.util.Scanner;

import static com.toyrobot.command.CommandKey.PLACE;

public class ConsoleApplication {

    private static final Scanner scanner = new Scanner(System.in);
    private String line;

    private final Table table;
    private final Robot robot;
    private CommandReader commandReader;

    public ConsoleApplication(Table table, Robot robot) {
        this.table = table;
        this.robot = robot;
    }

    private void initial() {
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

        printManual();

        while (true) {
            System.out.print("Enter Command: ");
            line = scanner.nextLine();
            line = line.toUpperCase();

            if (line.contains(PLACE.name())) {
                boolean passed = commandReader.read(line, robot);
                if (passed) {
                    System.out.println("Robot is ready on table...");
                    break;
                }
            }
        }
    }

    public void start() {
        initial();

        while (true) {
            System.out.print("Enter Command: ");
            line = scanner.nextLine();
            line = line.toUpperCase();

            commandReader.read(line, robot);
        }
    }

    private void printManual() {
        System.out.println(
                "---Application Manual---\n" +
                        "Robot command list:\n" +
                        "- 'PLACE xNum,yNum,direction': place(initial) robot on the table\n" +
                        "  xNum options: a number start from 0 to (dimensionX - 1)\n" +
                        "  yNum options: a number start from 0 to (dimensionY - 1)\n" +
                        "  direction options: NORTH, SOUTH, EAST, WEST\n" +
                        "- 'MOVE': move robot forward heading to current direction\n" +
                        "- 'LEFT': rotate left 90 degree\n" +
                        "- 'RIGHT': rotate right 90 degree\n" +
                        "- 'REPORT': display current position of the robot\n" +
                        "------------------------"
        );
    }
}
