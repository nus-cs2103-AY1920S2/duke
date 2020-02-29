package duke.handler;

import duke.entity.task.Deadline;
import duke.entity.task.Event;
import duke.entity.task.Task;
import duke.entity.task.Todo;
import duke.exception.DirectoryNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private Path filePath;
    private String fileName;

    public Storage() {

    }

    public Storage(Path filePath, String fileName) throws DirectoryNotFoundException {
        boolean directoryExists = java.nio.file.Files.exists(filePath);
        if (directoryExists) {
            //System.out.println("Directory initialized: " + filePath.toString());
            this.filePath = filePath;
            this.fileName = fileName;
        } else {
            //throw new DirectoryNotFoundException();
        }
    }

    /**
     * Loads the tasks in string form from memory, before converting them into Task objects
     *
     * @return list of tasks saved in the file
     */
    public List<Task> loadTaskFromMemory () throws FileNotFoundException {
        //System.out.println("Reading tasks from directory: " + this.filePath.toString());
        File file = new File(this.filePath.toString() + "\\" + this.fileName);
        Scanner sc = new Scanner(file);
        List<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            Task nextTask = parseTaskFromLine(sc.nextLine());
            if (nextTask != null) {
                tasks.add(nextTask);
            }
        }
        return tasks;
    }

    /**
     * Converts the saved string format in the file to a format that the programme can use to create Task objects
     *
     * @return the Task that was created
     */
    public Task parseTaskFromLine (String line) {
        Task task = null;
        //System.out.println("Parsing line: " + line);
        String[] wordArray = line.split("\\|");
        try {
            switch (wordArray[0]) {
                case "T":
                    task = new Todo(wordArray[2]);
                    break;

                case "D":
                    task = new Deadline(wordArray[2], wordArray[3]);
                    break;

                case "E":
                    task = new Event(wordArray[2], wordArray[3]);
                    break;

                default:
                    //System.out.println("Invalid task format: " + line);
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            //System.out.println("Invalid task format: " + line);
            return null;
        }
        if (task != null && wordArray[1].equals("1")) {
            task.markAsDone(true);
        }
        return task;
    }

    /**
     * Saves the list of task to memory after converting them to String format\
     *
     * @param tasks List of Task objects to be saved
     */
    public void saveTasksToMemory (List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath.toString() + "\\" + this.fileName);
        String toWrite = "";
        for(int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).toStringForm() + System.lineSeparator());
        }
        fw.close();
    }
}
