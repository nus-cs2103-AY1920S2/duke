import java.util.Scanner;

public class Duke {



    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I'm your personal chat bot assistant! How may I be of service today?");

        Scanner sc = new Scanner(System.in);

        boolean isActive = true;
        while(isActive) {
            String command = sc.next();

            switch(command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    isActive = false;
                    break;
                default:
                    System.out.println(command);
            }
        }
    }
}
