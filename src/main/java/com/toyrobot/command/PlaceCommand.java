package com.toyrobot.command;

import com.toyrobot.robot.Direction;
import com.toyrobot.robot.Robot;
import com.toyrobot.table.TableService;

public class PlaceCommand extends Command {

    private final Robot robot;
    private final TableService tableService;

    public PlaceCommand(Robot robot, TableService tableService) {
        this.robot = robot;
        this.tableService = tableService;
    }

    @Override
    public boolean execute() {
        try {
            int x = Integer.parseInt(getParameters()[0]);
            int y = Integer.parseInt(getParameters()[1]);
            Direction direction = Direction.valueOf(getParameters()[2]);

            if (robot.isOnTable()) {
                tableService.removeObject(robot.getX(), robot.getY());
            }
            tableService.placeObject(x, y, robot);
            robot.setDirection(direction);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
