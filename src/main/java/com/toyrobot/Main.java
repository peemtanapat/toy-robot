package com.toyrobot;

import com.toyrobot.platform.ConsoleApplication;
import com.toyrobot.robot.Robot;
import com.toyrobot.table.Table;

public class Main {
    public static void main(String[] args) {
        Table table = new Table();
        Robot robot = new Robot();
        ConsoleApplication consoleApplication = new ConsoleApplication(table, robot);
        consoleApplication.start(); // -> start program for standard input
    }
}