package com.evoke.employee.exeception;

public class RoleAssignedException extends Exception {

    private static final long serialVersionUID = -9079454849611061074L;

    public RoleAssignedException() {
        super();
    }

    public RoleAssignedException(final String message) {
        super(message);
    }

}
