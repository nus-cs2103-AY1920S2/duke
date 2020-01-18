import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String query = "What can the Duke do for you today?";
        String exitInfo = "Enter bye to exit!";
        String command = "";
        while (!command.equals("bye")) {
            System.out.println(query);
            System.out.println(exitInfo);
            command = scanner.nextLine().toLowerCase();
            System.out.println(command);
        }
        scanner.close();
    }
}
