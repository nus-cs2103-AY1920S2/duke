package duke.command;

import duke.task.TaskList;

public class ListCommand extends Command {

    /**
     * Prints all tasks that are currently in the TaskList.
     *
     * @param taskList The TaskList to print tasks from.
     */
    @Override
    public void execute(TaskList taskList) {
        if (taskList.size() <= 0) {
            System.out.println("There are currently no tasks.");
            return;
        }

        String[] names = taskList.getNames();
        String output = "";
        for (int i = 0; i < names.length; ++i) {
            output += i + ". " + names[i] + "\n";
        }
        System.out.println(output);
    }
}
