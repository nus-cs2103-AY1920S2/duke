package duke;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Storage manages reading and writing of data from and to txt file.
 */
public class Storage {
    protected String filePath;

    /**
     * Takes in path of txt tile.
     *
     * @param filePath of stored data
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads data from txt file and put into ArrayList.
     *
     * @return Data of ArrayList stored
     */
    public ArrayList<Task> load() {
        File file = new File(this.filePath);
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            if (file.length() != 0) {
                FileInputStream fin = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fin));
                String task = br.readLine();

                while (task != null) {
                    String[] temp = task.split("/");
                    Task newTask = new Task("");

                    if (temp[2].equals("T")) {
                        newTask = new ToDo(temp[1]);

                    } else if (temp[2].equals("D")) {
                        LocalDate localDate = LocalDate.parse(temp[3]);
                        newTask = new Deadline(temp[1], localDate);

                    } else if (temp[2].equals("E")) {
                        LocalDateTime localDateTime = LocalDateTime.parse(temp[3]);
                        newTask = new Event(temp[1], localDateTime);
                    }

                    if (temp[0].equals("\u2713")) {
                        newTask.isDone = true;
                    }

                    taskList.add(newTask);
                    task = br.readLine();
                }
            }

        } catch (Exception ex) {
            Ui ui = new Ui();
            ui.showLoadingError();
        }

        return taskList;
    }

    /**
     * Stores TaskList into txt file.
     *
     * @param taskList of data to be stored
     */
    public void store(TaskList taskList) {
        try {
            FileOutputStream fo = new FileOutputStream(this.filePath);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fo));

            for (int i = 0; i < taskList.tasks.size(); i++) {
                Task task = taskList.tasks.get(i);
                String save = task.getStatusIcon() + "/" + task.description + "/";

                if (task instanceof ToDo) {
                    save += "T";
                } else if (task instanceof Deadline) {
                    save += "D" + "/" + ((Deadline) task).by;
                } else if (task instanceof Event) {
                    save += "E" + "/" + ((Event) task).at;
                }

                bw.write(save);
                bw.newLine();
            }

            bw.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
