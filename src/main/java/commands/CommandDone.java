package commands;

import exceptions.DukeException;
import main.DukeProcessor;
import tasks.Task;

public class CommandDone implements DukeCommand {

    public void execute(DukeProcessor processor, String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);
        if(argsArray.length < 2) {
            throw new DukeException("Your 'done' command is incorrect! Use the following format: done <number>");
        }
        int taskNumber = Integer.parseInt(argsArray[1]);
        int taskIndex = taskNumber - 1;

        Task selectedTask = processor.getTaskList().get(taskIndex);
        selectedTask.complete();

        System.out.println("Great job on being productive! I've marked the following task as completed:");
        System.out.println(selectedTask);

        try {
            processor.getTaskListHandler().saveTasks();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
