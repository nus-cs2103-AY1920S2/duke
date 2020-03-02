package duke.command;

import duke.Ui.Ui;
import duke.storage.TaskStorage;
import duke.task.Priority;
import duke.task.Task;
import exception.IllegalTextException;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UpdatePriorityCommand extends Command {

    private Priority priority;
    private String taskNumber;

    public UpdatePriorityCommand(String[] updatePriorityArgs) {
       this.taskNumber = updatePriorityArgs[0];
        switch (updatePriorityArgs[1]) {
            case "HIGH":
                this.priority = Priority.HIGH;
                break;
            case "MEDIUM":
                this.priority = Priority.MEDIUM;
                break;
            case "LOW":
                this.priority = Priority.LOW;
                break;
        }
        assert this.priority!=null : "Invalid priority was passed to UpdatePriorityCommand constructor";
    }

    public static void checkItemNumberValidity(String taskNumber, TaskStorage storage) throws Exception {
        try {
            int taskNumberInt= Integer.parseInt(taskNumber);
            if (taskNumberInt < 1 || taskNumberInt > storage.getTaskList().size()) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String execute(Ui ui, TaskStorage storage) throws IllegalTextException {
        try {
            checkItemNumberValidity(this.taskNumber, storage);
        } catch (Exception e) {
            throw new IllegalTextException("The specified task does not exist in the list.");
        }

        System.out.printf("priority: %s, task_number %s", this.priority, this.taskNumber);
        storage.updateTaskPriority(this.priority, Integer.parseInt(this.taskNumber));

        return String.format("The priority for task %s has been set to %s", this.taskNumber, this.priority);
    }
}

