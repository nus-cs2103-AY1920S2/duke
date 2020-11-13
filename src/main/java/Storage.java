import java.io.*;
import java.util.ArrayList;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    public String filePath;
    public Ui ui;

    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    /**
     * Appends a new task to the back of the file.
     * @param task New task specified to be added.
     */
    public void appendFile(Task task) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            String line = task.toLine();
            writer.write(line);
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            ui.showFileNotFoundError();
        }
    }

    /**
     * Rewrites the updated list of tasks to the file.
     * @param list Updated Tasklist of tasks.
     */
    public void writeFile(TaskList list) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < list.size(); i++) {
                String line = list.get(i).toLine();
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            ui.showFileNotFoundError();
        }
    }

    /**
     * Loads existing tasks from the file to a list.
     * @return ArrayList representation of the list of tasks.
     */
    public ArrayList<Task> load() throws IOException, FileNotFoundException {
        ArrayList<Task> data = new ArrayList<>();
//        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while (true) {
                String str = br.readLine();
                if (str == null) {
                    break;
                }
                String[] arr = str.split("/");
                if (arr[0].equals("T")) {
                    Todo task = new Todo(arr[2]);
                    if (arr[1].equals("1")) {
                        task.setDone();
                    }
                    data.add(task);
                } else if (arr[0].equals("D")) {
                    Deadline task = new Deadline(arr[2], arr[3]);
                    if (arr[1].equals("1")) {
                        task.setDone();
                    }
                    data.add(task);
                } else { //event
                    Event task = new Event(arr[2], arr[3]);
                    if (arr[1].equals("1")) {
                        task.setDone();
                    }
                    data.add(task);
                }
            }
//        } catch (Exception e) {
//            ui.showFileNotFoundError();
//        }
        return data;
    }

}
