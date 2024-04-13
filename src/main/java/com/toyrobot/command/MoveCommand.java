package com.toyrobot.command;

import com.toyrobot.robot.Robot;
import com.toyrobot.table.TableService;

import java.util.Objects;

public class MoveCommand extends Command {

    private final Robot robot;
    private final TableService tableService;

    public MoveCommand(Robot robot, TableService tableService) {
        this.robot = robot;
        this.tableService = tableService;
    }

    @Override
    public boolean execute() {
        if (!robot.isOnTable()) {
            return false;
        }

//        if (!commandValidator.validateMove())

        switch (robot.getDirection()) {
            case NORTH:
                if (Objects.equals(robot.getY(), tableService.getTable().getTopBorder())) {
                    return false;
                }
                tableService.removeObject(robot.getX(), robot.getY());
                int newY = robot.getY() + 1;
                tableService.placeObject(robot.getX(), newY, robot);
                break;
            case SOUTH:
                if (Objects.equals(robot.getY(), tableService.getTable().getBottomBorder())) {
                    return false;
                }
                tableService.removeObject(robot.getX(), robot.getY());
                newY = robot.getY() - 1;
                tableService.placeObject(robot.getX(), newY, robot);
                break;
            case EAST:
                if (Objects.equals(robot.getX(), tableService.getTable().getRightBorder())) {
                    return false;
                }
                tableService.removeObject(robot.getX(), robot.getY());
                int newX = robot.getX() + 1;
                tableService.placeObject(newX, robot.getY(), robot);
                break;
            case WEST:
                if (Objects.equals(robot.getX(), tableService.getTable().getLeftBorder())) {
                    return false;
                }
                tableService.removeObject(robot.getX(), robot.getY());
                newX = robot.getX() - 1;
                tableService.placeObject(newX, robot.getY(), robot);
                break;
        }

        return true;
    }
}
