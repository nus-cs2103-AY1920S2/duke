
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import sampletest.Task;
import sampletest.Events;
import sampletest.Deadlines;

/**
 * CS2103 Individual Project.
 * Storage is used to store and load the file from the database.
 * @author Wei Cheng
 */
public class Storage {
    private String path;

    /**
     * Constructor for Storage.
     * @param storageLocation the String representation of the location of the text file.
     */

    public Storage(String storageLocation) {

        this.path = storageLocation;
    }

    /**
     * Save the changes made to the current list of tasks.
     * @param taskStorage current list of task.
     */

    public void saveToDisk(ArrayList<Task> taskStorage) {
        String name = this.path;
        assert name.length() > 0 : "The given path to store the list of Tasks is not valid.";
        try {
            FileWriter fw = new FileWriter(this.path);
            BufferedWriter bw = new BufferedWriter(fw);
            int counter = 0;
            for (Task task : taskStorage) {
                Task t = taskStorage.get(counter++);
                if (t instanceof Events) {
                    bw.write("Event " + t.checkDone() + " "
                            + t.getDescription() + " "
                            + (((Events) t).getDate()).toString());
                    bw.newLine();
                } else if (t instanceof Deadlines) {
                    bw.write("Deadlines " + t.checkDone() + " "
                            + t.getDescription() + " "
                            + (((Deadlines) t).getDate()).toString());
                    bw.newLine();
                } else {
                    bw.write("Todo " + t.getDescription() + " "
                            + t.checkDone());
                    bw.newLine();
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load all the existing task from the txt file.
     * @return ArrayList of Task.
     */

    public ArrayList<Task> load() {
        ArrayList<Task> destination = new ArrayList<>();
        try {
            FileReader fr = new FileReader(this.path);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                String[] tempArray = line.split(" ");
                String action = tempArray[0];
                if (action.equals("Todo")) {
                    destination.add(new Task(tempArray[1],tempArray[2]));
                } else if (action.equals("Deadlines")) {
                    destination.add(new Deadlines(tempArray[2], tempArray[3],tempArray[1]));
                } else if (action.equals("Event")) {
                    destination.add(new Events(tempArray[2], tempArray[3],tempArray[1]));
                }
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException err) {
            err.printStackTrace();
        }
        return destination;
    }
}










