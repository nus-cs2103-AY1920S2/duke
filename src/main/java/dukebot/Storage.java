package dukebot;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String storagePath;

    public Storage(String storagePath) {
        this.storagePath = storagePath;
    }

    public void saveToFile(TaskList tasks) {
        ArrayList<Task> data = tasks.taskList;
        try{
            FileOutputStream writeData = new FileOutputStream(new File(this.storagePath));
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(data);
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> loadFromFile() {
        if (new File(this.storagePath).isFile()) {
            try{
                FileInputStream readData = new FileInputStream(new File(this.storagePath));
                ObjectInputStream readStream = new ObjectInputStream(readData);

                ArrayList<Task> data = (ArrayList<Task>) readStream.readObject();
                readStream.close();

                return data;
            }catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println("fail load");
        return new ArrayList<>();
    }
}