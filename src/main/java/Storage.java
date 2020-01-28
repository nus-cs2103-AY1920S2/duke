import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private ArrayList<String> lines;
    
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.lines = new ArrayList<>();
        
        // populate array with lines from file
        // create empty file if file does not exist
        File file = new File(filePath);
        if (!file.createNewFile()) {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                lines.add(sc.nextLine());
            }
            sc.close();
        }
    }
    
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        for (String line : this.lines) {
            String[] lineArr = line.split("\\|");  // escape special char |
            
            String type = lineArr[0];
            String doneStatus = lineArr[1];
            String description = lineArr[2];
            
            Task task;
            if (type.equals("T")) {
                task = new Todo(description);
            } else if (type.equals("D")) {
                task = new Deadline(description, LocalDate.parse(lineArr[3]));
            } else {
                task = new Event(description, LocalDate.parse(lineArr[3]));
            }

            if (doneStatus.equals("1")) {
                task.isDone = true;
            }

            tasks.add(task);
        }
        
        return tasks;
    }
    
    public void writeToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, false);  // overwrite entire file
        for (Task line : taskList.getList()) {
            fw.append(line.getFileFormattedLine()).append(System.lineSeparator());
        }
        fw.close();
    }
}
