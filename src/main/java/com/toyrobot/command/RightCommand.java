package com.toyrobot.command;

import com.toyrobot.robot.Robot;

public class RightCommand extends Command {

    private final Robot robot;

    public RightCommand(Robot robot) {
        this.robot = robot;
    }

    @Override
    public boolean execute() {
        if (!robot.isOnTable()) {
            return false;
        }

        try{
            robot.rotateRight();
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
