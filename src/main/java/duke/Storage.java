package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Storage handles the writing and reading of tasks to and from a specified file. It can convert the tasks
 * in file line format to Task objects.
 */
public class Storage {
    private String filePath;
    private ArrayList<String> lines;

    /**
     * Constructs a Storage object which stores the tasks saved in the specified file as elements in a list,
     * still in the file line format.
     * 
     * @param filePath Path of file containing saved tasks.
     * @throws IOException If an I/O error occurred.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.lines = new ArrayList<>();
        
        // populate array with lines from file
        // create empty file if file does not exist
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        if (!file.createNewFile()) {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                lines.add(sc.nextLine());
            }
            sc.close();
        }
    }

    /**
     * Loads in tasks from the given file, converts them to Task objects, and stores them into a list. The
     * list is then returned.
     * 
     * @return List of saved tasks from file.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        for (String line : this.lines) {
            String[] lineArr = line.split("\\|");  // escape special char |
            
            String type = lineArr[0];
            String doneStatus = lineArr[1];
            String description = lineArr[2];
            String tagsStr = lineArr[3];
            
            Task task;
            switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                task = new Deadline(description, LocalDate.parse(lineArr[4]));
                break;
            case "E":
                task = new Event(description, LocalDate.parse(lineArr[4]));
                break;
            default:
                task = new Task(description);  // unknown, use generic type
            }

            if (doneStatus.equals("1")) {
                task.isDone = true;
            }
            
            List<String> tags = Arrays.asList(convertArrStrToStrArr(tagsStr));
            task.setTags(tags);

            tasks.add(task);
        }
        
        return tasks;
    }
    
    private String[] convertArrStrToStrArr(String arrStr) {
        String elemStr = arrStr.substring(1, arrStr.length() - 1);
        return elemStr.split(", ");
    }

    /**
     * Writes the tasks from the specified tasks list into the given file, in file line format.
     * 
     * @param taskList List of tasks.
     * @throws IOException If an I/O error occurred.
     */
    public void writeToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, false);  // overwrite entire file
        for (Task line : taskList.getList()) {
            fw.append(line.getFileFormattedLine()).append(System.lineSeparator());
        }
        fw.close();
    }
}
