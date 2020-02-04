import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    File savedTaskList;
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.savedTaskList = new File(filePath);
    }

    public String[] load() throws FileNotFoundException {
        String[] totalTasks = new String[100];
        Scanner scanSavedTaskList = new Scanner(this.savedTaskList);
        int i = 0;
        while (scanSavedTaskList.hasNext()) {
            totalTasks[i] = scanSavedTaskList.nextLine();
            i++;
        }
        return totalTasks;
    }

    public void save(TaskList newList) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        int i = 0;
        while (i < newList.totalTasksCount){
            fw.write(newList.totalTasks[i].toString() + System.lineSeparator());
            i++;
        }
        fw.close();
    }
}
