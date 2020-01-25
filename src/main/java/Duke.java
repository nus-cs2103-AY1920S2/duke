import java.util.Scanner;

public class Duke {
    public static String logo = ""
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private TaskList tasks;

    public Duke() {
        this.tasks = new TaskList();
    }

    public TaskList getTaskList() {
        return tasks;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from\n" + Duke.logo);
        Command command;
        do {
            command = new Command(sc.nextLine());
            try {
                String message = command.execute(this);
                System.out.println(message);
            } catch (DukeNoCommandException e) {
                continue;
            } catch (DukeException e) {
                System.out.println(e);
            }
        } while (!command.getCommandName().equals("bye") && sc.hasNextLine());
        sc.close();
    }

    public static void main(String[] args) {
        new Duke().run();
        System.exit(0);
    }
}
