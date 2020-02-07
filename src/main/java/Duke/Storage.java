package Duke;
import java.io.*;
import java.util.ArrayList;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        try {
            FileInputStream file = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(file);
            return (ArrayList) in.readObject();
        } catch (IOException e) {
            System.out.println(e + "\n" + "Creating a new storage file.....");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" +
                    " is caught");
        }
        return new ArrayList<Task>();
    }

    public void store(ArrayList<Task> tsklst) {
        try {
            //saving file as object
            FileOutputStream file = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(tsklst);
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
