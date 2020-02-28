import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Parser. The Parser class deals with making sense of the user command and running the command.
 */
public class Parser {
    /**
     * The user interface.
     */
    private Ui ui;
    /**
     * The list of task.
     */
    private TaskList tasks;
    /**
     * The storage object used.
     */
    private Storage storage;

    /**
     * Creates a new Parser with the given ui, tasklist and storage.
     */
    public Parser(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Greets and runs the user command line by line.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.greet();
        while (scanner.hasNextLine()) {
            String usercommand = scanner.nextLine();
            try {
                handleInstruction(usercommand);
            } catch (DukeException e) {
                ui.showCommandError(e);
            }
        }
    }

    /**
     * Deals with the user command (instruction).
     * @throws DukeException when the instruction has an invalid task type,
     *                       or the task type is specified but content is empty.
     */
    public void handleInstruction(String instruction) throws DukeException {
        if (instruction.equals("bye")) {
            ui.exit();
        } else if (instruction.equals("list")) {
            tasks.printList();
        } else if (instruction.split(" ")[0].equals("done")) {
            tasks.doneTask(Integer.parseInt(instruction.split(" ")[1]));
            storage.record(tasks.getTasks());
        } else if (instruction.split(" ")[0].equals("delete")) {
            tasks.deleteTask(Integer.parseInt(instruction.split(" ")[1]));
            storage.record(tasks.getTasks());
        } else if (instruction.split(" ")[0].equals("todo")) {
            String task = instruction.replace("todo", "");
            if (!task.equals("")) {
                tasks.addTask(new ToDo(task));
                storage.record(tasks.getTasks());
            } else {
                throw new DukeException("EmptyToDo");
            }
        } else if (instruction.split(" ")[0].equals("deadline")) {
            String task = instruction.split("/")[0].replace("deadline", "");
            if (!task.equals("")) {
                String time = instruction.split("/")[1].replace("by ", "");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                tasks.addTask(new Deadline(task, LocalDateTime.parse(time, formatter)));
                storage.record(tasks.getTasks());
            } else {
                throw new DukeException("EmptyDeadline");
            }
        } else if (instruction.split(" ")[0].equals("event")) {
            String task = instruction.split("/")[0].replace("event", "");
            if (!task.equals("")) {
                String time = instruction.split("/")[1].replace("at ", "");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                tasks.addTask(new Event(task, LocalDateTime.parse(time, formatter)));
                storage.record(tasks.getTasks());
            } else {
                throw new DukeException("EmptyEvent");
            }
        } else {
            throw new DukeException("invalid");
        }
    }

}
