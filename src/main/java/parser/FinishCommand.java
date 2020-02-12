package parser;

public class FinishCommand extends Command {
    private Integer position;

    FinishCommand(Integer position) {
        this.position = position;
    }

    public String execute() {
        return this.taskList.markTaskAsDone(position);
    }
}
