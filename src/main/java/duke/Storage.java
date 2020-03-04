package duke;

import duke.DukeException;
import duke.expense.Expense;
import duke.expense.ExpenseList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File taskFile;
    private File expenseFile;

    /**
     * Create a new Storage object givent the folder for information saving.
     * @param saveFolder The path of the save folder.
     */
    public Storage(String saveFolder) {
        this.taskFile = new File(saveFolder + File.separator + "tasks.txt");
        this.expenseFile = new File(saveFolder + File.separator + "expenses.txt");
    }

    /**
     * Loads the tasks data from the file.
     * @return A list of tasks.
     * @throws DukeException Error when reading the file.
     */
    public ArrayList<Task> loadTask() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(taskFile);
            
            while (scanner.hasNextLine()) {
                String[] details = scanner.nextLine().split(" \\| ");
                boolean isDone = !details[1].equals("0");
                String description = details[2];

                switch (details[0]) {
                case "T":
                    tasks.add(new Todo(isDone, description));
                    break;
                case "D":
                    tasks.add(new Deadline(isDone, description,
                            LocalDateTime.parse(details[3])));
                    break;
                case "E":
                    tasks.add(new Event(isDone, description,
                            LocalDateTime.parse(details[3]),
                            LocalDateTime.parse(details[4])));
                    break;
                default:
                    assert false : "Invalid save file format.";
                    continue;
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.err.println(e);
            throw new DukeException("Problem with reading task save file.");
        }

        return tasks;
    }

    /**
     * Loads the expenses from the file.
     * @return A list of expenses.
     * @throws DukeException Error when reading the file.
     */
    public ArrayList<Expense> loadExpense() throws DukeException {
        ArrayList<Expense> expenses = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(expenseFile);

            while (scanner.hasNextLine()) {
                String[] details = scanner.nextLine().split(" \\| ");
                LocalDate date = LocalDate.parse(details[0]);
                double expense = Double.parseDouble(details[1]);
                String description = details[2];

                expenses.add(new Expense(description, expense, date));

            }
            
            scanner.close();
        } catch (Exception e) {
            System.err.println(e);
            throw new DukeException("Problem with reading expense save file.");
        }

        return expenses;
    }

    /**
     * Saves the tasks to the given file path.
     * @param tasks The tasks to be saved.
     * @throws DukeException Error when writing to the file.
     */
    public void save(TaskList tasks) throws DukeException {
        // remove existing file
        if (taskFile.exists()) {
            taskFile.delete();
        }

        // create directory parent directory of file if 
        // it does not exist
        new File(taskFile.getParent()).mkdirs();

        try {
            taskFile.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(taskFile));
            writer.write(tasks.toSaveFormat());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Problem writing into save file.");
        }
    }

    /**
     * Saves the expenses to the given file path.
     * @param expenses The expenses to be saved.
     * @throws DukeException Error when writing to the file.
     */
    public void save(ExpenseList expenses) throws DukeException {
        // remove existing file
        if (expenseFile.exists()) {
            expenseFile.delete();
        }

        // create directory parent directory of file if 
        // it does not exist
        new File(expenseFile.getParent()).mkdirs();

        try {
            expenseFile.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(expenseFile));
            writer.write(expenses.toSaveFormat());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Problem writing into save file.");
        }
    }
}