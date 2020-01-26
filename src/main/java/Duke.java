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
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while(true) {
            String readtext = scanner.nextLine();
            if(readtext.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(readtext);
            }
        }

    }
}
