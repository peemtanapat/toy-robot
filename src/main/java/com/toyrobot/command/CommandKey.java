package com.toyrobot.command;

public enum CommandKey {

    PLACE(3, true, true),
    MOVE(0, false, false),
    LEFT(0, false, false),
    RIGHT(0, false, false),
    REPORT(0, false, false);

    private final int parameterAmount;
    private final boolean isInitialCommand;
    private final boolean requireParameter;

    CommandKey(int parameterAmount, boolean isInitialCommand, boolean requireParameter) {
        this.parameterAmount = parameterAmount;
        this.isInitialCommand = isInitialCommand;
        this.requireParameter = requireParameter;
    }

    public boolean validateParameterAmount(String[] parameters) {
        return !isRequireParameter() || parameters.length == getParameterAmount();
    }

    public int getParameterAmount() {
        return parameterAmount;
    }

    public boolean isInitialCommand() {
        return isInitialCommand;
    }

    public boolean isRequireParameter() {
        return requireParameter;
    }
}
