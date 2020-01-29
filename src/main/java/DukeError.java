public enum DukeError {
    NUMBER("☛ dude, no such task exists!"), // error in accessing list, no such task exists
    INSUFFICIENT("☛ dude, elaborate more!"), // error in creation of tasks, insufficient commands
    COMMAND("☛ dude, give me a proper command!");
    private String errorMessage;

    DukeError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
