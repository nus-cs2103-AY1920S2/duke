import java.io.*;
import java.util.ArrayList;

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
