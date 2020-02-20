package duke.util;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;

/**
 * Represents the storage used.
 *
 * <p>CS2103T AY19/20 Semester 2
 * Individual Duke project
 * 30 Jan 2020
 *
 * @author Jel
 */
public class Storage {
    protected File file;
    protected List<Task> tasks;

    /**
     * Constructs a Storage instance given the file path of the file to be used.
     * @param filePath The file path of the file to be used.
     */
    public Storage(String filePath) {
        this.tasks = new ArrayList<>();
        File tmp = new File(filePath);
        this.file = tmp;
        if (!tmp.exists()) {
            tmp.getParentFile().mkdirs();
            try {
                tmp.createNewFile();
                this.file = tmp;
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Loads tasks from data file to Duke.
     * @throws IOException Error opening file.
     */
    public void loadTasks() throws IOException {
        FileInputStream ips = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(ips));
        String line;

        while ((line = br.readLine()) != null) {
            String[] arr = line.split(" \\| ");
            String details = arr[2];
            Task getFromDisk;

            switch (arr[0]) {
            case "T":
                getFromDisk = new Todo(details);
                break;
            case "D":
                getFromDisk = new Deadline(details, LocalDate.parse(arr[3]));
                break;
            default:
                getFromDisk = new Event(details, LocalDate.parse(arr[3]));
                break;
            }

            if (arr[1].equals("1")) {
                getFromDisk.markAsDone();
            }
            this.tasks.add(getFromDisk);
        }
        br.close();
        ips.close();
    }

    /**
     * Saves task to data file.
     * @param task The task to be saved.
     * @param isAppendMode The boolean checking if the file is to be opened in append mode.
     * @throws IOException Error opening file.
     */
    protected void saveTask(Task task, boolean isAppendMode) throws IOException {
        if (file.length() == 0) {
            isAppendMode = false;
        }
        FileOutputStream ops = new FileOutputStream(file, isAppendMode);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ops));
        String[] toSave = new String[4];
        toSave[1] = task.isDone ? "1" : "0";
        toSave[2] = task.getDescription();
        System.out.println("file length: " + file.length());
        if (task instanceof Event) {
            toSave[0] = "E";
            toSave[3] = ((Event) task).getScheduledTime().toString();
        } else if (task instanceof Deadline) {
            toSave[0] = "D";
            toSave[3] = ((Deadline) task).getDueDate().toString();
        } else {
            toSave[0] = "T";
        }

        if (isAppendMode) {
            bw.newLine();
        }
        bw.write(String.join(" | ", toSave));
        bw.close();
        ops.close();
    }

    protected void clearAllData() throws IOException {
        FileOutputStream ops = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ops));
        bw.close();
        ops.close();
    }

    protected void updateData() throws IOException {
        for (int i = 0; i < this.tasks.size(); i++) {
            saveTask(this.tasks.get(i), i != 0);
        }
    }
}
