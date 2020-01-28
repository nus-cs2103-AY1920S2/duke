import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

class Storage {

    private final Path FILE_PATH;

    Storage(Path filePath) {
        this.FILE_PATH = filePath;
    }

    List<String> load() throws DukeIOException {
        if (!Files.exists(FILE_PATH)) {
            throw new DukeIOException("Path does not exists.");
        }

        try {
            return Files.readAllLines(FILE_PATH);
        } catch (IOException e) {
            throw new DukeIOException(e.getMessage());
        }
    }

    void save(TaskList tasks) throws DukeIOException {
        try {
            if (!Files.exists(FILE_PATH)) {
                Files.createDirectories(FILE_PATH.getParent());
            }

            List<String> lines = tasks
                .getTasks()
                .stream()
                .map(Task::toStringForSaving)
                .collect(Collectors.toList());
            Files.write(FILE_PATH, lines, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new DukeIOException(e.getMessage());
        }
    }
}