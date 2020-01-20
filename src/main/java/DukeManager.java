import java.util.ArrayList;
import java.util.Scanner;

// Handles the functioning of Duke
public class DukeManager {
    private static String line = "    ____________________________________________________________";
    private DukeList dl;

    public DukeManager() {
        dl = new DukeList();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while(true) {
            handleCommand(command);
            command = sc.nextLine();
        }
    }

    public void handleCommand(String inputString) {
        String[] splitS = inputString.split(" ");
        String s = splitS[0];

        String bye = "bye";
        String list = "list";
        String done = "done";

        System.out.println(line);

        if(s.equals(bye)) {
            System.out.println("    " + "Bye. Hope to see you again soon!");
        } else if (s.equals(list)) {
            dl.view_task();
        } else if (s.equals(done)) {
            dl.markTaskAsDone(Integer.parseInt(splitS[1]));
        }
        else {
            Task newTask = new Task(inputString);
            dl.add_task(newTask);
        }

        System.out.println(line);
    }

}
