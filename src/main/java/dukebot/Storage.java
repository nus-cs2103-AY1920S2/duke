package dukebot;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    public String path;

    public Storage(String path) {
        this.path = path;
    }

    public void saveToFile(ArrayList<Task> data) {
        try{
            FileOutputStream writeData = new FileOutputStream(this.path);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(data);
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> loadFromFile() {
        if (new File(this.path).isFile()) {
            try{
                FileInputStream readData = new FileInputStream(this.path);
                ObjectInputStream readStream = new ObjectInputStream(readData);

                ArrayList<Task> data = (ArrayList<Task>) readStream.readObject();
                readStream.close();

                return data;
            }catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }
}