import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    /**
     * Formats the deadline and event type commands.
     *
     * @param sentence Input line.
     * @param locOfSpace Location of space.
     * @param type Type of entry.
     * @param done Done status.
     * @return Task object of type deadline/event.
     */
    public static Task formatter(String sentence, int locOfSpace, String type, int done) throws DukeException {
        int locOfSlash = sentence.indexOf("/");
        if (locOfSlash == -1) {
            if (type.equals("deadline")) {
                DukeException ex = new DukeException(":( OOPS!!! "
                        + "Incorrect format!\n The correct format for deadline is task_name /by yyyy-MM-dd HHmm");
                throw ex;
            } else {
                DukeException ex = new DukeException(":( OOPS!!! "
                        + "Incorrect format!\n The correct format for event is task_name /at yyyy-MM-dd HHmm");
                throw ex;
            }
        }
        if ((locOfSpace + 1) >= (locOfSlash)) {
            DukeException ex = new DukeException(":( OOPS!!! "
                    + "Missing description!");
            throw ex;
        }
        String description = sentence.substring(locOfSpace + 1, locOfSlash - 1);
        String time = sentence.substring(locOfSlash + 4);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(time, format);
        Task obj;
        if (type.equals("deadline")) {
            obj = new Deadline(description, dateTime, done);
        } else {
            obj = new Event(done, description, dateTime);
        }
        return obj;
    }

    /**
     * Parses the input and returns the respective command object.
     *
     * @param saved Saved status.
     * @param sentence Input line.
     * @param done Done status.
     * @return Command.
     * @throws IOException Throws IOException.
     * @throws DukeException Throws DukeException.
     */
    static Command parse(int saved, String sentence, int done, TaskList tasks) throws IOException, DukeException {
        int locOfSpace = sentence.indexOf(" ");
        String firstWord = (locOfSpace == -1) ? sentence : sentence.substring(0, locOfSpace);
        if (firstWord.equals(sentence) && firstWord.length() == 1) {
            char ch = firstWord.charAt(0);
            boolean isDigit = Character.isDigit(ch);
            if (isDigit) {
                Help h = new Help(Integer.valueOf(firstWord));
                return h;
            } else {
                DukeException ex = new DukeException(":( OOPS!!! "
                        + "I do not know what you mean");
                throw ex;
            }
        }
        switch (firstWord) {
        case "delete":
            int num = Integer.valueOf(sentence.substring(locOfSpace + 1));
            if (num >= 0 && num <= tasks.getList().size()) {
                return new Delete(num);
            } else {
                DukeException ex = new DukeException(":( OOPS!!! "
                        + "This task number does not exist in the list");
                throw ex;
            }

        case "done":
            return new Done(sentence.substring(locOfSpace + 1));
        case "list":
            return new ListCommand();
        case "bye":
            return new Exit();
        case "find":
            return new Find(sentence.substring(locOfSpace + 1));
        case "deadline":
            if (sentence.equals("deadline")) {
                DukeException ex = new DukeException(":( OOPS!!! "
                        + "The description of a deadline cannot be empty.");
                throw ex;
            } else {
                return new Add(formatter(sentence, locOfSpace, "deadline",done),saved);
            }
        case "todo":
            if (sentence.equals("todo")) {
                DukeException ex = new DukeException(":( OOPS!!! The description of a todo cannot be empty.");
                throw ex;
            } else {
                Todo todoObj = new Todo(done,sentence.substring(locOfSpace + 1));
                return new Add(todoObj,saved);
            }
        case "event":
            if (sentence.equals("event")) {
                DukeException ex = new DukeException(":( OOPS!!! The description of an event cannot be empty.");
                throw ex;
            } else {
                return new Add(formatter(sentence, locOfSpace, "event",done),saved);
            }
        default:
            DukeException ex = new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means");
            throw ex;
        }

    }
}
