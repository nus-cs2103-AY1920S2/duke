package duke.storage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import duke.exception.DukeException;
import duke.exception.DukeFileNotFoundException;
import duke.exception.DukeCorruptedFileException;
import duke.exception.DukeReadFileException;
import duke.exception.DukeUnrecognisedTaskTypeException;
import duke.exception.DukeWriteFileException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;
import duke.utils.TaskList;

public class Storage {
    public static final String DEFAULT_FILE_PATH = "data/data.txt";

    private String filePath;

    public Storage() {
        this.filePath = Storage.DEFAULT_FILE_PATH;
    }

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public TaskList loadTaskList() throws DukeException {
        TaskList tasks = new TaskList();
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                Task newTask = parseTask(line);
                tasks.addTask(newTask);
                line = br.readLine();
            }
            br.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException(e);
        } catch (IOException e) {
            throw new DukeReadFileException(e);
        } catch (ArrayIndexOutOfBoundsException
                | DukeUnrecognisedTaskTypeException
                | DateTimeParseException e) {
            throw new DukeCorruptedFileException(filePath);
        }
    }

    private Task parseTask(String line)
            throws DukeUnrecognisedTaskTypeException, DateTimeParseException {
        String[] parts = line.split(Pattern.quote(" " + Task.DELIMITER + " "));
        char taskTypeIcon = parts[0].charAt(0);
        boolean isCompleted = parts[1].charAt(0) == Task.COMPLETED;
        String description = parts[2];
        Task newTask;
        switch (taskTypeIcon) {
        case TodoTask.ICON: {
            newTask = new TodoTask(description, isCompleted);
            break;
        }
        case DeadlineTask.ICON: {
            LocalDateTime by = LocalDateTime.parse(parts[3],
                    DeadlineTask.DATE_TIME_OUTPUT_FORMAT);
            newTask = new DeadlineTask(description, by, isCompleted);
            break;
        }
        case EventTask.ICON: {
            LocalDateTime at = LocalDateTime.parse(parts[3],
                    EventTask.DATE_TIME_OUTPUT_FORMAT);
            newTask = new EventTask(description, at, isCompleted);
            break;
        }
        default: {
            String taskTypeString = Character.toString(taskTypeIcon);
            throw new DukeUnrecognisedTaskTypeException(taskTypeString);
        }
        }
        return newTask;
    }

    public void saveTaskList(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.getTask(i);
                String line = task.toStringDelimited();
                fw.write(line + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeWriteFileException(e);
        }
    }
}