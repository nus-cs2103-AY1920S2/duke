import java.util.Scanner;

public class Duke {
    public void echo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello I am Duke.\n" + "What can I do for you?");

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
