import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    protected String command;

    AddCommand(String command) {
        this.command = command;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            if (this.command.startsWith("deadline")) {
                String commandWithoutDeadline = this.command.substring(9);
                String[] commands = commandWithoutDeadline.split("/by");
                LocalDate localDate = LocalDate.parse(commands[1].trim());
                Deadline deadline = new Deadline(commands[0], localDate);
                tasks.getTaskList().add(deadline);
                storage.save(tasks);
                System.out.println("\n" + "Alright, I've added this task:" + "\n");
                System.out.println(deadline + "\n");
                System.out.println("You currently have " + tasks.getTaskList().size() + " task(s) in the list.");
            } else if (this.command.startsWith("todo")) {
                String[] commands = this.command.split("todo ");
                try {
                    if (commands.length >= 2) {
                        ToDo toDo = new ToDo(commands[1]);
                        tasks.getTaskList().add(toDo);
                        storage.save(tasks);
                        System.out.println("\n" + "Alright, I've added this task:" + "\n");
                        System.out.println(toDo + "\n");
                        System.out.println("You currently have " + tasks.getTaskList().size() + " task(s) in the list.");
                    } else {
                        throw new DukeException("");
                    }
                } catch (DukeException e) {
                    ui.showLine();
                    System.err.println("There is no valid input after to do. Please try again.");
                    ui.showLine();
                }
            } else if (this.command.startsWith("event")) {
                String commandWithoutEvent = command.substring(6);
                String[] commands = commandWithoutEvent.split("/at");
                LocalDate localDate = LocalDate.parse(commands[1].trim());
                Event event = new Event(commands[0], localDate);
                tasks.getTaskList().add(event);
                storage.save(tasks);
                System.out.println("\n" + "Alright, I've added this task:" + "\n");
                System.out.println(event + "\n");
                System.out.println("You currently have " + tasks.getTaskList().size() + " task(s) in the list.");
            }
        } catch (DateTimeParseException e) {
            System.err.println("Your date input should be in 'YYYY-MM-DD' format. Please try again.");
        }
        }
}
