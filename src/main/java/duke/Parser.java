package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.security.InvalidKeyException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.ArrayList;

/**
 * contains methods which deal with making sense of the user command.
 */
public class Parser {
    public static HashMap<String, Message> availableMessage = new HashMap<>() {{
                put("done", Message.DONE);
                put("delete", Message.DELETE);
                put("find", Message.FIND);
                put("todo", Message.TODO);
                put("deadline", Message.DEADLINE);
                put("event", Message.EVENT);
                put("list", Message.LIST);
                put("hey", Message.HEY);
                put("tag", Message.TAG);
            }};

    /**
     * splits the string given in a specific way. (exclusive for deadline and event")
     * @param st the string being split
     * @return an array of Stings, containing parts the original String is split into.
     */
    public String[] stringSplitting(StringTokenizer st) {
        String temp = st.nextToken("/").substring(1);
        String description = temp.substring(0, temp.length() - 1);
        String byOrAt = st.nextToken("/").substring(3);
        return new String[]{description, byOrAt};
    }

    /**
     * checks whether the input for description is empty or not.
     * @param str the input from user.
     * @param length the length of the command being checked.
     * @throws EmptyDescriptionException if the description is empty.
     */
    public void checkDescriptionWhetherExit(String str, int length) throws EmptyDescriptionException {
        if (str.length() <= length + 1) {
            throw new EmptyDescriptionException("OOPS!!! The description of a duke.task cannot be empty.");
        }
    }

    /**
     * gets the corresponding duke.Message indicated from the user input String.
     * @param str the first token gotten from the user input.
     * @return a duke.Message from the user input.
     * @throws InvalidKeyException if the input does not give a valid message.
     */
    public Message getMessage(String str) throws InvalidKeyException {
        assert Parser.availableMessage.get(str) != null : "The request message entered is not valid.";

        //Lambda usage here
        return Optional.ofNullable(Parser.availableMessage.get(str))
                .orElseThrow(() -> new InvalidKeyException("OOPS!!! I'm sorry, but I don't know what that means :-("));
    }

    /**
     * gets the LocalDate object from a String.
     * @param str the given String.
     * @return The LocalDate object represented by the given String.
     */
    public LocalDate getLocalDate(String str) {
        return LocalDate.parse(str);
    }

    /**
     * parses a String containing the all the tags of a Task into a list of tags.
     * @param tagsInString the string consists of all the tags of a Task.
     * @return a list of tags of a Task.
     */
    public List<String> parseTags(String tagsInString) {
        StringTokenizer st = new StringTokenizer(tagsInString);
        List<String> tags = new ArrayList<>();
        while (st.hasMoreTokens()) {
            tags.add(st.nextToken("#").trim());
        }
        return tags;
    }

    /**
     * decode the String gotten from the data file into a Task.
     * @param data a String gotten from the data file
     * @return the Task after decoding the String.
     */
    public Task parseFromDataFile(String data) {
        StringTokenizer st = new StringTokenizer(data);
        String className = st.nextToken("~");
        String status = st.nextToken("~");
        String description = st.nextToken("~");

        Task task;

        if (className.equals("Deadline")) {
            String extra = st.nextToken("~");
            task =  new Deadline(description, getLocalDate(extra));
        } else if (className.equals("Event")) {
            String extra = st.nextToken("~");
            task = new Event(description, getLocalDate(extra));
        } else {
            task = new Todo(description);
        }

        if (status.equals("1")) {
            task.markAsDone();
        }

        if (st.hasMoreTokens()) {
            task.setTags(parseTags(st.nextToken("~")));
        }

        return task;
    }

    /**
     * extracts the request number from a String.
     * @param str the request number in the form of a String.
     * @return the integer represented by the String.
     */
    public int extractRequestNumber(String str) {
        return Integer.parseInt(str);
    }
}
