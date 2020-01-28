package seedu.duke;

import seedu.duke.exception.DukeIoException;
import seedu.duke.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {

    private final Path filePath;

    Storage(Path filePath) {
        this.filePath = filePath;
    }

    List<String> load() throws DukeIoException {
        if (!Files.exists(filePath)) {
            throw new DukeIoException("Path does not exists.");
        }

        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new DukeIoException(e.getMessage());
        }
    }

    public void save(TaskList tasks) throws DukeIoException {
        try {
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
            }

            List<String> lines = tasks
                    .getTasks()
                    .stream()
                    .map(Task::toStringForSaving)
                    .collect(Collectors.toList());
            Files.write(filePath, lines, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new DukeIoException(e.getMessage());
        }
    }
}