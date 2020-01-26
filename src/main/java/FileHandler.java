import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static void saveFile(String filePath, List<Task> tasks) {
        try {
            FileOutputStream fo = new FileOutputStream(new File(filePath));
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(tasks);
            oo.close();
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Task> loadFile(String filePath) {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            } else {
                FileInputStream fi = new FileInputStream(file);
                ObjectInputStream oi = new ObjectInputStream(fi);
                tasks = (List<Task>) oi.readObject();
                oi.close();
                fi.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
