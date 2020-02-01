import java.io.*;
import java.util.ArrayList;

/**
 * A Storage object contains a <code>filePath</code>, the location that can be used to find the file storing the task
 * list or create a new file at.
 */
public class Storage {
    File file;
    ArrayList<Task> taskList;

    public Storage(String filePath) {
        this.file = new File(filePath);
        this.taskList = new ArrayList<>();
    }

    public File getFile() {
        return file;
    }

    /**
     * Returns task list stored in file on hard disk or throw an exception and create a new file to store task list.
     *
     * @return task list saved in hard disk.
     * @throws DukeException if file is not found and cannot be loaded.
     * @throws IOException if there is error reading data from a file.
     */
    public ArrayList<Task> load() throws DukeException, IOException {
        if (file.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                String[] strArr = line.split(" - ");
                switch (strArr[0]) {
                    case "T": {
                        Todo newTask = new Todo(strArr[2]);
                        taskList.add(newTask);
                        if (strArr[1].equals("1")) {
                            newTask.setDone();
                        }
                        break;
                    }
                    case "D": {
                        String[] deadlineArr = strArr[3].split(" ", 2);
                        Deadline newTask = new Deadline(strArr[2], deadlineArr[0], deadlineArr[1]);
                        taskList.add(newTask);
                        if (strArr[1].equals("1")) {
                            newTask.setDone();
                        }
                        break;
                    }
                    case "E": {
                        Event newTask = new Event(strArr[2], strArr[3]);
                        taskList.add(newTask);
                        if (strArr[1].equals("1")) {
                            newTask.setDone();
                        }
                        break;
                    }
                }
                line = br.readLine();
            }
            return taskList;
        } else {
            file.createNewFile();
            throw new DukeException(8);
        }
    }
}
