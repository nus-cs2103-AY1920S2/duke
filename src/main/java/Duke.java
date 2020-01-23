import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("May I know your name?");
        String name = scanner.nextLine();
        ArrayList<Task> taskList = new ArrayList<>();

        while (true) {
            System.out.println("How may I help you " + name + "?");
            String command = scanner.nextLine();
            if (command.toLowerCase().equals("list")) {
                for (int currNum = 1; currNum <= taskList.size(); currNum++) {
                    System.out.println(currNum + ". " + taskList.get(currNum - 1));
                }
            }
            else if (command.toLowerCase().equals("bye")) {
                System.out.println("Adios amigo. It was my pleasure assisting you. Keep smiling " + name + ".");
                break;
            } else if (command.toLowerCase().split(" ")[0].equals("done")){
                Task toComplete = taskList.get(Integer.parseInt(command.split(" ")[1]) - 1);
                toComplete.completeStatus();
                System.out.println("Hooray! You've finally managed to finish this task:");
                System.out.println(toComplete);
            } else {
                taskList.add(new Task(command));
                System.out.println("added: " + command);
            }
        }
    }
}
