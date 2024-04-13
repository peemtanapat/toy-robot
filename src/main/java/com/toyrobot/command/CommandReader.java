package com.toyrobot.command;


import com.toyrobot.robot.Robot;

import static com.toyrobot.command.CommandKey.LEFT;
import static com.toyrobot.command.CommandKey.MOVE;
import static com.toyrobot.command.CommandKey.PLACE;
import static com.toyrobot.command.CommandKey.REPORT;
import static com.toyrobot.command.CommandKey.RIGHT;

public class CommandReader {

    private Command placeCommand, moveCommand, leftCommand, rightCommand, reportCommand;
    private final Commander commander = new Commander();

    public CommandReader() {}

    public CommandReader(Command placeCommand) {
        this.placeCommand = placeCommand;
    }

    public boolean read(String line, Robot robot) {
        if (!robot.isOnTable() && !line.contains(PLACE.name())) {
            return false;
        }

        if (line.contains(PLACE.name())) {
            placeCommand.setParameters(extractParameters(line, PLACE.name()));
            commander.setCommand(placeCommand);
            return commander.invoke();
        } else if (moveCommand != null && line.contains(MOVE.name())) {
            commander.setCommand(moveCommand);
            return commander.invoke();
        } else if (leftCommand != null && line.contains(LEFT.name())) {
            commander.setCommand(leftCommand);
            return commander.invoke();
        } else if (rightCommand != null && line.contains(RIGHT.name())) {
            commander.setCommand(rightCommand);
            return commander.invoke();
        } else if (reportCommand != null && line.contains(REPORT.name())) {
            commander.setCommand(reportCommand);
            return commander.invoke();
        }

        return false;
    }

    public String[] extractParameters(String line, String commandKeyStr) {
        String parameterPart = line
                .replace(commandKeyStr, "")
                .replaceAll(" ", "")
                .trim();

        return parameterPart.isEmpty() ? null : parameterPart.split(",");
    }

    public void setPlaceCommand(Command placeCommand) {
        this.placeCommand = placeCommand;
    }

    public void setMoveCommand(Command moveCommand) {
        this.moveCommand = moveCommand;
    }

    public void setLeftCommand(Command leftCommand) {
        this.leftCommand = leftCommand;
    }

    public void setRightCommand(Command rightCommand) {
        this.rightCommand = rightCommand;
    }

    public void setReportCommand(Command reportCommand) {
        this.reportCommand = reportCommand;
    }
}
