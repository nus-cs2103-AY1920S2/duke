import java.util.Scanner;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    public static int pendingTask = 0;
    public static Scanner sc = new Scanner(System.in);

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println("error somewhere");
        }
    }

    public void run() throws IOException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line = "__________________________";
        System.out.println(line + "\nWhat can i do for you?\n" + line);

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                storage.save(tasks);
                break;
            } else if (input.equals("list")) {
                tasks.list();
            } else if (input.contains("done")) {
                try {
                    int taskNum = Integer.parseInt(input.substring(5));
                    tasks.done(taskNum);
                } catch (Exception e) {
                    System.out.println ("Please state a task number:)");
                }
            } else if (input.contains("delete")) {
                //Delete task
                int taskNum = Integer.parseInt(input.substring(7));
                tasks.delete(taskNum);
            } else {
                //Create task using key words: "todo", "deadline", "event"
                if (input.contains("todo")) {
                    //todo request format: todo<space><task>
                    tasks.addTask("T", input);
                } else if (input.contains("deadline")) {
                    //deadline request format: deadline<space><task></><yyyy-mm-dd>
                    tasks.addTask ("D", input);
                } else if (input.contains("event")) {
                    //event request format: event<space><task></><yyyy-mm-dd><T><hh:mm-hh:mm>
                    tasks.addTask ("E", input);
                } else {
                    //must have todo/deadline/event request format
                    System.out.println("Im sorry, but I do not understand what this means:-(");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}