public class EmptyCommand extends Command {

    public EmptyCommand() {
        super();
    }

    public String execute() {
        String out = "☹ OOPS!!! Please type something here.";
        return out;
    }
}
