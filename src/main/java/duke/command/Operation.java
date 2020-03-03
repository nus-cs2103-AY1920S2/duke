package duke.command;


public enum Operation {
    DONE, LIST, EVENT, TODO, DEADLINE, DELETE, BYE, FIND,
    //These commands are for the expenses extension
    SPEND,
    //These commands are for the friendlier syntax extension
    SET;
    private final String displayName;

    Operation(final String display) {
        this.displayName = display;
    }

    Operation() {
        this.displayName = this.toString();
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}