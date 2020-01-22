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
        System.out.println("What can I do for you?");
        input();
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void input() {
        Scanner sc;
        String statement;
        ArrayList<String> arrList = new ArrayList<>();

        sc = new Scanner(System.in);
        statement = sc.nextLine();

        while (!statement.equals("bye")) {
            if (statement.equals("list") && arrList.size() != 0) {
                for (int i=1; i <= arrList.size(); i++) {
                    System.out.println(i +". " + arrList.get(i-1));
                }
                System.out.println();
            }
            else if (statement.equals("list")) {
                System.out.println("Empty");
                System.out.println();
            }
            else {
                arrList.add(statement);
                System.out.println("added: " + statement);
                System.out.println();
            }
            statement = sc.nextLine();
        }
    }
}