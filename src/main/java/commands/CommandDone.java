package commands;

import main.DukeProcessor;
import tasks.Task;

public class CommandDone implements DukeCommand {

    public void execute(DukeProcessor processor, String args) {
        int taskNumber = Integer.parseInt(args);
        int taskIndex = taskNumber - 1;

        Task selectedTask = processor.getTaskList().get(taskIndex);
        selectedTask.complete();

        System.out.println("Great job on being productive! I've marked the following task as completed:");
        System.out.println(selectedTask);
    }
}
