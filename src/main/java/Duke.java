import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I 'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________");

        Scanner myScanner = new Scanner(System.in);

        while (true) {
            String nextWord = myScanner.next();
            System.out.println("________________________________________");

            if (nextWord.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("________________________________________");
                break;
            }


            System.out.println(nextWord);
            System.out.println("________________________________________");

        }
    }
}
