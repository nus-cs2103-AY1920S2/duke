import java.util.ArrayList;
import java.util.Scanner;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone(){
        this.isDone = true;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

public class Duke {
    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<>();

        final String ExitCommand = "bye";
        final String ListCommand = "list";
        final String DoneCommand = "done";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");


        Scanner scanner = new Scanner(System.in);

        main:
        while (scanner.hasNextLine()) {
            System.out.println("\t____________________________________________________________");
            String line = scanner.nextLine().trim();
            String command = line.split(" ")[0];

            switch (command) {
                case ExitCommand:
                    System.out.println("\t Bye. Hope to see you again soon!");
                    System.out.println("\t____________________________________________________________");
                    break main;
                case ListCommand:
                    for(int i = 1; i <= tasks.size(); i++){
                        System.out.println("\t " + i + ". " + tasks.get(i-1));
                    }
                    break;
                case DoneCommand:
                    int taskId = Integer.parseInt(line.split(" ")[1]);
                    Task task = tasks.get(taskId  - 1);
                    task.markAsDone();
                    System.out.println("\t Nice! I've marked this task as done: ");
                    System.out.println("\t\t" + task);
                    break;
                default:
                    System.out.println("\t added: " + line);
                    tasks.add(new Task(line));
            }

            System.out.println("\t____________________________________________________________");
        }
    }
}
