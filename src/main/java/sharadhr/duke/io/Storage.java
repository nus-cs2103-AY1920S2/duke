package sharadhr.duke.io;

import sharadhr.duke.Duke;
import sharadhr.duke.parse.DateParser;
import sharadhr.duke.task.Deadline;
import sharadhr.duke.task.Event;
import sharadhr.duke.task.Task;
import sharadhr.duke.task.TaskList;
import sharadhr.duke.task.Todo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A class to handle file read/write operations by the Duke program.
 */
public class Storage {
    private Path taskFile;

    private BufferedWriter writer;

    /**
     * Creates a new {@link Storage} object for working with the data file as saved by Duke,
     * using the provided {@code filePath}.
     *
     * @param filePath a comma-separated list of the file directory structure
     */
    public Storage(String... filePath) {
        this.taskFile = Paths.get(".", filePath).normalize().toAbsolutePath();

        try {
            Files.createDirectories(this.taskFile.getParent());

            if (Files.notExists(this.taskFile)) {
                Files.createFile(this.taskFile);
            }

            this.writer = Files.newBufferedWriter(this.taskFile, StandardOpenOption.WRITE,
                                                  StandardOpenOption.APPEND);
            Files.newBufferedReader(this.taskFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a possible {@link Task} after decoding {@code line}.
     *
     * @param line A line from the file
     * @return A possible task; returns {@link Optional#empty()} if line cannot be decoded
     */
    static Optional<Task> decodeLine(String line) {
        String[] tokens = line.split(",");
        try {
            switch (tokens[0]) {
            case "T":
                return tokens.length == 3 ? Optional
                    .of(new Todo(tokens[2], Boolean.parseBoolean(tokens[1]))) : Optional.empty();
            case "D":
                return tokens.length == 4 ? Optional
                    .of(new Deadline(tokens[2], Boolean.parseBoolean(tokens[1]),
                                     DateParser.decodeString(tokens[3]))) : Optional.empty();
            case "E":
                return tokens.length == 5 ? Optional
                    .of(new Event(tokens[2], Boolean.parseBoolean(tokens[1]),
                                  DateParser.decodeString(tokens[3]),
                                  DateParser.decodeString(tokens[4]))) : Optional.empty();
            default:
                return Optional.empty();
            }
        }
        catch (Exception e) {
            Duke.output.sayError(e);
            return Optional.empty();
        }
    }

    /**
     * Encodes and appends {@code task} to this file.
     *
     * @param task the {@link Task} to be encoded and written to this file
     */
    public void appendTaskToFile(Task task) {
        try {
            writer.append(task.encode());
            writer.newLine();
            writer.flush();
        }
        catch (IOException e) {
            Output.ioException();
        }
    }

    /**
     * Returns a {@link TaskList}, parsed from the specified file associated with
     * this {@link Storage} object.
     *
     * @return the {@link Tasklist} after parsing the file on disk
     */
    public TaskList loadFromFile() {
        try {
            return new TaskList(
                Files.lines(taskFile).map(Storage::decodeLine).filter(Optional::isPresent)
                     .map(Optional::get).collect(Collectors.toList()));
        }
        catch (IOException e) {
            Output.ioException();
            Duke.output.say("File could not be read; creating new list.");
            return new TaskList();
        }
    }
}