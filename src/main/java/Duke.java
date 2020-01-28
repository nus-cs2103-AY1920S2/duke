import DukeExceptions.*;
import UI.DukeUI;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.io.IOException;

// Handles the functioning of Duke
public class Duke {
    private DukeList dl;
    private DukeStorage ds = new DukeStorage();
    private DukeUI ui;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public Duke() {
        try {
            dl = ds.load();
            ui = new DukeUI();
        } catch(IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        String command;

        while(true) {
            command = ui.readCommandString();
            handleCommand(command);
        }
    }

    public void handleCommand(String inputString) {
        String[] splitS = inputString.split(" ");
        String command = splitS[0];
        DukeCommandEnums DukeCommand = getEnum(command);

        ui.printLine();
        try{
            switch(DukeCommand) {
                case DELETE:
                    dl.delete_task(Integer.parseInt(splitS[1]));
                    break;
                case HELP:
                    System.out.println(helpMessage());
                    break;
                case EVENT:
                    LocalDate eventAt = findDeadline(inputString);
                    String eventDes = findTaskDes(inputString);
                    Event newEvent = new Event(eventDes, eventAt);
                    dl.addTask(newEvent);
                    break;
                case DEADLINE:
                    LocalDate deadlineBy = findDeadline(inputString);
                    String deadlineDes = findTaskDes(inputString);
                    Deadline newDL = new Deadline(deadlineDes, deadlineBy);
                    dl.addTask(newDL);
                    break;
                case TODO:
                    String taskDes = findTaskDes(inputString);
                    Todo newTask = new Todo(taskDes);
                    dl.addTask(newTask);
                    break;
                case DONE:
                    dl.markTaskAsDone(Integer.parseInt(splitS[1]));
                    break;
                case LIST:
                    dl.view_task();
                    break;
                case BYE:
                    System.out.println("    " + "Bye. Hope to see you again soon!");
                    break;
                case UNKNOWN:
                    throw new UnknownCommandException("Sorry! I don't understand that command.");
            }
        } catch (UnknownCommandException e) {
            System.out.println("    " + e.getMessage());
        } catch (MissingDescriptionException e) {
                System.out.println("    " + e.getMessage());
            } catch (MissingTimingException e) {
                System.out.println("    " + e.getMessage());
            } catch (EmptyListException e) {
                System.out.println("    " + e.getMessage());
            } catch (InvalidEntryException e) {
                System.out.println("    " + e.getMessage());
            } catch (InvalidFormatException ife) {
            System.out.println("    " + ife.getMessage());
        }
        ui.printLine();
    }

    public LocalDate findDeadline(String S) throws MissingTimingException, InvalidFormatException{
        String[] curr = S.split("/");
        return findDeadlineHelper(curr[1]);
    }

    private LocalDate findDeadlineHelper(String S) throws MissingTimingException, InvalidFormatException {
        String[] help = S.split(" ");
        int descriptionLength = help.length;
        if(descriptionLength == 1) {
            throw new MissingTimingException("Hey! Your deadline/event has no timing! Please re-enter with timing!");
        } else {
            String dateString = help[1];

            return parseDate(dateString);
        }
    }

    private LocalDate parseDate(String dateString) throws InvalidFormatException {
        try {
            LocalDate result = LocalDate.parse(dateString);
            return result;
        } catch(DateTimeException dte){
            throw new InvalidFormatException(dte.getMessage());
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
            throw new MissingDescriptionException("Hey! Your command doesn't have a description! Please try again");
        } else {
            String output = help[1];

            for(int x = 2; x < descriptionLength; x++) {
                output +=  " " + help[x];
            }

            return output;
        }
    }

    private DukeCommandEnums getEnum(String s) {
        if(s.equals("bye")) {
            return DukeCommandEnums.BYE;
        } else if(s.equals("list")) {
            return DukeCommandEnums.LIST;
        } else if(s.equals("done")) {
            return DukeCommandEnums.DONE;
        } else if(s.equals("todo")) {
            return DukeCommandEnums.TODO;
        } else if(s.equals("deadline")) {
            return DukeCommandEnums.DEADLINE;
        } else if(s.equals("event")) {
            return DukeCommandEnums.EVENT;
        } else if(s.equals("help")) {
            return DukeCommandEnums.HELP;
        } else if(s.equals("delete")) {
            return DukeCommandEnums.DELETE;
        } else {
            return DukeCommandEnums.UNKNOWN;
        }
    }

    private String helpMessage() {
        return "    All available commands are: \n" +
                "    1. 'help' to see all available commands.\n" +
                "    2. 'list' to see all listed tasks.\n" +
                "    3. 'todo x' where x is an event description to note a To-Do event.\n" +
                "    4. 'deadline x /by y' where is x is an event description and y is a date in YYYY-MM-DD format.\n" +
                "    5. 'event x /at y where x is an event description and y is a date in YYYY-MM-DD format.\n" +
                "    6. 'delete x where x in the task in the list you want to delete (1 -  indexed.)";
    }

}
