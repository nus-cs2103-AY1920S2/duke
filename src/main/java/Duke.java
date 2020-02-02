import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the main point of entry for this project.
 */
public class Duke {
    private final Scanner sc = new Scanner(System.in);
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();
    private final Ui ui = new Ui();

    private void greet() {
        String greeting = "Hello, I am Duke " + new String(Character.toChars(0x1F481)) + ", your personal assistant.";
        ui.output(greeting);
    }

    private void initialise() {
        ArrayList<String> lines = this.storage.readFromDisk();
        for (String line : lines) {
            Parser parser = new Parser();
            parser.parseDiskData(line);
            Command command = parser.getCommand();

            if (command == Command.ADD_TODO) {
                Todo todo = new Todo(parser.getDescription(), parser.getIsDone());
                this.taskList.addTask(todo);
            } else if (command == Command.ADD_DEADLINE) {
                Deadline deadline = new Deadline(parser.getDescription(), parser.getIsDone(), parser.getDate());
                this.taskList.addTask(deadline);
            } else if (command == Command.ADD_EVENT) {
                Event event = new Event(parser.getDescription(), parser.getIsDone(), parser.getDate());
                this.taskList.addTask(event);
            }
        }
    }

    private void getCommands() {
        while (true) {
            String userInput = this.sc.nextLine().trim();
            try {
                Parser parser = new Parser();
                parser.parseUserInput(userInput);
                Command command = parser.getCommand();

                if (command == Command.EXIT_DUKE) {
                    break;
                } else if (command == Command.LIST_TASKS) {
                    this.ui.output(this.taskList.listTasks());
                } else if (command == Command.ADD_TODO) {
                    Todo todo = new Todo(parser.getDescription(), false);
                    this.taskList.addTask(todo);
                    this.ui.output("Added: " + todo.getFullDescription() + "\n    " + this.taskList.printNumTasks());
                    this.storage.writeToDisk("T|0|" + parser.getDescription());
                } else if (command == Command.ADD_DEADLINE) {
                    Deadline deadline = new Deadline(parser.getDescription(), parser.getIsDone(), parser.getDate());
                    this.taskList.addTask(deadline);
                    this.ui.output(
                            "Added: " + deadline.getFullDescription() + "\n    " + this.taskList.printNumTasks());
                    this.storage.writeToDisk("D|0|" + parser.getDescription() + "|" + parser.getDate().toString());
                } else if (command == Command.ADD_EVENT) {
                    Event event = new Event(parser.getDescription(), parser.getIsDone(), parser.getDate());
                    this.taskList.addTask(event);
                    this.ui.output("Added: " + event.getFullDescription() + "\n    " + this.taskList.printNumTasks());
                    this.storage.writeToDisk("E|0|" + parser.getDescription() + "|" + parser.getDate().toString());
                } else if (command == Command.MARK_TASK_AS_DONE) {
                    Task task = this.taskList.markAsDone(parser.getTaskIndex());
                    this.ui.output(
                            "Marked as done: " + task.getFullDescription() + "\n    " + this.taskList.printNumTasks());
                    this.storage.markAsDone(parser.getTaskIndex());
                } else if (command == Command.DELETE_TASK) {
                    Task task = this.taskList.removeTask(parser.getTaskIndex());
                    this.ui.output("Deleted: " + task.getFullDescription() + "\n    " + this.taskList.printNumTasks());
                    this.storage.removeTask(parser.getTaskIndex());
                } else if (command == Command.FIND_TASKS) {
                    this.ui.output(this.taskList.findTasks(parser.getDescription()));
                }
            } catch (DukeException e) {
                this.ui.output(e.getMessage());
                continue;
            }
        }
        sc.close();
    }

    private void exit() {
        this.ui.output("Bye! " + new String(Character.toChars(0x1F44B)));
    }

    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.initialise();

        duke.greet();

        duke.getCommands();

        duke.exit();
    }
}
