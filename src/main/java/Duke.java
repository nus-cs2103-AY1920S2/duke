import java.util.Scanner;

public class Duke {
    private void initialise() {
        String greeting = "Hello, I am Duke, I am your personal assistant.";
        this.print(greeting);
    }

    private void print(String string) {
        System.out.println("    ------------------\n    " + string + "\n    ------------------");
    }

    private void getCommands() {
        Parser parser = new Parser();
        Scanner sc =  new Scanner(System.in);
        TaskList taskList = new TaskList();

        while (true) {
            String userCommand = sc.nextLine();
            String[] userCommandArr = userCommand.split(" ");
            Command command = parser.parse(userCommandArr[0]);

            if (command == Command.EXIT_DUKE) {
                break;
            } else if (command == Command.LIST_TASKS) {
                this.print(taskList.listTasks());
            } else if (command == Command.ADD_TASK) {
                Task task = new Task(userCommand);
                taskList.addTask(task);
                this.print("added: " + task.getDescription());
            } else if (command == Command.MARK_TASK_AS_DONE) {
                int taskIndex = Integer.parseInt(userCommandArr[1]) - 1;
                Task task = taskList.markAsDone(taskIndex);
                this.print("Marked as done:\n      " + task.getDescriptionWithIsDone());
            }
        }
        sc.close();
    }

    private void exit() {
        this.print("Bye!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.initialise();

        duke.getCommands();

        duke.exit();
    }
}
