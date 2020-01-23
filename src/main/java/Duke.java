import java.util.*;

public class Duke {
    public static void main(String[] args) {
/*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
*/
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String[] arr = new String[100];
        String[] doneArr = new String[100];
        int arrPointer = 1;
        String doneLogo = "[✓]";
        String notDoneLogo = "[✗]";
        for (int i = 0; i < 100; i++) {
            doneArr[i] = notDoneLogo;
        }
        do {
            userInput = sc.nextLine();
            if (userInput.equals("list")) {                                                  //Lists out the tasks
                System.out.println("Here are the tasks in your list: ");
                for (int i = 1; i < arrPointer; i++) {
                    System.out.println(i + "." + doneArr[i] + " " + arr[i]);
                }
            }
            else if (userInput.split("\\s")[0].equals("done")) {
                int doneTask = Integer.parseInt(userInput.split("\\s")[1]);
                doneArr[doneTask] = doneLogo;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(doneLogo + " " + arr[doneTask]);
            }
            else {
                arr[arrPointer] = userInput;
                arrPointer++;
                if (!userInput.equals("bye")) {
                    System.out.println("added: " + userInput);
                }

            }


        } while (!userInput.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}
