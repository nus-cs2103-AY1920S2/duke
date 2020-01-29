
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    String home;
    Path path;

    public Storage() {
        this.home = System.getProperty("user.home");
        this.path = Paths.get(home, "Desktop", "NUS Y2S2", "CS2103T", "project", "duke", "data", "duke.txt");

    }

    /**
     * method invoked to store all task from storage in hard disk to program
     */
    public ArrayList<String> startupStorage() {

        try {
            return readFile(this.path);
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return new ArrayList<String>();

    }

    /**
     * method invoked to store all task from program to storage in hard disk
     */
    public void storeToStorage(ArrayList<Task> list) {

        try {
            writeToHardDisk(list);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }


    /**
     * Reads the item from the file and store in and arraylist
     * @param filepath file path of where the data is stored
     * @return the arraylist containing the tasks
     * @throws IOException
     */
    private ArrayList<String> readFile(Path filepath) throws IOException {
        File f = filepath.toFile();
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<String> mylist = new ArrayList<>();
        while (s.hasNext()) {
            String tasks = s.nextLine();
            mylist.add(tasks);
        }

        return mylist;

    }



    /**
     * Writes the items in the list to the hard disk
     * @param mylist list which stores all the task
     * @throws IOException Exception thrown if no file is found
     */
    private void writeToHardDisk (ArrayList<Task> list) throws IOException {
        File f = this.path.toFile();
        FileWriter writer = new FileWriter(f);

        for (Task tasks : list) {
            writer.write(tasks.saveToHardDiskFormat() + System.lineSeparator());
        }
        writer.close();

    }
}
