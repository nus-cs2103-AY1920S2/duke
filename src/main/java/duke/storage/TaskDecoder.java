package duke.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import duke.exception.StorageOperationException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import static duke.common.Messages.INVALID_ENCODING_MSG;

public class TaskDecoder {
    static TaskList decodeTasksList(List<String> taskListInString) throws StorageOperationException {
        List<Task> decodedTasksList = new ArrayList<>();
        for (String taskInString : taskListInString) {
            decodedTasksList.add(parseTaskFromString(taskInString));
        }
        return new TaskList(decodedTasksList);
    }


    private static Task parseTaskFromString(String encodedTask) throws StorageOperationException {
        String[] tokens = encodedTask.split(",");

        Task task;
        try {
            String taskType = tokens[0];
            String description = tokens[2];

            // Create task accordingly.
            switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                task = new Deadline(description, LocalDateTime.parse(tokens[3]));
                break;
            case "E":
                task = new Event(description, LocalDateTime.parse(tokens[3]));
                break;
            default:
                // Wrong format
                throw new StorageOperationException(INVALID_ENCODING_MSG);
            }

            // Check if task is marked as done (only allow the value of 0 and 1).
            int doneValue = Integer.parseInt(tokens[1]);
            if (doneValue == 1) {
                task.markAsDone();
            } else if (doneValue != 0) {
                // Wrong format
                throw new StorageOperationException(INVALID_ENCODING_MSG);
            }
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            // Wrong format
            throw new StorageOperationException(INVALID_ENCODING_MSG);
        }
        return task;
    }
}
