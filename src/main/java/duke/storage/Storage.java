/*
 * @author leslieharland
 */

package duke.storage;

import duke.alias.AliasList;
import duke.expense.ExpenseItem;
import duke.expense.ExpenseList;
import duke.task.Deadline;
import duke.task.EventObj;
import duke.task.SearchTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


/**
 * The Class Storage handles all the file related operations performed to
 * act on a task.
 */
public class Storage implements CheckTask {
    public String filePath;

    /**
     * Instantiates a new storage.
     *
     * @param filePath the file path
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Populate the file with the updated lists of tasks overwriting existing
     * content each time.
     *
     * @param myContent stores the string format of the task
     */
    public void writeToFile(String myContent) {
        BufferedWriter writer = null;
        try {
            FileWriter fw = new FileWriter(createDataFile());
            writer = new BufferedWriter(fw);
            writer.write(myContent);
            writer.flush();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Load tasks from the file, if the file does not exist, create it and use
     * it to store created tasks
     * Parses the tasks based on tasks type enum specified in Operation.java
     *
     * @return the task list
     */
    public TaskList loadTasks() {
        TaskList tasks = new TaskList(this);
        try {
            createDataFile();
            BufferedReader rd = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = rd.readLine()) != null) {
                String type = line.split("|")[0];
                switch (type) {
                case "E":
                    tasks.addTask(EventObj.parse(line));
                    break;
                case "D":
                    tasks.addTask(Deadline.parse(line));
                    break;
                case "T":
                    tasks.addTask(Todo.parse(line));
                    break;
                default:
                    //add code
                    break;
                }
            }
            rd.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return tasks;
    }

    /**
     * Find tasks containing the user search term.
     *
     * @return the task list
     */
    public TaskList findTasks(String... searchTerm) {
        TaskList tasks = loadTasks();
        TaskList temp = new TaskList(this);
        int pos = 1;
        for (Task task : tasks.getTasks()) {
            if (Arrays.stream(searchTerm).parallel().anyMatch(task.getDescription()::contains)) {
                temp.addTask(new SearchTask(pos, task.isDone(), task.getDescription(), task.getType()));
            }
            pos++;
        }
        return temp;
    }

    @Override
    public boolean test(String task) {
        return task.equals("T") || task.equals("E") || task.equals("D");
    }


    /**
     * Load expenses from the file.
     * Parses the tasks based on tasks type enum specified in duke.expense.Category
     *
     * @return the expense list
     */
    public ExpenseList loadExpenses() {
        ExpenseList expenseList = new ExpenseList(this);
        try {
            createDataFile();
            BufferedReader rd = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = rd.readLine()) != null) {
                expenseList.addExpense(ExpenseItem.parse(line));
            }
            rd.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return expenseList;
    }

    /**
     * Load alias from the file.
     * Retrieves all the alias from the file.
     *
     * @return the expense list
     */
    public AliasList loadAlias() {
        AliasList aliasList = new AliasList(this);
        try {
            createDataFile();
            BufferedReader rd = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = rd.readLine()) != null) {
                String[] parts = line.split(" ");

                aliasList.addAlias(parts[0], parts[1]);
            }
            rd.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return aliasList;
    }

    /**
     * If the file does not exist, create it and use it to store created alias.
     */
    public File createDataFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return file;
    }

}
