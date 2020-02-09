package storage;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;

import task.Task;

/**
 * File utilities to load and save.
 */
public class FileUtility {

    /**
     * save file.
     * @param object object
     * @param filePath file path
     */
    public static void save(Object object, String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * load file.
     * @param filePath file
     * @return
     */
    public static Object load(String filePath) throws Exception {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return ois.readObject();
    }

}
