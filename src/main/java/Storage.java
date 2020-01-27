import java.io.*;
import java.io.File;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileWriter;

/**
 * Storage class to represent the tasks being stored into hard drive.
 * Contains file location of saved task and the file f representing the saved tasks.
 */
public class Storage {

    private String fileLocation = "./Data/Tasks.txt"; //hard-coded relative file location of stored tasks
    private File f;

    // initialization of Storage

    /**
     * Constructor for the storage class.
     */
    public Storage() {
        this.f = new File(this.fileLocation);
    }

    /**
     * Function to check if the file exists or not.
     * @return true if it exists, else false.
     */
    public boolean existFile() {
      return this.f.exists();
    }

    /**
     * Function to retrieve all tasks from the storage.
     * @return ArrayList representing all the tasks from past interactions with Duke.
     */
    public ArrayList<Task> getTaskFromStorage() {
        ArrayList<Task> result = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.f));
            String line;
            while ((line = (reader.readLine())) != null) {
                line = line.trim();
                result.add(this.translateTask(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist, please create Tasks.txt file under the Data directory!");
        } catch (IOException e) {
            System.out.println("Something occurred trying to read the file");
        }
        return result;
    }

    /**
     * Function to translate the individual lines in
     * the text file to Tasks.
     * @param line line in the text file.
     * @return the Task associated with the line.
     */
    public Task translateTask(String line) {
        String[] contents = line.split("\\|");
        String taskType = contents[0];
        Task result;
        switch (taskType) {
            case "T":
                result = new ToDo(contents[2]); //as the file is hardcoded, the contents[1] should be the description
                break;
            case "E":
                result = new Event(contents[2], contents[3]);
                break;
            default:
                result = new Deadline(contents[2], contents[3]); // means deadline
        }
        if (contents[1].equals("1")) {
            result.setDone();
        }
        return result;
    }

    /**
     * Function to write the leftover tasks into the text file to
     * be used as a "database" of tasks.
     * @param tasks ArrayList of tasks to be saved.
     */
    public void writeToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.f);
            int counter = 0;
            String result = "";
            for (Task t : tasks) {
                result += this.convertTaskToString(t);
                if (counter != tasks.size() - 1) {
                    result += "\n"; //next line
                }
            }
            fw.write(result);
            fw.close();
        } catch(IOException e) {
            System.out.println("Error occurred saving tasks!");
        }
    }

    /**
     * Function to convert each task into text line to be
     * saved into the text file.
     * @param t Task to be written to text file.
     * @return String representation of the task.
     */
    public String convertTaskToString(Task t) {
        String taskType = t.Tasktype;
        String result = "";
        switch(taskType) {
            case "T":
                result += "T|" + this.returnDone(t) + "|" + t.getTaskname();
                break;
            case "E":
                Event e = (Event) t;
                result += "E|" + this.returnDone(t) + "|" + t.getTaskname() + "|" + e.DateTime;
                break;
            case "D":
                Deadline d = (Deadline) t;
                result += "D|" + this.returnDone(t) + "|" + t.getTaskname() + "|" + d.DateTime;
                break;
        }
        return result;
    }

    /**
     * Function to check if the given task is done or not
     * and return the correct string representation of it.
     * @param t Task to be checked.
     * @return Custom String representation of whether is it done or not.
     */
    public String returnDone(Task t) {
        if (t.getDone()) {
            return "1";
        } else {
            return "0";
        }
    }
}
