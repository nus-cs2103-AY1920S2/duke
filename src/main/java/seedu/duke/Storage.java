package seedu.duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates a hard disk that allows user to add, delete, or modify the content of the file.
 */
public class Storage {
    String path;
    List<Task> tasks = new ArrayList<>();

    /**
     * Creates a hard disk to store and load the user's task list.
     *
     * @param path relative path to the file that is being opened
     * @throws IOException if an input or output exception occurred
     */
    public Storage(String path) throws IOException {
        this.path = path;
    }

    /**
     * Loads the content of the hard disk into a list of tasks that user can see.
     *
     * @return a task list with content from the hard disk
     * @throws IOException if an input or output exception occurred
     * @throws InvalidTaskInputException if an invalid task command is input
     * @throws InvalidDateException if a date is input in a wrong format
     */
    public List<Task> load() throws IOException, InvalidTaskInputException, InvalidDateException {
        String inputLine;
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        while ((inputLine = br.readLine()) != null) {
            String[] input = inputLine.split("\\|", 3);
            String type = input[0].trim();
            String doneStatus = input[1].trim();
            String desc = input[2].trim();
            if (type.equalsIgnoreCase("T")) {
                addTodo(desc, doneStatus);
            } else if (type.equalsIgnoreCase("D")) {
                addDeadline(desc, doneStatus);
            } else if (type.equalsIgnoreCase("E")) {
                addEvent(desc, doneStatus);
            } else {
                throw new InvalidTaskInputException();
            }
        }
        return tasks;
    }

    /**
     * Loads a todo task into the task list.
     *
     * @param desc the details of the todo task
     * @param doneStatus an indicator which shows whether a todo task has been completed or not
     * @throws IOException if an input or output exception occurred
     */
    public void addTodo(String desc, String doneStatus) throws IOException {
        Task todo = new Todo(desc);
        if (doneStatus.equalsIgnoreCase("Y")) {
            todo.markAsDone();
        }
        tasks.add(todo);
    }

    /**
     * Loads a deadline task into the task list.
     *
     * @param desc the details of the deadline task
     * @param doneStatus an indicator which shows whether a deadline task has been completed or not
     * @throws InvalidTaskInputException if an invalid task command is input
     * @throws InvalidDateException if a date is input in a wrong format
     */
    public void addDeadline(String desc, String doneStatus)
            throws InvalidTaskInputException, InvalidDateException {
        String[] descs = desc.split(" /by |\\|");
        if (descs.length == 1) { // invalid Deadline input format
            throw new InvalidTaskInputException();
        }

        String deadlineDesc = descs[0].trim();
        String deadlineTime = descs[1].trim();
        LocalDate formattedDeadlineTime = null;

        if (isValidDate(deadlineTime)) {
            formattedDeadlineTime = LocalDate.parse(deadlineTime);
        } else {
            throw new InvalidDateException();
        }

        Task deadline = new Deadline(deadlineDesc, formattedDeadlineTime);

        if (doneStatus.equalsIgnoreCase("Y")) {
            deadline.markAsDone();
        }
        tasks.add(deadline);
    }

    /**
     * Loads an event task into the task list.
     *
     * @param desc the details of the event task
     * @param doneStatus an indicator which shows whether an event task has been completed or not
     * @throws InvalidTaskInputException if an invalid task command is input
     * @throws InvalidDateException if a date is input in a wrong format
     */
    public void addEvent(String desc, String doneStatus)
            throws InvalidTaskInputException, InvalidDateException {
        String[] descs = desc.split(" /at |\\|");
        if (descs.length == 1) { // invalid Event input format
            throw new InvalidTaskInputException();
        }
        String eventDesc = descs[0].trim();
        String eventTime = descs[1].trim();
        LocalDate formattedEventTime = null;
        if (isValidDate(eventTime)) {
            formattedEventTime = LocalDate.parse(eventTime);
        } else {
            throw new InvalidDateException();
        }
        Task event = new Event(eventDesc, formattedEventTime);

        if (doneStatus.equalsIgnoreCase("Y")) {
            event.markAsDone();
        }

        tasks.add(event);
    }

    /**
     * Checks if an input date is written in a valid date format.
     *
     * @param inDate the input date
     * @return true if the input date is written in a valid date format
     */
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    /**
     * Saves the task that the user inputs into the hard disk.
     *
     * @param task the task that user inputs which needs to be saved into the hard disk
     * @throws IOException if an input or output exception occurred
     */
    public void addToStorage(Task task) throws IOException {
        File file = new File(path);
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        String data = "";
        data += task.getType() + " | " + task.getStatusIcon() + " | " + task.getDescription();
        if (task instanceof Deadline || task instanceof Event) {
            data += " | " + task.getTime();
        }

        bw.write("\n" + data);
        bw.close();
        fw.close();
    }

    /**
     * Modifies the done status of a task inside the hard disk according to the index number given by the user.
     *
     * @param index the index number ot the task that is being modified
     * @throws IOException if an input or output exception occurred
     */
    public void changeToStorage(int index) throws IOException {
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String data = "";
        String line = null;
        int counter = 1;
        while ((line = br.readLine()) != null) {
            if (counter == index) {
                line = line.substring(0, 4) + "Y" + line.substring(5, line.length());
            }

            if (counter == 1) {
                data += line;
            } else {
                data += "\n" + line;
            }
            counter++;
        }

        FileOutputStream fileOutputStr = new FileOutputStream(path);
        fileOutputStr.write(data.getBytes());
        fileOutputStr.close();
    }

    /**
     * Deletes the task in the hard disk according to the index number given by the user.
     *
     * @param index the index number ot the task that is being modified
     * @throws IOException if an input or output exception occurred
     */
    public void deleteInStorage(int index) throws IOException {
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String data = "";
        String line = null;
        int counter = 1;
        boolean isFirstUndeletedLine = true;
        while ((line = br.readLine()) != null) {
            if (counter != index) {
                if (isFirstUndeletedLine) {
                    data += line;
                    isFirstUndeletedLine = false;
                } else {
                    data += "\n" + line;
                }
            }
            counter++;
        }

        FileOutputStream fileOutputStr = new FileOutputStream(path);
        fileOutputStr.write(data.getBytes());
        fileOutputStr.close();
    }
}
