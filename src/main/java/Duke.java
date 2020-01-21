import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String statement;
        Scanner sc;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        sc = new Scanner(System.in);
        statement = sc.nextLine();

        while (!statement.equals("bye")) {
            System.out.println(statement);
            System.out.println();
            sc = new Scanner(System.in);
            statement = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
