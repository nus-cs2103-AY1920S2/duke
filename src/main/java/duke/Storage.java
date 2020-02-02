package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    File file;
    Ui ui;

    public Storage(String filepath, Ui ui) {
        file = new File(new File(filepath).getAbsolutePath());
        this.ui = ui;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!file.createNewFile()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] task = line.split("\\|");
                    String taskType = task[0].trim();
                    boolean isDone = task[1].trim().equals("1");
                    if (taskType.equals("T")) {
                        Task todo = new Todo(task[2].trim());
                        if (isDone) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                    } else if (taskType.equals("D")) {
                        Task deadline = new Deadline(task[2].trim(), LocalDate.parse(task[3].trim(), DateTimeFormatter.ofPattern("MMM d yyyy")));
                        if (isDone) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                    } else {
                        Task event = new Event(task[2].trim(), LocalDate.parse(task[3].trim(), DateTimeFormatter.ofPattern("MMM d yyyy")));
                        if (isDone) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                    }
                }
                br.close();
            }
        } catch (Exception e) {
            ui.exceptionMessage(new DukeException("☹ OOPS!!! Error creating or loading file."));
        }
        return tasks;
    }

    public void writeToFile(String command, int taskNumber) {
        List<String> lines = new ArrayList<>();
        int i = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                if (i == taskNumber) {
                    if (command.equals("done")) {
                        line = line.substring(0, 4) + "1" + line.substring(5);
                    } else {
                        i++;
                        continue;
                    }
                }
                lines.add(line + "\n");
                i++;
            }
            br.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (String s : lines) {
                bw.write(s);
            }
            bw.close();
        } catch (Exception e) {
            ui.exceptionMessage(new DukeException("☹ OOPS!!! Error writing to file."));
        }
    }

    public void writeToFile(String data) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            ui.exceptionMessage(new DukeException("☹ OOPS!!! Error writing to file."));
        }
    }
}
