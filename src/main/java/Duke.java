import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello from\n" + logo);
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println(input);
            }
        }
        System.out.println("Goodbye!");
        sc.close();
    }
}
