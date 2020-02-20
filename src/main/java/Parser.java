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
     * @return Display message.
     * @throws DukeException  If first word of input (instruction) is unrecognised.
     */
    public String parseInput(String input) throws DukeException {
        String msg = "";
        String[] inputParts = input.split(" ");
        String command = inputParts[0];
        if (input.equals("list")) {
            msg = taskList.showList();
        } else if (command.equals("done")) {
            int taskNum = Integer.parseInt(inputParts[1]);
            msg = taskList.done(taskNum);
        } else if (command.equals("todo") ||
                command.equals("event") ||
                command.equals("deadline")) {
            msg = taskList.add(input);
        } else if (command.equals("delete")) {
            int taskNum = Integer.parseInt(inputParts[1]);
            msg = taskList.delete(taskNum);
        } else if (command.equals("find")) {
            String query = inputParts[1];
            msg = taskList.find(query);
        } else if (command.equals("bye")) {
            msg = "Bye. Hope to serve you again soon!";
        } else {
            throw new UnrecognisedCommandException();
        }
        assert ! msg.equals("") : "Response is not empty";
        return msg;
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
