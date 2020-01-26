import java.io.*;

public class Storage {

    protected String filePath;

    public Storage(String filepath) {
        this.filePath = filepath;
    }

    public TaskList load() {
        TaskList res = new TaskList();

        try {
            File savedData = new File(filePath);
            FileInputStream fis = new FileInputStream(savedData);
            ObjectInputStream ois = new ObjectInputStream(fis);
            TaskList lstSaved = (TaskList) ois.readObject();
            ois.close();
            System.out.println("    Retrieving my little boy's history..");
            res = lstSaved;

        } catch (FileNotFoundException e) {
            System.out.println("    Initialising new list for my little boy..");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void save(TaskList lst) {
        try {
            FileOutputStream fos = new FileOutputStream (filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lst);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
