import java.util.Scanner;

public class Duke {

    //public final static String LINE = "__________________________________";
    //public final static String INDENT = "     ";

    public static void main(String[] args) {

        // declare an array to store stuff
        String[] tasks = new String[100];
        int counter = 0;
        for (int i = 0; i < 100; i++) {
            tasks[i] = "empty"; // populate with emptys
        }

        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo); */
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String user_input = sc.nextLine(); // read in the user input
        while (!user_input.toLowerCase().equals("bye")) {
            if (user_input.toLowerCase().equals("list")) {
                list(tasks);
            }
            else {
                // adding stuff to the array
                tasks[counter] = user_input;
                counter++;
                System.out.println("added: " + user_input);
            }
            user_input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void list(String[] tasks) {
        for (int i = 0; i < 100; i++) {
            if (tasks[i].equals("empty")) {
                break;
            } else {
                System.out.println(Integer.toString(i+1) + ". " + tasks[i]);
            }
        }
    }
}
