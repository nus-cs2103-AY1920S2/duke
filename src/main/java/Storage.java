import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Storage {
    String path;
    static final String HOME = System.getProperty("user.dir");

    /**
     * Constructor for Storage located at filepath.
     * @param filepath Pathname of where storage will be located
     */
    public Storage(String filepath) {
        path = HOME + "/" + filepath;
    }

    /**
     * Saves taskList to current storage.
     * @param taskList List to be saved in storage
     */
    public void saveTask(List<Task> taskList) {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(taskList);
            objectOut.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads taskList from current storage.
     * @return taskList obtained from current storage. Returns new empty taskList if no existing taskList in storage.
     */
    @SuppressWarnings("unchecked")
    public List<Task> loadTask() {
        String parentDirectory = new File(path).getParent();
        if (!Files.exists(Paths.get(path))) { // if file or directory does not exist
            try {
                new File(parentDirectory).mkdirs(); // create new directory
                Files.createFile(Paths.get(path));
            } catch (IOException e) {
                System.err.println("Error creating new file at given filepath. Please try another filepath");
            }
        } else {
            try {
                FileInputStream fileIn = new FileInputStream(path);
                if (fileIn.available() > 0) {
                    ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                    List<Task> obj = (ArrayList<Task>) objectIn.readObject();
                    if (!Objects.isNull(obj)) {
                        return obj;
                    }
                    objectIn.close();
                    fileIn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }
}
