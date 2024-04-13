package com.toyrobot.command;

public abstract class Command {

    private String[] parameters;

    public abstract boolean execute();

    public void setParameters(String... parameters) {
        this.parameters = parameters;
    }

    public String[] getParameters() {
        return parameters;
    }
}
