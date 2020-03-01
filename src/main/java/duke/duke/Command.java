package duke;

public abstract class Command {
    String str;

    Command(String str) {
        this.str = str;
    }

    abstract String execute(TaskList task,Storage storage,Ui ui, TasksNum tasks) throws DukeException;

    boolean isExit() {
        return false;
    }

}