import java.util.Scanner;

public class Duke {
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public void echo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello I am\n" + this.logo + "\nWhat can I do for you?");

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
