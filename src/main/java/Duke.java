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

        String input = "";
        input = scanner.nextLine();

        while (!input.equals("bye")) {
            System.out.println(separator);
            System.out.println(input);
            System.out.println(separator);
            input = scanner.nextLine();
        }

        // exit the app
        System.out.println(separator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separator);

    }
}
