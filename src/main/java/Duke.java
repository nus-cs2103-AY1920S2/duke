import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you today?");
        System.out.println("Please tell me what to do!");
        System.out.println("\n");
        System.out.println("====================================================================================");
        Scanner sc = new Scanner(System.in);
        String exitCommand = "bye";
        ArrayList<Task> listOfText = new ArrayList<Task>();
        int counter = 1;

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.toLowerCase().equals(exitCommand)) {
                break;
            }

            if (input.toLowerCase().equals("list")) {
                for (int i = 0; i < listOfText.size(); i++) {
                    System.out.println(listOfText.get(i));
                }
                continue;
            }
            String[] splitStr = input.split("\\s+");
            if (splitStr[0].toLowerCase().equals("done")) {
                int index = Integer.parseInt(splitStr[1]) - 1;
                Task currentTask = listOfText.get(index);
                currentTask.markAsDone();
                System.out.println("Nice! I've marked this task as done and dusted:");
                System.out.println(currentTask);
                continue;

            }
            System.out.println("added: " + input);
            Task t = new Task(counter + ". " + input);
            listOfText.add(t);
            counter++;


        }

        System.out.println("\n");
        System.out.println("====================================================================================");
        System.out.println("Bye bye! Thank you for using me! Hope to see you again soon.");

    }
}
