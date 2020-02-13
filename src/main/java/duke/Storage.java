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
    private static final int TASK_TYPE_POSITION = 1;
    private static final int COMPLETION_STATUS_POSITION = 4;
    private static final int INFO_START_POSITION = 7;
    private static final int DESCRIPTION_POSITION = 0;
    private static final int TIME_POSITION = 1;

    private Path file;

    Storage(String filepath) {
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
                                char taskType = x.charAt(TASK_TYPE_POSITION);
                                boolean isDone = (x.charAt(COMPLETION_STATUS_POSITION) == '\u2713');
                                x = x.substring(INFO_START_POSITION);
                                String[] info;
                                switch(taskType) {
                                case 'T':
                                    task = new Todo(x);
                                    break;
                                case 'D':
                                    info = x.substring(0, x.length() - 1).split(" \\(by: ");
                                    task = new Deadline(info[DESCRIPTION_POSITION],
                                            LocalDate.parse(info[TIME_POSITION],
                                                DateTimeFormatter.ofPattern("d MMM yyyy")));
                                    break;
                                case 'E':
                                    info = x.substring(0, x.length() - 1).split(" \\(at: ");
                                    task = new Event(info[DESCRIPTION_POSITION], info[TIME_POSITION]);
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
