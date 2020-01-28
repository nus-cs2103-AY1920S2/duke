package Duke.Parser;

import Command.*;
import Duke.Command.*;
import Duke.DukeExceptions.DukeException;
import Duke.DukeExceptions.InvalidFormatException;
import Duke.DukeExceptions.MissingDescriptionException;
import Duke.DukeExceptions.MissingTimingException;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Todo;
import DukeExceptions.*;
import Duke.List.DukeList;
import Duke.Storage.DukeStorage;
import Tasks.*;
import Duke.UI.DukeUI;

import java.time.DateTimeException;
import java.time.LocalDate;

public class DukeParser {

    public DukeParser() {
    }

    public DukeCommand handleCommand(String inputString) throws DukeException {
        String[] splitS = inputString.split(" ");
        String command = splitS[0];
        DukeCommandEnums DukeCommand = getEnum(command);
        DukeCommand returnCommand = new DukeCommand() {
            @Override
            public void execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException {
            }
        };
        try{
            switch(DukeCommand) {
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
            }
        } catch (DukeException e) {
            throw e;
        }

        return returnCommand;
    }

    public LocalDate findDeadline(String S) throws MissingTimingException, InvalidFormatException {
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

    public String findTaskDes(String S) throws MissingDescriptionException {
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
}
