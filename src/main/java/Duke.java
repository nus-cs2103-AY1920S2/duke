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
            String command = sc.nextLine();
            String token = parser.parse(command);
            if (token == null) {
                break;
            } else if (token.equals("list")) {
                this.print(taskList.listTasks());
            } else {
                Task task = new Task(token);
                taskList.addTask(task);
                this.print("added: " + task.toString());
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
