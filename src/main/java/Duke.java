import java.util.Scanner;

public class Duke {
    private void initialise() {
        String greeting = "Hello, I am Duke " + new String(Character.toChars(0x1F481)) +", your personal assistant.";
        this.print(greeting);
    }

    private void print(String string) {
        System.out.println("    ------------------\n    " + string + "\n    ------------------");
    }

    private void getCommands() {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();

        while (true) {
            String userInput = sc.nextLine();
            try {
                Parser parser = new Parser(userInput);
                Command command = parser.getCommand();

                if (command == Command.EXIT_DUKE) {
                    break;
                } else if (command == Command.LIST_TASKS) {
                    this.print(taskList.listTasks());
                } else if (command == Command.ADD_TODO) {
                    Todo todo = new Todo(parser.getDescription());
                    taskList.addTask(todo);
                    this.print("Added: " + todo.getFullDescription() + "\n    " + taskList.printNumTasks());
                } else if (command == Command.ADD_DEADLINE) {
                    Deadline deadline = new Deadline(parser.getDescription(), parser.getBy());
                    taskList.addTask(deadline);
                    this.print("Added: " + deadline.getFullDescription() + "\n    " + taskList.printNumTasks());
                } else if (command == Command.ADD_EVENT) {
                    Event event = new Event(parser.getDescription(), parser.getAt());
                    taskList.addTask(event);
                    this.print("Added: " + event.getFullDescription() + "\n    " + taskList.printNumTasks());
                } else if (command == Command.MARK_TASK_AS_DONE) {
                    Task task = taskList.markAsDone(parser.getTaskIndex());
                    this.print("Marked as done: " + task.getFullDescription() + "\n    " + taskList.printNumTasks());
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

        duke.getCommands();

        duke.exit();
    }
}
