import java.util.ArrayList;
import java.util.Scanner;

// Handles the functioning of Duke
public class DukeManager {
    private static String line = "    ____________________________________________________________";
    private ArrayList<String> listOfTasks;

    public DukeManager() {
        listOfTasks = new ArrayList<>();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        String bye = "bye";

        while(true) {
            printResponse(command);
            if(command.equals(bye)) {
                break;
            } else {
                command = sc.nextLine();
            }
        }
    }

    public void printResponse(String s) {
        System.out.println(line);
        if(s.equals("bye")) {
            System.out.println("    " + "Bye. Hope to see you again soon!");
        } else {
            System.out.println("    " + s);
        }
        System.out.println(line);
    }

}
