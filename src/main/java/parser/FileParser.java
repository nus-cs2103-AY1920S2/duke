package parser;

import task.Task;
import task.Todo;
import task.Event;
import task.Deadline;

/**
 * Represents a file parser that converts between strings and command objects.
 */
public class FileParser {

    private String thisLine;

    /**
     * Constructor of the file parser.
     *
     * @param str the string stored in the file
     */
    public FileParser(String str) {
        this.thisLine = str.trim();
    }

    /**
     * Converts a string in the file to command objects.
     *
     * @return a task object depending on the content of the string
     */
    public Task lineToTask() {

        String[] words = this.thisLine.split(" \\| ");

        boolean isDone;
        isDone = words[1].trim().equals("1");

        String name = words[2].trim();
        String duration;

        String category = words[0].trim();
        switch (category) {
        case "T":
            return new Todo(name, isDone);
        case "E":
            duration = words[3];
            return new Event(name, duration, isDone);
        case "D":
            duration = words[3];
            return new Deadline(name, duration, isDone);
        default:
            return new Task(name, isDone);
        }
    }
}
