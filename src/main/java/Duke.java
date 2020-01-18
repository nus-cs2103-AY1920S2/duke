import java.util.Scanner;

public class Duke {

    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
    public static void main(String[] args) {
        printLogo();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.equals("bye")) {
                System.out.println("End program.")
                break;
            } else {
                System.out.println(cmd);
            }
        }
    }
}