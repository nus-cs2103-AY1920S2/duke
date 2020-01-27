package duke;

import duke.data.Calender;
import duke.exception.BadDateException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    public ArrayList<Task> printFileIntoList(Calender calender) {
        try {
            Scanner sc = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();
            while (sc.hasNext()) {
                String str = sc.nextLine();
                String[] parts = str.split("\\|");
                if (parts[0].equals("T")) {
                    Task task = new Todo(Boolean.parseBoolean(parts[1]), parts[2]);
                    tasks.add(task);
                    calender.addDate(task);
                } else if (parts[0].equals("E")) {
                    Task task = new Event(Boolean.parseBoolean(parts[1]), parts[2], parts[3]);
                    tasks.add(task);
                    calender.addDate(task);
                } else if (parts[0].equals("D")) {
                    Task task = new Deadline(Boolean.parseBoolean(parts[1]), parts[2], parts[3]);
                    tasks.add(task);
                    calender.addDate(task);
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            return null;
        } catch (BadDateException e) {
            System.err.println("Bad dates in File\n Please correct it and reload");
            return null;
        }
    }

    public void writeListIntoFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                switch (task.getType()) {
                case TODO:
                    fw.write("T|" + task.getDone() + "|" + task.getTask()
                            + System.lineSeparator());
                    break;
                case EVENT:
                    fw.write("E|" + task.getDone() + "|" + task.getTask() + "|" +
                            task.getDate().format(Parser.DATE_WRITE_FORMATTER)
                            + System.lineSeparator());
                    break;
                case DEADLINE:
                    fw.write("D|" + task.getDone() + "|" + task.getTask() + "|" +
                            task.getDate().format(Parser.DATE_WRITE_FORMATTER)
                            + System.lineSeparator());
                    break;
                default:
                    break;
                }
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("Unable to write");
        }
    }

    public Storage (String filepath) {
        file = new File(filepath);
        try {
            file.getParentFile().mkdir(); //makes data directory if does not exists
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("Unable to create file");
        }
    }
}
