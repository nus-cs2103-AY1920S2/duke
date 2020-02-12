package parser;

public class DeleteCommand extends Command {
    private Integer position;

    DeleteCommand(Integer position) {
        this.position = position;
    }

    @Override
    public String execute() {
        return this.taskList.remove(this.position);
    }
}
