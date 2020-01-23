import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String separator = "---------------------------------------------------------";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you? :)\n");

        Scanner scanner = new Scanner(System.in);

        //store user input items
        String[] items = new String[100];
        int index = 0;

        String input = "";
        input = scanner.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println(separator);
                    System.out.println((i+1) + ". " + items[i]);
                    System.out.println(separator);
                }
            } else {
                items[index++] = input;

                //display reply
                System.out.println(separator);
                System.out.println("added: " + input);
                System.out.println(separator);
            }
            //next input
            input = scanner.nextLine();
        }

        // exit the app
        System.out.println(separator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separator);

    }
}
