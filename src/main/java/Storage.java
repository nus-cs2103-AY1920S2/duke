import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.List;

public class Storage {
    String filePath;
    Gson jsonParser = new Gson();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(getFile()));

        return jsonParser.fromJson(br,
                TypeToken.getParameterized(List.class, Task.class).getType());
    }

    public void save(List<Task> tasks) {
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
