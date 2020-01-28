import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    /**
     * Formats the sentence.
     *
     * @param sentence Input line.
     * @param locOfSpace Location of space.
     * @param type Type of entry.
     * @param done Done status.
     * @return Task.
     */
    public static Task formatter(String sentence, int locOfSpace, String type, int done) {
        int locOfSlash = sentence.indexOf("/");
        String description = sentence.substring(locOfSpace + 1, locOfSlash - 1);
        String time = sentence.substring(locOfSlash + 4);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(time, format);
        Task obj;
        if (type.equals("deadline")) {
            obj = new Deadline(description, dateTime,done);
        } else {
            obj = new Event(done,description,dateTime);
        }
        return obj;
    }

    /**
     * Parses the sentence.
     *
     * @param saved Saved status.
     * @param sentence Input line.
     * @param done Done status.
     * @return Command.
     * @throws IOException Throws IOException.
     * @throws DukeException Throws DukeException.
     */
    static Command parse(int saved, String sentence, int done) throws IOException, DukeException {
        int locOfSpace = sentence.indexOf(" ");
        String firstWord = (locOfSpace == -1) ? sentence : sentence.substring(0, locOfSpace);
        switch (firstWord) {
        case "delete":
            return new Delete(sentence.substring(locOfSpace + 1));
        case "done":
            return new Done(sentence.substring(locOfSpace + 1));
        case "list":
            return new ListCommand();
        case "bye":
            return new Exit();
        case "deadline":
            if (sentence.equals("deadline")) {
                DukeException ex = new DukeException(":( OOPS!!! "
                        + "The description of a deadline cannot be empty."); //change
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
            DukeException ex = new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
            throw ex;
        }

    }
}
