import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static List list = new ArrayList<>();
    private static String horizontalLine = "__________________________________________";
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I am \n" + logo + "\n" + "What can I do for you?");

        while (scan.hasNextLine()) {
            String command = scan.nextLine();
            Task task = new Task(command);
            if (command.equals("bye")) {
                System.out.println(horizontalLine
                        + "\n"
                        + "Bye. Hope to see you again soon!"
                        + "\n"
                        + horizontalLine);
                break;
            } else {
                System.out.println(task);
            }
        }
    }

}
