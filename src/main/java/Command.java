public class Command {
    private CommandType commandType;
    private String[] params;

    public Command(CommandType commandType,  String[] params) {
        this.commandType = commandType;
        this.params = params;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String[] getParams() {
        return params;
    }
}
