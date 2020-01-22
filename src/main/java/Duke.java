import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String divider = "____________________________________________________________";
        System.out.println("My name is Jarvis!\nHow may I provide my services on this fine day?\n" + divider);


        String[] tasks = new String[100];
        int i = 0;
        Scanner sc = new Scanner(System.in);
        String nextLine = sc.nextLine();
        while (!nextLine.equals("bye")) {

            // to check if 'list' service is called
            if (nextLine.equals("list")) {
                int listStart = 1;
                for (String task : tasks) {
                    if (task == null) {
                        System.out.println(divider);
                        break;
                    }
                    System.out.println(listStart + ". " + task);
                    listStart++;
                }
                nextLine = sc.nextLine();
                continue;
            }

            // normal addition of task
            tasks[i] = nextLine;
            System.out.println("added: " + nextLine);
            System.out.println(divider);
            nextLine = sc.nextLine();
            i++;
        }
        System.out.println("Hope my service has been of great help! See you again!");
    }
}