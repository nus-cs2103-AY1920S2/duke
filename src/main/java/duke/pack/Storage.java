package duke.pack;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {
    protected String filePath;
    protected File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> arrList = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String task = fileScanner.nextLine();
                String[] taskArr = task.split("\\|");
                String taskType = taskArr[0].trim();

                if (taskType.equals("T")) {
                    Task todo = new Todo(taskArr[2].trim());
                    if (taskArr[1].trim().equals("1")) {
                        todo.setDone(true);
                    }
                    arrList.add(todo);

                } else if (taskType.equals("E")) {
                    LocalDate date = LocalDate.parse(taskArr[4].trim());
                    Task event = new Event(taskArr[2].trim(), taskArr[3].trim(), date);
                    if (taskArr[1].trim().equals("1")) {
                        event.setDone(true);
                    }
                    arrList.add(event);

                } else if (taskType.equals("D")) {
                    LocalDate date = LocalDate.parse(taskArr[4].trim());
                    Task deadline = new Deadline(taskArr[2].trim(), taskArr[3].trim(), date);
                    if (taskArr[1].trim().equals("1")) {
                        deadline.setDone(true);
                    }
                    arrList.add(deadline);
                }
            }

        } catch (FileNotFoundException e) {
            throw new DukeException("    Oh no, file could not be found, please check your file path!");
        }

        return arrList;
    }

    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            String text = "";

            for ( Task task: tasks.getList()) {
                text = text + task.formatForFile();
            }

            fw.write(text);
            fw.close();

        } catch (IOException e) {
            throw new DukeException("Oh no, error in saving tasks to hard disk!");
        }
    }
}
