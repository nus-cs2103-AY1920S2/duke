import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Parses user input and translates it into action. A <code>Parser</code> object is associated
 * with a <code>TaskList</code> object on which the action is applied.
 */
public class Parser {
    private TaskList taskList;

    public Parser(TaskList taskList) { this.taskList = taskList; }

    /**
     * Evaluates user input using first word (instruction).
     * Adds or removes tasks from task list according to instruction details.
     *
     * @param input User string input.
     * @throws DukeException  If first word of input (instruction) is unrecognised.
     */
    public void parseInput(String input) throws DukeException {
        if (input.equals("list")) {
            taskList.showList();
        } else if (input.split(" ")[0].equals("done")) {
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            taskList.done(taskNum);
        } else if (input.split(" ")[0].equals("todo") ||
                input.split(" ")[0].equals("event") ||
                input.split(" ")[0].equals("deadline")) {
            taskList.add(input);
        } else if (input.split(" ")[0].equals("delete")) {
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            taskList.delete(taskNum);
        } else {
            throw new DukeException();
        }
    }

    /**
     * Formats date and time from user input and returns formatted string.
     *
     * @param dayTime User input string.
     * @return Formatted date-and-time string.
     */
    public static String parseTime(String dayTime) {
        String s;
        if (dayTime.split(" ").length >= 2) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime dt = LocalDateTime.parse(dayTime, formatter);
            s = dt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate d = LocalDate.parse(dayTime, formatter);
            s = d.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        }
        return s;
    }
}
