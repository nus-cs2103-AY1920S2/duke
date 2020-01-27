import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class Storage {

    private String filepath;
    private File file;

    protected Storage(String filepath) throws IOException {
        this.filepath = filepath;
        file = new File(filepath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            file = new File(filepath);
        }
    }

   protected TaskList loadTaskList() throws IOException {
       List<String> data = FileUtils.readLines(file, Charset.defaultCharset());
       return new TaskList(data);
   }

   protected void saveTaskList(TaskList taskList) throws IOException {
       FileWriter fileWriter = new FileWriter(file);
       fileWriter.write(taskList.getTaskListSaveFormat());
       fileWriter.flush();
   }

}
