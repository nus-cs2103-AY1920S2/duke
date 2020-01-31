package dukeparser;

import dukecommand.*;
import dukeexceptions.DukeException;
import dukeexceptions.InvalidFormatException;
import dukeexceptions.MissingDescriptionException;
import dukeexceptions.MissingTimingException;
import duketasks.Deadline;
import duketasks.Event;
import duketasks.Todo;
import dukelist.DukeList;
import dukestorage.DukeStorage;
import dukeui.DukeUI;

import java.time.DateTimeException;
import java.time.LocalDate;

public class DukeParser {

    public DukeParser() {
    }

    public DukeCommand handleCommand(String inputString) throws DukeException {
        String[] splitS = inputString.split(" ");
        String command = splitS[0];
        DukeCommandEnums dukeCommand = getEnum(command);
        DukeCommand returnCommand = new DukeCommand() {
            @Override
            public void execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException {
            }

            @Override
            public boolean getIsExit() {
                return false;
            }
        };
        try {
            switch (dukeCommand) {
            case DELETE:
                int deletedIndex = Integer.parseInt(splitS[1]);
                returnCommand = new DeleteCommand(deletedIndex);
                break;
            case HELP:
                returnCommand = new HelpCommand();
                break;
            case EVENT:
                LocalDate eventAt = findDeadline(inputString);
                String eventDes = findTaskDes(inputString);
                Event newEvent = new Event(eventDes, eventAt);
                returnCommand = new AddCommand(newEvent);
                break;
            case DEADLINE:
                LocalDate deadlineBy = findDeadline(inputString);
                String deadlineDes = findTaskDes(inputString);
                Deadline newDL = new Deadline(deadlineDes, deadlineBy);
                returnCommand = new AddCommand(newDL);
                break;
            case TODO:
                String taskDes = findTaskDes(inputString);
                Todo newTask = new Todo(taskDes);
                returnCommand = new AddCommand(newTask);
                break;
            case DONE:
                int doneIndex = Integer.parseInt(splitS[1]);
                returnCommand = new DoneCommand(doneIndex);
                break;
            case LIST:
                returnCommand = new ListCommand();
                break;
            case BYE:
                returnCommand = new ByeCommand();
                break;
            case UNKNOWN:
                returnCommand = new UnknownCommand();
                break;
            default:
                System.out.println("?");
                break;
            }
        } catch (DukeException e) {
            throw e;
        }

        return returnCommand;
    }

    public LocalDate findDeadline(String inputString) throws MissingTimingException, InvalidFormatException {
        String[] curr = inputString.split("/");
        return findDeadlineHelper(curr[1]);
    }

    private LocalDate findDeadlineHelper(String inputString) throws MissingTimingException, InvalidFormatException {
        String[] help = inputString.split(" ");
        int descriptionLength = help.length;
        if (descriptionLength == 1) {
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
        } catch (DateTimeException dte) {
            throw new InvalidFormatException(dte.getMessage());
        }
    }

    public String findTaskDes(String inputString) throws MissingDescriptionException {
        if (inputString.indexOf("/") <= 0) {
            return findTaskDesHelper(inputString);
        } else {
            String[] findingDes = inputString.split("/");
            return findTaskDesHelper(findingDes[0]);
        }
    }

    private String findTaskDesHelper(String inputString) throws MissingDescriptionException {
        String[] help = inputString.split(" ");
        int descriptionLength = help.length;
        if (descriptionLength == 1) {
            throw new MissingDescriptionException("Hey! Your command doesn't have a description! Please try again");
        } else {
            String output = help[1];

            for (int x = 2; x < descriptionLength; x++) {
                output +=  " " + help[x];
            }

            return output;
        }
    }

    private DukeCommandEnums getEnum(String enumString) {
        if (enumString.equals("bye")) {
            return DukeCommandEnums.BYE;
        } else if (enumString.equals("list")) {
            return DukeCommandEnums.LIST;
        } else if (enumString.equals("done")) {
            return DukeCommandEnums.DONE;
        } else if (enumString.equals("todo")) {
            return DukeCommandEnums.TODO;
        } else if (enumString.equals("deadline")) {
            return DukeCommandEnums.DEADLINE;
        } else if (enumString.equals("event")) {
            return DukeCommandEnums.EVENT;
        } else if (enumString.equals("help")) {
            return DukeCommandEnums.HELP;
        } else if (enumString.equals("delete")) {
            return DukeCommandEnums.DELETE;
        } else {
            return DukeCommandEnums.UNKNOWN;
        }
    }
}
