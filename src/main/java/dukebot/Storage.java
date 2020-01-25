package dukebot;

import java.io.*;

public class Storage {
    public String path;

    public Storage(String path) {
        this.path = path;
    }

    public void saveToFile(Serializable data) {
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

    public Object loadFromFile() {
        try{
            FileInputStream readData = new FileInputStream("peopledata.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            Object data =  readStream.readObject();
            readStream.close();

            return data;
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}