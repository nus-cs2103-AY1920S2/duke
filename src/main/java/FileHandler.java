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

    private static List<Task> loadFileHelper(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(new File("filePath"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        List<Task> tasks = (List<Task>) oi.readObject();
        oi.close();
        fi.close();
        return tasks;
    }

    public static List<Task> loadFile(String filePath) {
        List<Task> tasks = new ArrayList<>();
        try {
            tasks =  loadFileHelper(filePath);
        } catch (Exception e) {
            File file = new File(filePath);
            boolean made = file.mkdir();
            System.out.println("Enjoy your virgin duke experience!");
        } finally {
            return tasks;
        }
    }
}
