import DukeExceptions.*;
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
        String help = "help";


        System.out.println(line);
        try {
            if (command.equals(bye)) {
                System.out.println("    " + "Bye. Hope to see you again soon!");
            } else if (command.equals(list)) {
                dl.view_task();
            } else if (command.equals(done)) {
                dl.markTaskAsDone(Integer.parseInt(splitS[1]));
            } else if (command.equals(todo)) {
                String taskDes = findTaskDes(inputString);
                Todo newTask = new Todo(taskDes);
                dl.addTask(newTask);
            } else if (command.equals(deadline)) {
                String deadlineBy = findDeadline(inputString);
                String deadlineDes = findTaskDes(inputString);
                Deadline newDL = new Deadline(deadlineDes, deadlineBy);
                dl.addTask(newDL);
            } else if (command.equals(event)) {
                String eventAt = findDeadline(inputString);
                String eventDes = findTaskDes(inputString);
                Event newEvent = new Event(eventDes, eventAt);
                dl.addTask(newEvent);
            } else if (command.equals(help)) {
                System.out.println(helpMessage());
            } else {
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException e) {
            System.out.println("    Sorry! I don't understand that command!");
        } catch (MissingDescriptionException e) {
            System.out.println("    Hey! Your command doesn't have a description! Please try again.");
        } catch (MissingTimingException e) {
            System.out.println("    Hey! Your deadline/event has no timing! Please re-enter with timing!");
        }

        System.out.println(line);
    }

    public String findDeadline(String S) throws MissingTimingException{
        String[] curr = S.split("/");
        return findDeadlineHelper(curr[1]);
    }

    private String findDeadlineHelper(String S) throws MissingTimingException {
        String[] help = S.split(" ");
        int descriptionLength = help.length;
        if(descriptionLength == 1) {
            throw new MissingTimingException();
        } else {
            String output = help[1];

            for(int x = 2; x < descriptionLength; x++) {
                output +=  " " + help[x];
            }

            return output;
        }

    }

    public String findTaskDes(String S) throws MissingDescriptionException{
        if(S.indexOf("/") <= 0) {
            return findTaskDesHelper(S);
        } else {
            String[] findingDes = S.split("/");
            return findTaskDesHelper(findingDes[0]);
        }
    }

    private String findTaskDesHelper(String S) throws MissingDescriptionException{
        String[] help = S.split(" ");
        int descriptionLength = help.length;
        if(descriptionLength == 1) {
            throw new MissingDescriptionException();
        } else {
            String output = help[1];

            for(int x = 2; x < descriptionLength; x++) {
                output +=  " " + help[x];
            }

            return output;
        }
    }

    private String helpMessage() {
        return "    All available commands are: \n" +
                "    1. 'help' to see all available commands.\n" +
                "    2. 'list' to see all listed tasks.\n" +
                "    3. 'todo x' where x is an event description to note a To-Do event.\n" +
                "    4. 'deadline x /by y' where is x is an event description and y is a time period.\n" +
                "    5. 'event x /at y where x is an event description and y is a time period.";
    }

}
