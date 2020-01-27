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

    List<String> load() throws IOException {
        if (Files.exists(FILE_PATH)) {
            List<String> lines = Files.readAllLines(FILE_PATH);
            return lines;
        } else {
            throw new IOException();
        }
    }

    void save(List<Task> tasks) throws IOException {
        if (!Files.exists(FILE_PATH)) {
            Files.createDirectories(FILE_PATH.getParent());
        }
        List<String> lines = tasks.stream().map(Task::toStringForSaving).collect(Collectors.toList());
        Files.write(FILE_PATH, lines, StandardOpenOption.CREATE);
    }
}