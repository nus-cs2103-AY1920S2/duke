import com.google.gson.Gson;

import java.io.*;

public class Storage {
    String filePath;
    Gson jsonParser = new Gson();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(getFile()));

        return jsonParser.fromJson(br, TaskList.class);
    }

    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(getFile());
            fw.append(jsonParser.toJson(tasks));
            fw.close();
        } catch (FileNotFoundException e) {
            getFile().getParentFile().mkdirs();
            save(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getFile() {
        return new File(filePath);
    }
}
