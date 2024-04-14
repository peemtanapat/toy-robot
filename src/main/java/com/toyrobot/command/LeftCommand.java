package com.toyrobot.command;

import com.toyrobot.robot.Robot;

public class LeftCommand extends Command {

    private final Robot robot;

    public LeftCommand(Robot robot) {
        this.robot = robot;
    }

    @Override
    public boolean execute() {
        if (!robot.isOnTable()) {
            return false;
        }

        try {
            robot.rotateLeft();
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
