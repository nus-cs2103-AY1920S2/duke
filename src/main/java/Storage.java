import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Storage implements Serializable {
    private String path;
    private static final String HOME = System.getProperty("user.dir");

    /**
     * Constructor for Storage located at filepath.
     * @param filepath Pathname of where storage will be located
     */
    public Storage(String filepath) {
        this.path = HOME + "/" + filepath;
    }

    public String getPath() {
        return path;
    }

    /**
     * Saves taskList to current storage.
     * @param taskList List to be saved in storage
     */
    public void saveTask(List<Task> taskList) {
        try {
            assert taskList != null;
            FileOutputStream fileOut = new FileOutputStream(this.path);
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

    /**
     * Saves statistics to current storage.
     * @param stats Statistic to be saved in storage
     */
    public void saveStats(List<Command> commandList) {
        try {
            assert commandList != null;
            FileOutputStream fileOut = new FileOutputStream(this.path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(commandList);
            objectOut.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads stats from current storage.
     * @return stats obtained from current storage. Returns new empty stats if no existing taskList in storage.
     */
    @SuppressWarnings("unchecked")
    public List<Command> loadStats() {
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
                    List<Command> obj = (LinkedList<Command>) objectIn.readObject();
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
        return new LinkedList<>();
    }
}
