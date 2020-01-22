import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String separator_top = "\n------------------------------------------";
        String separator_btm = "------------------------------------------\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from\n" + logo);

        while (true) {
            String command = sc.next();

            if (command.toLowerCase().equals("bye")) {
                System.out.println(separator_top);
                System.out.println("Hope to see you again!");
                System.out.println(separator_btm);
                break;
            } else {
                System.out.println(separator_top);
                System.out.println(command);
                System.out.println(separator_btm);
            }
        }
    }
}
