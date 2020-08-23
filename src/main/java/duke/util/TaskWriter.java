package duke.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/*
 * TaskWriter
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 27 Jan 2020
 *
 */

/**
 * <p>TaskWriter class abstracts the output method
 * to write the task to the file whenever there is a
 * change.</p>
 * @author Mario Lorenzo
 */

public class TaskWriter implements IWriter<Task> {
    private String filename;

    /**
     * Constructs a TaskWriter instance to write
     * into a particular file.
     * @param filename The name of the file where TaskWriter is expected to write.
     */

    public TaskWriter(String filename) {
        this.filename = filename;
    }

    /**
     * Writes the task to the file whenever there is a change
     * from the client.
     * @param task The task that can be written.
     * @param isAppendMode A boolean to determine whether the writer is in append mode.
     * @throws IOException If there is an error in I/O.
     */

    public void write(Task task, boolean isAppendMode) throws IOException {
        FileOutputStream fos;
        if (isAppendMode) {
            fos = new FileOutputStream(new File(filename), true);
        } else {
            fos = new FileOutputStream(new File(filename));
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        String[] dataArray = new String[3];

        dataArray[0] = getCommandString(task);
        dataArray[1] = getStatus(task);
        dataArray[2] = getDetails(task);

        String taskString = String.join(" | ", dataArray);
        if (isAppendMode) {
            writer.newLine();
        }

        writer.write(taskString);
        writer.close();
    }

    /**
     * Gets the String of the type from a task.
     * @param task The corresponding task.
     * @return The String representing the type of task.
     */

    private String getCommandString(Task task) {
        if (task instanceof Todo) {
            return "T";
        } else if (task instanceof Deadline) {
            return "D";
        } else {
            return "E";
        }
    }

    /**
     * Gets the String of the status from a task.
     * @param task The corresponding task.
     * @return The String representing the progress of the task.
     */

    private String getStatus(Task task) {
        if (task.getStatus()) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * Gets the details of the task.
     * @param task The corresponding task.
     * @return The String representing the details of the task.
     */

    private String getDetails(Task task) {
        String details = task.getDescription();
        if (task instanceof Deadline) {
            details += " | " + ((Deadline) task).getDueDate();
        } else if (task instanceof Event) {
            details += " | " + ((Event) task).getEventSchedule();
        }
        return details;
    }

    /**
     * Sets the file to blank.
     */

    public void setBlank() {
        try {
            FileOutputStream fos;
            fos = new FileOutputStream(new File(filename));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.close();

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
