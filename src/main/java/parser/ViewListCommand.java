package parser;

public class ViewListCommand extends Command {

    public ViewListCommand() {

    }

    @Override
    public String execute() {
        return this.taskList.toString();
    }
}

