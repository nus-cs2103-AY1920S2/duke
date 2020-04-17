import java.io.*;
import java.util.ArrayList;
/**
 * Represents a wrapper for a File database. A <code>Storage</code> object corresponds to a database
 */
public class Storage {
    private File database;

    public Storage(String fileName) {
        database = new File(fileName);
    }

    /**
     * Returns an array of String of all tasks read from the database
     *
     * @return an array of String
     * @throws IOException when the file is unable to be read properly
     */
    // returns an array of String
    public ArrayList<String> load() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(database));
        ArrayList<String> contents = new ArrayList<>();
        String str;

        while ((str = br.readLine()) != null) {
            contents.add(str);
        }

        return contents;
    }

    /**
     * Writes from memory to the database
     *
     * @param list represents the list of tasks to be done
     * @throws IOException if unable to create new BufferedWriter from database, or unable to write
     */
    public void save(ArrayList<Task> list) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(database));

        for (Task task : list) {
            bw.write(task.convert() + "\n");
        }

        bw.flush();
    }
}