package duke.util;

import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Storage {
    private File saveFile;

    public Storage() {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        saveFile = new File(dir, "userdata.txt");
        if (!saveFile.exists()) {
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    public File getSave() {
        return this.saveFile;
    }

    public void saveTaskToFile(Task task) {
        FileWriter writer;
        try {
            writer = new FileWriter(saveFile, true);
            PrintWriter pw = new PrintWriter(writer);
            pw.println(task.toSaveString());
            pw.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void saveTaskListToFile(TaskList taskList) {
        FileWriter writer;
        PrintWriter pw;
        try {
            // Clear save
            writer = new FileWriter(saveFile);
            pw = new PrintWriter(writer);
            pw.print("");
            // Save from scratch
            writer = new FileWriter(saveFile, true);
            pw = new PrintWriter(writer);
            pw.print("");
            for (Task t: taskList.getList()) {
                pw.println(t.toSaveString());
            }
            pw.close();
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    public void load(TaskList taskList) {
        try {
            Scanner reader = new Scanner(saveFile);
            String data;
            while (reader.hasNextLine()) {
                data = reader.nextLine();
                taskList.addSaveStringAsTask(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
