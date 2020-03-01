package duke.task;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A task to be done that has no time attribute.
 */
class TodoTask extends Task {

    /**
     * Constructs an TodoTask object.
     *
     * @param inputArr a String array that represents the input.
     */
    public TodoTask(String[] inputArr) {
        this.type = "todo";
        size++;
        this.description = Arrays.stream(inputArr)
                .map(str -> str.toLowerCase().equals("todo") ? "" : str)
                .collect(Collectors.joining(" "));

    }

    /**
     * Returns the String representation of this TodoTask object.
     *
     * @return the String representation of this TodoTask object.
     */
    @Override
    public String toString() {
        return " TODO       :" + super.toString();
    }
}