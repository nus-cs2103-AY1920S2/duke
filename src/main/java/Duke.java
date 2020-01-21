import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static List<Task> list = new ArrayList<>();
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
            String[] arrOfCommands = command.split(" ");
            Task task = new Task(command);

            if (command.equals("bye")) {
                System.out.println(horizontalLine
                        + "\n"
                        + "Bye-bye! See you again, my friend!"
                        + "\n"
                        + horizontalLine);
                break;

            } else if (command.equals("list")) {
                System.out.println("list" + "\n" + horizontalLine + "\n" + "Here are your tasks!");
                for (Task t : list) {
                    System.out.println(t);
                }
                System.out.println(horizontalLine);

            } else if (arrOfCommands[0].equals("done")) {
                int num = Integer.parseInt(arrOfCommands[1]);
                Task doneTask = list.get(num-1);
                System.out.println(horizontalLine + "\n" + "Fantastic! This task is a done-deal!" + "\n");
                doneTask.markAsDone();
                System.out.println(doneTask + "\n" + horizontalLine);
            } else {
                list.add(task);
                System.out.println(command + "\n" + horizontalLine);
                System.out.println("added: " + command + "\n" + horizontalLine);
            }
        }
    }

}
