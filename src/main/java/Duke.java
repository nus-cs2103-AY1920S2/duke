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
        int arrPointer = 1;
        do {
            userInput = sc.nextLine();
            if (!userInput.equals("list")) {
                arr[arrPointer] = userInput;
                arrPointer++;
                if (!userInput.equals("bye")) {
                    System.out.println("added: " + userInput);
                }

            }
            else {
                for (int i = 1; i < arrPointer; i++) {
                    System.out.println(i + ". " + arr[i]);
                }
            }
        } while (!userInput.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}
