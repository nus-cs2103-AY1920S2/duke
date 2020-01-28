import java.util.Scanner;

public class Duke {
    private final Scanner sc = new Scanner(System.in);
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();

    private void greet() {
        String greeting = "Hello, I am Duke " + new String(Character.toChars(0x1F481)) +", your personal assistant.";
        this.print(greeting);
    }

    private void print(String string) {
        System.out.println("    ------------------\n    " + string + "\n    ------------------");
    }

    private void initialise() {
        this.storage.readFromDisk();
    }

    private void getCommands() {
        while (true) {
            String userInput = this.sc.nextLine().trim();
            try {
                Parser parser = new Parser(userInput);
                Command command = parser.getCommand();

                if (command == Command.EXIT_DUKE) {
                    break;
                } else if (command == Command.LIST_TASKS) {
                    this.print(this.taskList.listTasks());
                } else if (command == Command.ADD_TODO) {
                    Todo todo = new Todo(parser.getDescription());
                    this.taskList.addTask(todo);
                    this.print("Added: " + todo.getFullDescription() + "\n    " + this.taskList.printNumTasks());
                    this.storage.writeToDisk("T|0|" + parser.getDescription());
                } else if (command == Command.ADD_DEADLINE) {
                    Deadline deadline = new Deadline(parser.getDescription(), parser.getBy());
                    this.taskList.addTask(deadline);
                    this.print("Added: " + deadline.getFullDescription() + "\n    " + this.taskList.printNumTasks());
                    this.storage.writeToDisk("D|0|" + parser.getDescription() + "|" + parser.getBy());
                } else if (command == Command.ADD_EVENT) {
                    Event event = new Event(parser.getDescription(), parser.getAt());
                    this.taskList.addTask(event);
                    this.print("Added: " + event.getFullDescription() + "\n    " + this.taskList.printNumTasks());
                    this.storage.writeToDisk("E|0|" + parser.getDescription() + "|" + parser.getAt());
                } else if (command == Command.MARK_TASK_AS_DONE) {
                    Task task = this.taskList.markAsDone(parser.getTaskIndex());
                    this.print("Marked as done: " + task.getFullDescription() + "\n    " + this.taskList.printNumTasks());
                } else if (command == Command.DELETE_TASK) {
                    Task task = this.taskList.removeTask(parser.getTaskIndex());
                    this.print("Deleted: " + task.getFullDescription() + "\n    " + this.taskList.printNumTasks());
                }
            } catch (DukeException e) {
                this.print(e.getMessage());
                continue;
            }
        }
        sc.close();
    }

    private void exit() {
        this.print("Bye! " + new String(Character.toChars(0x1F44B)));
    }

    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.initialise();

        duke.greet();

        duke.getCommands();

        duke.exit();
    }
}
