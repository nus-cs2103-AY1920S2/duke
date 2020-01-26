import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    void saveTask(Task task) throws IOException {
        FileWriter fw = new FileWriter(this.filePath,true);
        fw.write(task.toString() + System.lineSeparator());
        fw.close();
    }
}
