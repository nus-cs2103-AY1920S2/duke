package seedu.duke.storage;

import seedu.duke.exception.InvalidDateException;
import seedu.duke.exception.InvalidInputFormatException;
import seedu.duke.task.Deadline;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;
import seedu.duke.task.Event;
import seedu.duke.ui.Ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Creates a hard disk that allows user to add, delete, or modify the content of the file.
 */
public class Storage {
    String filePath;
    List<Task> tasks;

    /**
     * Creates a hard disk to store and load the user's task list.
     *
     * @param filePath Relative path to the file that is being opened.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //@@author johannagwan-reused
    //Reused from https://www.w3schools.com/java/java_files_create.asp with minor modifications.
    /**
     * Creates new file when the data file specified in the file path does not exist.
     */
    public void makeNewFile() {
        Ui ui = new Ui();
        try {
            String newFilePath = "duke.txt";
            File newFile = new File(newFilePath);
            if (!newFile.createNewFile()) {
                ui.printDuplicateFile();
            } else { // if file is successfully created, change the file path to the new one.
                filePath = newFilePath;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the content of the hard disk into a list of tasks that user can see.
     *
     * @return A task list with content from the hard disk.
     * @throws IOException If an input or output exception occurred.
     * @throws InvalidInputFormatException If an input is keyed in a wrong format.
     * @throws InvalidDateException If a date is input in a wrong format.
     */
    public List<Task> load() throws IOException, InvalidInputFormatException, InvalidDateException {
        tasks = new ArrayList<>();
        String inputLine;
        File file = new File(filePath);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        while ((inputLine = br.readLine()) != null) {
            String[] inputs = inputLine.split("\\|", 3);
            String type = inputs[0].trim();
            String doneStatus = inputs[1].trim();
            String desc = inputs[2].trim();
            if (type.equalsIgnoreCase("T")) {
                addTodo(desc, doneStatus);
            } else if (type.equalsIgnoreCase("D")) {
                addDeadline(desc, doneStatus);
            } else if (type.equalsIgnoreCase("E")) {
                addEvent(desc, doneStatus);
            } else {
                throw new InvalidInputFormatException();
            }
        }

        Collections.sort(tasks);
        deleteAllInStorage();
        addAllToStorage(tasks);
        return tasks;
    }

    /**
     * Sorts the content inside the hard disk according to due dates and alphabetical order.
     *
     * @throws InvalidInputFormatException If an input is keyed in a wrong format.
     * @throws InvalidDateException If a date is input in a wrong format.
     * @throws IOException If an input or output exception occurred.
     */
    public void sortStorage() throws InvalidInputFormatException, InvalidDateException, IOException {
        List<Task> tasksInStorage = new ArrayList<>();
        tasksInStorage = load();
        Collections.sort(tasksInStorage);
        deleteAllInStorage();
        addAllToStorage(tasksInStorage);
    }

    /**
     * Deletes all content inside the hard disk.
     *
     * @throws IOException If an input or output exception occurred.
     */
    public void deleteAllInStorage() throws IOException {
        String data = "";
        FileOutputStream fileOutputStr = new FileOutputStream(filePath);
        fileOutputStr.write(data.getBytes());
        fileOutputStr.close();
    }

    /**
     * Adds all the sorted tasks into the hard disk.
     *
     * @param sortedTasks The list of sorted tasks.
     * @throws IOException If an input or output exception occurred.
     */
    public void addAllToStorage(List<Task> sortedTasks) throws IOException {
        for (Task task : sortedTasks) {
            addToStorage(task);
        }
    }

    /**
     * Loads a todo task into the task list.
     *
     * @param desc The details of the todo task.
     * @param doneStatus An indicator which shows whether a todo task has been completed or not.
     */
    private void addTodo(String desc, String doneStatus) {
        Task todo = new Todo(desc);
        if (doneStatus.equalsIgnoreCase("Y")) {
            todo.markAsDone();
            assert todo.isDone();
        }
        tasks.add(todo);
    }

    /**
     * Loads a deadline task into the task list.
     *
     * @param desc The details of the deadline task.
     * @param doneStatus An indicator which shows whether a deadline task has been completed or not.
     * @throws InvalidInputFormatException If an input is keyed in a wrong format.
     * @throws InvalidDateException If a date is input in a wrong format.
     */
    private void addDeadline(String desc, String doneStatus)
            throws InvalidInputFormatException, InvalidDateException {
        String[] descs = desc.split(" /by |\\|");
        if (descs.length == 1) { // invalid Deadline input format
            throw new InvalidInputFormatException();
        }

        String deadlineDesc = descs[0].trim();
        String deadlineDate = descs[1].trim();

        if (deadlineDate.length() != 10) {
            throw new InvalidDateException();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date formattedDeadlineDate = null;
        try {
            formattedDeadlineDate = dateFormat.parse(deadlineDate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        Task deadline = new Deadline(deadlineDesc, formattedDeadlineDate);

        if (doneStatus.equalsIgnoreCase("Y")) {
            deadline.markAsDone();
        }
        tasks.add(deadline);
    }

    /**
     * Loads an event task into the task list.
     *
     * @param desc The details of the event task.
     * @param doneStatus An indicator which shows whether an event task has been completed or not.
     * @throws InvalidInputFormatException If an input is keyed in a wrong format.
     * @throws InvalidDateException If a date is input in a wrong format.
     */
    private void addEvent(String desc, String doneStatus)
            throws InvalidInputFormatException, InvalidDateException {
        String[] descs = desc.split(" /at |\\|");
        if (descs.length == 1) { // invalid Event input format
            throw new InvalidInputFormatException();
        }
        String eventDesc = descs[0].trim();
        String eventDate = descs[1].trim();
        if (eventDate.length() != 10) {
            throw new InvalidDateException();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date formattedEventDate = null;
        try {
            formattedEventDate = dateFormat.parse(eventDate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        Task event = new Event(eventDesc, formattedEventDate);

        if (doneStatus.equalsIgnoreCase("Y")) {
            event.markAsDone();
        }

        tasks.add(event);
    }

    //@@author johannagwan-reused
    //Reused from http://www.java2s.com/Tutorial/Java/0120__Development/CheckifaStringisavaliddate.htm
    //with minor modifications.
    /**
     * Checks if an input date is written in a valid date format.
     *
     * @param inDate The input date.
     * @return true if the input date is written in a valid date format.
     */
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
    //@@author

    /**
     * Saves the task that the user inputs into the hard disk.
     *
     * @param task The task that user inputs which needs to be saved into the hard disk.
     * @throws IOException If an input or output exception occurred.
     */
    public void addToStorage(Task task) throws IOException {
        File file = new File(filePath);
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        String data = "";

        if (task instanceof Deadline) {
            data += "D | " + task.getStatusIcon() + " | " + task.getDescription()
                + " | " + ((Deadline) task).getDate();
        } else if (task instanceof Event) {
            data += "E | " + task.getStatusIcon() + " | " + task.getDescription()
                + " | " + ((Event) task).getDate();
        } else if (task instanceof Todo) {
            data += "T | " + task.getStatusIcon() + " | " + task.getDescription();
        }

        if (file.length() == 0) {
            bw.write(data);
        } else {
            bw.write("\n" + data);
        }

        bw.close();
        fw.close();
    }

    /**
     * Modifies the done status of a task inside the hard disk according to the index number given by the user.
     *
     * @param index The index number ot the task that is being modified.
     * @throws IOException If an input or output exception occurred.
     */
    public void changeToStorage(int index) throws IOException {
        File file = new File(filePath);
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
                assert counter > 1 : counter;
                data += "\n" + line;
            }
            counter++;
        }

        FileOutputStream fileOutputStr = new FileOutputStream(filePath);
        fileOutputStr.write(data.getBytes());
        fileOutputStr.close();
    }

    /**
     * Deletes the task in the hard disk according to the index number given by the user.
     *
     * @param index The index number ot the task that is being modified.
     * @throws IOException If an input or output exception occurred.
     */
    public void deleteInStorage(int index) throws IOException {
        File file = new File(filePath);
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
                    assert !isFirstUndeletedLine;
                    data += "\n" + line;
                }
            }
            counter++;
        }

        FileOutputStream fileOutputStr = new FileOutputStream(filePath);
        fileOutputStr.write(data.getBytes());
        fileOutputStr.close();
    }
}
