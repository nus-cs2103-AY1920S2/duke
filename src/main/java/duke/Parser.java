package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Parser class for duke.
 */
public class Parser {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Enums for task.
     */
    enum TaskEnum {
      TODO, DEADLINE, EVENT
    }

    /**
     * Parse method for reading commands.
     * @param cmd takes in a command
     * @return the command class
     */
    public static Command parse(String cmd) throws DukeException {
        try {
            if (cmd.equals("bye")) {
                return new ExitCommand();
            } else if (cmd.equals("list")) {
                return new ListCommand();
            } else if (cmd.contains("done")) {
                String which = cmd.split(" ")[1];
                return new DoneCommand(which);
            } else if (cmd.contains("delete")) {
                String wh = cmd.split(" ")[1];
                return new DeleteCommand(wh);
            } else if (cmd.contains("find")) {
                String description = cmd.split(" ")[1];
                return new FindCommand(description);
            } else {
                TaskEnum taskEnum;
                String comm = cmd.split(" ")[0];
                System.out.println(comm);
                taskEnum = TaskEnum.valueOf(comm.toUpperCase());
                switch (taskEnum) {
                case TODO:
                    String todocomm = cmd.split("todo")[1];
                    return new ToDoCommand(todocomm);
                case DEADLINE:
                    String deadlinecomm = cmd.split("deadline")[1];
                    String deadLineDescription = deadlinecomm.split("/by")[0];
                    String finishDate = deadlinecomm.split("/by")[1];
                    LocalDateTime deadlineTime = convertToDate(finishDate);
                    return new DeadLineCommand(deadlinecomm, deadLineDescription, deadlineTime);
                case EVENT:
                    String eventcomm = cmd.split("event")[1];
                    String eventDescription = eventcomm.split("/at")[0];
                    String atTime = eventcomm.split("/at")[1];
                    return new EventCommand(eventcomm, eventDescription, atTime);
                default:
                    throw new UnknownCommandException();
                }
            }
        }  catch (IllegalArgumentException ex) {
            throw new UnknownCommandException();
        } catch (DukeException ex) {
            System.out.println(ex);
        }
        return new ExitCommand();
    }

    /**
     * Converts String to local date time.
     * @param fin takes in the String of date
     * @return returns the local date time
     */
    public static LocalDateTime convertToDate(String fin) {
        fin = fin.trim();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(fin, dateFormat);
        return dateTime;
    }
}
