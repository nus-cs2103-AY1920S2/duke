import java.util.Scanner;

public class Duke {

    public void echo() {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am\n" + logo + "\nWhat can I do for you?");

        Lister lister = new Lister();

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if(command.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else {
                lister.record(command);
            }
        }
    }

}
