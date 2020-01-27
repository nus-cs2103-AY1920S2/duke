package seedu.duke.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import seedu.duke.common.Messages;
import seedu.duke.exception.StorageOperationException;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.task.Todo;

/**
 * Decodes the storage file data into a TaskList object.
 */
class TaskDecoder {
    /**
     * Decodes the specified list of strings from the storage file into a decoded TaskList.
     *
     * @param taskListInString The list of strings from the storage file to be decoded into a TaskList object.
     * @return The decoded TaskList.
     * @throws StorageOperationException If the list of strings received is in an invalid format.
     */
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
                throw new StorageOperationException(Messages.INVALID_ENCODING_MSG);
            }

            // Check if task is marked as done (only allow the value of 0 and 1).
            int doneValue = Integer.parseInt(tokens[1]);
            if (doneValue == 1) {
                task.markAsDone();
            } else if (doneValue != 0) {
                // Wrong format
                throw new StorageOperationException(Messages.INVALID_ENCODING_MSG);
            }
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            // Wrong format
            throw new StorageOperationException(Messages.INVALID_ENCODING_MSG);
        }
        return task;
    }
}
