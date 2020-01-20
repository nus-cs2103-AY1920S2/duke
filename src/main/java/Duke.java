import main.java.Task;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> dukeList = new ArrayList<Task>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Greetings, I am\n" + logo);
        System.out.println("How may I be of assistance today?");

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("These items are in your list: \n");
                for (int i = 0; i < dukeList.size(); i++){
                    System.out.println(i + 1 + ". " + dukeList.get(i).getStatusIcon() + " "
                            + dukeList.get(i).getDescription());
                }
                command = sc.nextLine();
                continue;
            }
            if (command.contains("done")){
                String[] splitCommand = command.split(" ");
                int index = Integer.parseInt(splitCommand[1]);
                dukeList.get(index - 1).markAsDone();
                System.out.println("You have completed this task.\n");
                System.out.println(dukeList.get(index - 1));
                command = sc.nextLine();
                continue;
            }
            dukeList.add(new Task(command));
            System.out.println("added: " + command);
            command = sc.nextLine();
        }

        System.out.println("It was my pleasure to help you.\n");

    }
}
