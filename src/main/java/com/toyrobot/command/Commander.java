package com.toyrobot.command;

public class Commander {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public boolean invoke() {
        return command.execute();
    }
}
