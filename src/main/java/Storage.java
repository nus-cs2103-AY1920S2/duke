import java.io.BufferedReader;
import java.io.FileReader;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.StringTokenizer;

/**
 * The class that links the data file and the project.
 * It reads from and writes the file when there are changes in the tasks.
 */
public class Storage {
    private String location;
    private BufferedReader br;

    /**
     * Constructor of Storage.
     *
     * @param filepath Location of the file that stores the tasks.
     * @throws FileNotFoundException .
     */
    public Storage(String filepath) throws FileNotFoundException {
        location = filepath;
        br = new BufferedReader(new FileReader(filepath));
    }

    /**
     * Writes all tasks in the list to the file specified.
     *
     * @param tasks The TaskList that stores an ArrayList of all tasks.
     * @throws IOException .
     */
    public void save(TaskList tasks) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(location));
        for (int i = 0; i < tasks.list.size(); i++) {
            out.write((tasks.list.get(i)).readyToSave());
            out.write("\n");
            out.flush();
        }
        out.close();
    }

    /**
     * Read previously existing tasks from the file specified.
     * Interpret the data as a list of tasks.
     *
     * @return An instance of TaskList that stores an ArrayList of all existing tasks.
     * @throws IOException .
     */
    public TaskList load() throws IOException {
        TaskList tasks = new TaskList();
        String curr = br.readLine();
        while (curr != null) {
            StringTokenizer st = new StringTokenizer(curr);
            String cmd = st.nextToken();
            st.nextToken();
            String status = st.nextToken();
            assert (cmd.equals("T")) || (cmd.equals("D")) || (cmd.equals("E"));
            assert curr.length() > 7;
            if (cmd.equals("T")) {
                Task newTask = new Task(curr.substring(7));
                if (status.equals("1")) {
                    newTask.status = "[" + "\u2713" + "]";
                }
                tasks.list.add(newTask);
            } else if (cmd.equals("D")) {
                int idx = curr.substring(7).indexOf("|") + 7;
                Deadline newTask = new Deadline(curr.substring(7,idx - 1), " (by:" + curr.substring(idx + 1) + ")");
                if (status.equals("1")) {
                    newTask.status = "[" + "\u2713" + "]";
                }
                tasks.list.add(newTask);
            } else {
                int idx = curr.substring(7).indexOf("|") + 7;
                Event newTask = new Event(curr.substring(7,idx - 1), " (at:" + curr.substring(idx + 1) + ")");
                if (status.equals("1")) {
                    newTask.status = "[" + "\u2713" + "]";
                }
                tasks.list.add(newTask);
            }
            curr = br.readLine();
        }
        br.close();
        return tasks;
    }

}
