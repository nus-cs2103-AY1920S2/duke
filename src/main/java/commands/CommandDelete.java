package commands;

import exceptions.DukeException;
import main.DukeProcessor;
import tasks.Task;

public class CommandDelete implements DukeCommand {

    public void execute(DukeProcessor processor, String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);
        if(argsArray.length < 2) {
            throw new DukeException("Your 'delete' command is incorrect! Use the following format: delete <number>");
        }

        int taskNumber = Integer.parseInt(argsArray[1]);
        int taskIndex = taskNumber - 1;

        Task selectedTask = processor.getTaskList().remove(taskIndex);
        System.out.println("Noted! I've deleted the following task:");
        System.out.println(selectedTask);
        System.out.println("You now have " + processor.getTaskList().size() + " tasks remaining!");

        try {
            processor.getTaskListHandler().saveTasks();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
