import task.Task;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ListStateSaver {
    public static void saveListState(String filePath, ArrayList<Task> list) {
        try {
            FileOutputStream savedFile = new FileOutputStream(new File(filePath));
            ObjectOutputStream savedData = new ObjectOutputStream(savedFile);
            savedData.writeObject(list);
            savedData.close();
            savedFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Task> loadSavedList(String filePath) {
        ArrayList<Task> result = new ArrayList<>();
        try {
            File savedFile = new File(filePath);
            // to create a save file in the event that there is no save file
            if (!savedFile.exists()) {
                File directory = new File(savedFile.getParent());
                if (!directory.exists()) {
                    // creates all the necessary files (parents included)
                    boolean isSuccess = directory.mkdirs();
                }
                boolean isCreated = savedFile.createNewFile();
            } else {
                // read from save file
                FileInputStream readFile = new FileInputStream(savedFile);
                ObjectInputStream readData = new ObjectInputStream(readFile);
                Object object = readData.readObject();
                result = (ArrayList<Task>) object;
                readData.close();
                readFile.close();
            }
        } catch (IOException e) {
            System.out.println("Created a new save file for you to store your data on the go!");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Name is not matching input!");
        }
        return result;
    }
}
