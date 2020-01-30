import java.io.IOException;

public class DoneCommand extends Command {

    private int taskNum;

    DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.save(tasks);
        Task doneTask = tasks.getTaskList().get(taskNum - 1);
        System.out.println(Duke.LINE + "\n" + "Fantastic! This task is a done-deal!" + "\n");
        doneTask.markAsDone();
        System.out.println(doneTask + "\n");
    }
}
