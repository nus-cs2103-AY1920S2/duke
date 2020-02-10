public class UnknownCommand extends Command {

    public UnknownCommand() {
        super();
    }

    public String execute() {
        String out = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        return out;
    }
}
