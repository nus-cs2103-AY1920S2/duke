package duke.storage;

import duke.task.Deadline;
import duke.task.EventObj;
import duke.task.TaskList;
import duke.task.Task;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;

public class Storage {
    public String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

	public void writeToFile(String mycontent) {
        BufferedWriter writer = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            writer.write(mycontent);
            writer.flush();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    TaskList loadTasks(){
        TaskList tasks = new TaskList();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader rd = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = rd.readLine()) != null) {
                String type = line.split("|")[0];
                if (type.equals("E")) {
                    tasks.addTask(EventObj.parse(line));
                } else if (type.equals("D")) {
                    tasks.addTask(Deadline.parse(line));
                } else if (type.equals("T")) {
                    tasks.addTask(Task.parse(line));
                } else {

                }
            }
            rd.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return tasks;
    }
}