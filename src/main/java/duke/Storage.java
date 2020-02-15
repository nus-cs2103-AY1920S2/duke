package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;
import java.util.stream.Collectors;

class Storage {
    private Path file;

    Storage(String filepath) {
        assert filepath != null;
        file = Paths.get(filepath);
    }

    static Storage empty() {
        return new EmptyStorage();
    }

    TaskList load() throws StorageException {
        try {
            return new TaskList(Files.readAllLines(file)
                    .stream()
                    .flatMap(x -> {
                            try {
                                Task task;
                                char taskType = x.charAt(1);
                                boolean isDone = (x.charAt(4) == '\u2713');
                                x = x.substring(7);
                                String[] info;
                                switch(taskType) {
                                case 'T':
                                    task = new Todo(x);
                                    break;
                                case 'D':
                                    x = x.substring(0, x.length() - 1);
                                    info = x.split(" \\(by: ");
                                    task = new Deadline(info[0],
                                            LocalDate.parse(info[1], DateTimeFormatter.ofPattern("d MMM yyyy")));
                                    break;
                                case 'E':
                                    x = x.substring(0, x.length() - 1);
                                    info = x.split(" \\(at: ");
                                    task = new Event(info[0], info[1]);
                                    break;
                                default:
                                    return Stream.empty();
                                }
                                if (isDone) {
                                    task = task.complete();
                                }
                                return Stream.of(task);
                            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                                return Stream.empty();
                            }
                        })
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            throw new StorageException("Unable to load tasks from " + file, e);
        }
    }

    void save(TaskList tasks) throws StorageException {
        try {
            Files.createDirectories(file.getParent());
            Files.write(file, tasks.asList().stream().map(Task::toString).collect(Collectors.toList()));
            assert Files.exists(file);
        } catch (IOException e) {
            throw new StorageException("Unable to save tasks to " + file, e);
        }
    }

    private static class EmptyStorage extends Storage {
        EmptyStorage() {
            super("");
        }

        @Override
        TaskList load() {
            return new TaskList();
        }

        @Override
        void save(TaskList tasks) {}
    }
}
