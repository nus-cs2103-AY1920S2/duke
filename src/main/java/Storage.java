import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    String filepath;
    List<Task> taskList;

    public Storage() {
        taskList = new ArrayList<>();
    }
    public Storage(String filepath) {
        this();
        this.filepath = filepath;
    }
    public Storage load() {
        loadFile();
        return this;
    }
    public List<Task> getTasks() {
        return this.taskList;
    }

    private List<Task> loadFile() {
        FileInputStream fi = null;
        ObjectInputStream oi = null;
        File file = null;
        try {
            file = new File(this.filepath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            fi = new FileInputStream(file);
            oi = new ObjectInputStream(fi);
            while (true) {
                Task t = (Task) oi.readObject();
                taskList.add(t);
            }
        } catch (EOFException e) {

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (oi != null) {
                    oi.close();
                }
                if (fi != null) {
                    fi.close();
                }
            } catch (IOException ex) {

            }
        }
        return taskList;
    }

    private void saveFile() {
        FileOutputStream fi = null;
        ObjectOutputStream oi = null;
        File file = null;
        try {
            file = new File(this.filepath);
            if (file.exists()) {
                file.delete();
            }
            file.getParentFile().mkdirs();
            file.createNewFile();
            fi = new FileOutputStream(file);
            oi = new ObjectOutputStream(fi);
            for (Task t : taskList) {
                oi.writeObject(t);
            }
        } catch (EOFException e) {

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream: " + e.getMessage());
            try {
                if (oi != null) {
                    oi.close();
                }
                if (fi != null) {
                    fi.close();
                }
            } catch (IOException ex) {

            }
        }
    }

}
