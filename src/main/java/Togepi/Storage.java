package Togepi;

import Togepi.Tasks.Deadline;
import Togepi.Tasks.Event;
import Togepi.Tasks.Task;
import Togepi.Tasks.Todo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.time.LocalDate;
import java.util.ArrayList;

/** Represents a storage that has methods to load from and save to
 *    the list of tasks from the input file.
 */
public class Storage {
    private String filepath;
    private ArrayList<Task> tasks = new ArrayList<>();
    private static ArrayList<Task> todoList = new ArrayList<>();
    private static ArrayList<Task> deadlineList = new ArrayList<>();
    private static ArrayList<Task> eventList = new ArrayList<>();
    private static ArrayList<Task> doneList = new ArrayList<>();
    private int counter = 0;
    private String line;

    /** Creates a storage object which takes in the file path
     *    of the file that contains the list of tasks.
     * @param filepath is the file path of the file that
     *     contains the list of tasks.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /** Loads the list of tasks from the input file.
     * @return the list of tasks already in the input file
     */
    public ArrayList<Task> load() {
        try { //read file
            FileReader fileReader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] temp = line.split(" - ");
                if (temp[0].contains("T")) {
                    Task task = new Todo(temp[2]);
                    todoList.add(task);
                    tasks.add(counter, task);
                    counter++;
                    if (temp[1].trim().equals("✓")) {
                        task.setIsDone();
                        doneList.add(task);
                    }
                } else if (temp[0].contains("D")) {
                    LocalDate date = LocalDate.of(yearHelper(temp[3]), monthHelper(temp[3]), dayHelper(temp[3]));
                    Task task = new Deadline(temp[2], date);
                    deadlineList.add(task);
                    tasks.add(counter, task);
                    counter++;
                    if (temp[1].trim().equals("✓")) {
                        task.setIsDone();
                        doneList.add(task);
                    }
                } else if (temp[0].contains("E")) {
                    LocalDate date = LocalDate.of(yearHelper(temp[3]), monthHelper(temp[3]), dayHelper(temp[3]));
                    Task task = new Event(temp[2], date);
                    eventList.add(task);
                    tasks.add(counter, task);
                    counter++;
                    if (temp[1].trim().equals("✓")) {
                        task.setIsDone();
                        doneList.add(task);
                    }
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /** Replaces the list of tasks in the input file with the new list of tasks
     *    that has been modified through the user commands.
     * @param toSave represents the new list of tasks that is to be written back
     *     into the input file.
     */
    public void save(ArrayList<Task> toSave) {
        try { //write file
            FileWriter fileWriter = new FileWriter(filepath, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String content = "\n";
            for (Task task : toSave) {
                content = content + task.saveToFile() + "\n";
            }
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Gets list of todos.
     * @return an arraylist of todos.
     */
    public static ArrayList<Task> getTodoList() {
        return todoList;
    }

    /** Gets list of deadlines.
     * @return an arraylist of deadlines.
     */
    public static ArrayList<Task> getDeadlineList() {
        return deadlineList;
    }

    /** Gets list of events.
     * @return an arraylist of events.
     */
    public static ArrayList<Task> getEventList() {
        return eventList;
    }

    /** Gets list of completed tasks.
     * @return an arraylist of completed tasks.
     */
    public static ArrayList<Task> getDoneList() {
        return doneList;
    }

    /** Converts string to integer represeting the day.
     * @param string contains the day
     * @return the day
     */
    public int dayHelper(String string) {
        return Integer.parseInt(string.split("-")[2]);
    }

    /** Converts string to integer represeting the month.
     * @param string contains the month
     * @return the month
     */
    public int monthHelper(String string) {
        return Integer.parseInt(string.split("-")[1]);
    }

    /** Converts string to integer represeting the year.
     * @param string contains the year
     * @return the year
     */
    public int yearHelper(String string) {
        return Integer.parseInt(string.split("-")[0]);
    }
}