import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The AddCommand class is used to update the current TaskList with the newly added task
 * and prints the added task to the user.
 */
class AddCommand extends Command {
    protected String command;

    AddCommand(String command) {
        this.command = command;
    }

    /**
     * The execute method of the AddCommand is used to add the task in the command given into
     * the TaskList and updates the stored TaskList in duke.txt.
     *
     * @param tasks This is the current TaskList in duke.txt.
     * @param ui The current Ui created.
     * @param storage This is used for saving of the new TaskList into duke.txt.
     * @throws IOException if file input cannot be written to or closed.
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            if (this.command.startsWith("deadline")) {
                String commandWithoutDeadline = this.command.substring(8);
                String[] commands = commandWithoutDeadline.trim().split("/by");
                try {
                    if (commandWithoutDeadline.length() > 0) {
                        LocalDate localDate = LocalDate.parse(commands[1].trim());
                        Deadline deadline = new Deadline(commands[0], localDate);
                        tasks.getTaskList().add(deadline);
                        storage.save(tasks);

                        System.out.println("\n" + "Alright, I've added this task:" + "\n");
                        System.out.println(deadline + "\n");
                        System.out.println("You currently have "
                                + tasks.getTaskList().size()
                                + " task(s) in the list.");
                    } else {
                        throw new DukeException("");
                    }
                } catch (DukeException e) {
                    ui.showLine();
                    System.out.println("There is no valid input after deadline. Please try again.");
                    ui.showLine();
                }

            } else if (this.command.startsWith("todo")) {
                String[] commands = this.command.split("todo ");
                try {
                    if (commands.length >= 2) {
                        ToDo toDo = new ToDo(commands[1]);
                        tasks.getTaskList().add(toDo);
                        storage.save(tasks);

                        System.out.println("\n" + "Alright, I've added this task:" + "\n");
                        System.out.println(toDo + "\n");
                        System.out.println("You currently have "
                                + tasks.getTaskList().size()
                                + " task(s) in the list.");
                    } else {
                        throw new DukeException("");
                    }
                } catch (DukeException e) {
                    ui.showLine();
                    System.out.println("There is no valid input after to do. Please try again.");
                    ui.showLine();
                }
            } else if (this.command.startsWith("event")) {
                String commandWithoutEvent = command.substring(5);
                String[] commands = commandWithoutEvent.trim().split("/at");
                try {
                    if (commandWithoutEvent.length() > 0) {
                        LocalDate localDate = LocalDate.parse(commands[1].trim());
                        Event event = new Event(commands[0], localDate);
                        tasks.getTaskList().add(event);
                        storage.save(tasks);

                        System.out.println("\n" + "Alright, I've added this task:" + "\n");
                        System.out.println(event + "\n");
                        System.out.println("You currently have "
                                + tasks.getTaskList().size()
                                + " task(s) in the list.");
                    } else {
                        throw new DukeException("");
                    }
                } catch (DukeException e) {
                    ui.showLine();
                    System.out.println("There is no valid input after event. Please try again.");
                    ui.showLine();
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Your date input should be in 'YYYY-MM-DD' format. Please try again.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please check your input to see if it is valid.");
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
