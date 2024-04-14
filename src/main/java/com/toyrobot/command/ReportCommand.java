package com.toyrobot.command;

import com.toyrobot.robot.Robot;

public class ReportCommand extends Command {

    private final Robot robot;

    public ReportCommand(Robot robot) {
        this.robot = robot;
    }

    @Override
    public boolean execute() {
        if (!robot.isOnTable()) {
            return false;
        }

        try {
            String output = "Output: " + robot.getCurrentPosition();

            System.out.println(output);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
