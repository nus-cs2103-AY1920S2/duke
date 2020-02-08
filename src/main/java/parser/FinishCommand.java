package parser;

public class FinishCommand extends Command{
    protected Integer position;

    public FinishCommand(Integer position) {
        this.position = position;
    }

    public String execute() {
        String commandResult = this.taskList.markTaskAsDone(position);
        return commandResult;
    }

}
