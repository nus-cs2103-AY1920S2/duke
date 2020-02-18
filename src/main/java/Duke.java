import java.util.ArrayList;

/**
 * This class is the main point of entry for this project.
 */
public class Duke {
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();

    /**
     * Constructor for the {@code Duke} class.
     */
    public Duke() {
        try {
            this.initialise();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public String greet() {
        return "Hello, I am Karen, your personal assistant.";
    }

    /**
     * Initialises Duke by reading the stored data from disk and storing the tasks
     * in {@code TaskList}.
     */
    private void initialise() throws DukeException {
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

    /**
     * Relays the user input string {@code userInput} to a {@code Parser} object and
     * uses the parsed tokens to manipulate tasks and generate a response String.
     * 
     * @param userInput the raw String input from the user
     * @return the actual String response of Duke
     */
    public String getResponse(String userInput) {
        try {
            Parser parser = new Parser();
            parser.parseUserInput(userInput.trim());
            Command command = parser.getCommand();

            if (command == Command.LIST_TASKS) {
                return this.taskList.listTasks();
            } else if (command == Command.ADD_TODO) {
                Todo todo = new Todo(parser.getDescription(), false);
                this.taskList.addTask(todo);
                this.storage.writeToDisk("T|0|" + parser.getDescription());
                return "Added: " + todo.getFullDescription() + "\n" + this.taskList.printNumTasks();
            } else if (command == Command.ADD_DEADLINE) {
                Deadline deadline = new Deadline(parser.getDescription(), parser.getIsDone(), parser.getDate());
                this.taskList.addTask(deadline);
                this.storage.writeToDisk("D|0|" + parser.getDescription() + "|" + parser.getDate().toString());
                return "Added: " + deadline.getFullDescription() + "\n" + this.taskList.printNumTasks();
            } else if (command == Command.ADD_EVENT) {
                Event event = new Event(parser.getDescription(), parser.getIsDone(), parser.getDate());
                this.taskList.addTask(event);
                this.storage.writeToDisk("E|0|" + parser.getDescription() + "|" + parser.getDate().toString());
                return "Added: " + event.getFullDescription() + "\n" + this.taskList.printNumTasks();
            } else if (command == Command.MARK_TASK_AS_DONE) {
                Task task = this.taskList.markAsDone(parser.getTaskIndex());
                this.storage.markAsDone(parser.getTaskIndex());
                return "Marked as done: " + task.getFullDescription() + "\n" + this.taskList.printNumTasks();
            } else if (command == Command.DELETE_TASK) {
                Task task = this.taskList.removeTask(parser.getTaskIndex());
                this.storage.removeTask(parser.getTaskIndex());
                return "Deleted: " + task.getFullDescription() + "\n" + this.taskList.printNumTasks();
            } else if (command == Command.FIND_TASKS) {
                return this.taskList.findTasks(parser.getDescription());
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
        assert false; // this should be unreachable, all input cases should be handled by above
        return null;
    }
}
