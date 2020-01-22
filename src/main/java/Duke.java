import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you today homie?\n");
        Scanner io = new Scanner(System.in);
        String command = io.nextLine();
        while(!command.equals("bye")) {
            System.out.println("   -----");
            System.out.println("   " + command);
            System.out.println("   -----");
            command = io.nextLine();
        }
        System.out.println("   -----");
        System.out.println("   Bye bye friend!");
        System.out.println("   -----");
    }
}
