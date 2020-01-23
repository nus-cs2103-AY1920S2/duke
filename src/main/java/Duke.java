import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList(new ArrayList<Task>());
        String line = "\n______________________________________ \n";

        System.out.println(line + "Hello! I'm Duke \nWhat can I do for you?" + line);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again soon!" + line);
                break;
            } else if (input.equals("list")) {
                System.out.println(line + taskList.toString() + line);
            } else {
                taskList.list.add(new Task(input));
                System.out.println(line + " > added: " + input + line);
            }
        }
        scanner.close();
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
    }
}
