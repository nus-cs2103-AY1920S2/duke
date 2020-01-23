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
        String command = splitS[0];

        String bye = "bye";
        String list = "list";
        String done = "done";
        String todo = "todo";
        String deadline = "deadline";
        String event = "event";

        System.out.println(line);

        if(command.equals(bye)) {
            System.out.println("    " + "Bye. Hope to see you again soon!");
        } else if (command.equals(list)) {
            dl.view_task();
        } else if (command.equals(done)) {
            dl.markTaskAsDone(Integer.parseInt(splitS[1]));
        } else if (command.equals(todo)) {
            String taskDes = findTaskDes(inputString);
            Todo newTask = new Todo(taskDes);
            dl.addTask(newTask);
        } else if(command.equals(deadline)){
            String deadlineBy = findDeadline(inputString);
            String deadlineDes = findTaskDes(inputString);
            Deadline newDL = new Deadline(deadlineDes, deadlineBy);
            dl.addTask(newDL);
        } else if(command.equals(event)){
            String eventAt = findDeadline(inputString);
            String eventDes = findTaskDes(inputString);
            Event newEvent = new Event(eventDes, eventAt);
            dl.addTask(newEvent);
        }
        else {
            System.out.println("placeholder");
        }

        System.out.println(line);
    }

    public String findDeadline(String S) {
        String[] curr = S.split("/");
        return findTaskDes(curr[1]);
    }

    public String findTaskDes(String S) {
        if(S.indexOf("/") <= 0) {
            return findTaskDesHelper(S);
        } else {
            String[] findingDes = S.split("/");
            return findTaskDesHelper(findingDes[0]);
        }
    }

    private String findTaskDesHelper(String S) {
        String[] help = S.split(" ");
        int descriptionLength = help.length;

        String output = help[1];

        for(int x = 2; x < descriptionLength; x++) {
            output +=  " " + help[x];
        }

        return output;
    }

}
