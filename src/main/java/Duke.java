import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] items = new String[100];
        int i = 0;

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int j = 0; j < i; j++) {
                    System.out.println(j + 1 + ". " + items[j]);
                }
            } else {
                items[i] = input;
                i++;
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
