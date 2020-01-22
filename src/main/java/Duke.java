import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("My name is Jarvis!\nHow may I provide my services on this fine day?");

        Scanner sc = new Scanner(System.in);
        String nextLine = sc.nextLine();
        while (!nextLine.equals("bye")) {
            System.out.println(nextLine);
            nextLine = sc.nextLine();
        }
        System.out.println("Hope my service has been of great help! See you again!");
    }
}
