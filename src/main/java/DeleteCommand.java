import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The DeleteCommand inherits from Command and is used to delete tasks in the saved TaskList.
 */
class DeleteCommand extends Command {
    protected String command;

    /**
     * The constructor for DeleteCommand which takes in a String command that starts with "delete".
     * @param command command indicated by user input
     */
    DeleteCommand(String command) {
        this.command = command;
    }

    /**
     * The execute method of DeleteCommand is used to delete the specified task from
     * the list of tasks.
     *
     * @param tasks This is the saved TaskList in duke.txt.
     * @param ui This is the created Ui in Duke.
     * @param storage This is responsible for loading and saving the updated TaskList.
     * @throws IOException if file cannot be written to or closed.
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] commands = command.split(" ");
        TaskList before = tasks;
        if (!(commands[1].trim().equals("all"))) {
            List<Task> taskList = tasks.getTaskList();
            final Task toBeRemoved = taskList.get(Integer.parseInt(commands[1]) - 1);
            tasks.taskList.remove(Integer.parseInt(commands[1]) - 1);
            storage.save(tasks);
            ui.showLine();
            System.out.println("\n" + "Alright, I've removed this task:" + "\n");
            System.out.println(toBeRemoved + "\n");
        } else {
            // It is a mass deletion task
            List<Task> remainingTasks = new ArrayList<>();
            boolean error = false;

            try {
                if (commands[2].equals("todo")) {
                    for (Task task : tasks.taskList) {
                        if (!(task instanceof ToDo)) {
                            remainingTasks.add(task);
                        }
                    }
                } else if (commands[2].equals("event")) {
                    for (Task task : tasks.taskList) {
                        if (!(task instanceof Event)) {
                            remainingTasks.add(task);
                        }
                    }
                } else if (commands[2].equals("deadline")) {
                    for (Task task : tasks.taskList) {
                        if (!(task instanceof Deadline)) {
                            remainingTasks.add(task);
                        }
                    }
                } else if (commands[2].equals("done")) {
                    for (Task task : tasks.taskList) {
                        if (!(task.getStatusIcon().equals("[Y]"))) {
                            remainingTasks.add(task);
                        }
                    }
                } else {
                    error = true;
                    throw new DukeException("There was no invalid input after 'all'. Please try again.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            if (!error) {
                tasks.taskList = remainingTasks;
                storage.save(tasks);
                ui.showLine();
                System.out.println("\n" + "Alright, I've removed all " + commands[2] + " tasks." + "\n");
                System.out.println("You currently have "
                        + tasks.taskList.size()
                        + " task(s) in the list.");
            }
        }
    }

    /**
     * Returns true if the command is an ExitCommand and false otherwise.
     * @return false
     */
    @Override
    boolean isExit() {
        return false;
    }

}
