import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * Storage class handles loading and saving of tasks into an external file.
 */
public class Storage {
    String filePath;
    File file;

    /**
     * Constructs a Storage object.
     * @param filePath Path where the file is saved or should be saved.
     */
    public Storage(String filePath) {
        file = new File(filePath);
        try {
            file.getParentFile().mkdir();
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("Unable to create file");
        }
    }

    /**
     * Loads tasks data from file.
     * @return Array list of Task.
     * @throws FileNotFoundException If file fails to load.
     */
    public ArrayList<Task> loadData() throws FileNotFoundException {
        ArrayList<Task> dukeList = new ArrayList<>();
        Scanner sc = new Scanner(file);
        Task t;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] splitBySpace = line.split(" ");
            String[] splitBySlash;
            String command = splitBySpace[0];

            if (command.equals("D")) {
                splitBySlash = line.split("/");

                //Splits String the second time to get the description of deadline task
                String[] splitBySpace2 = splitBySlash[0].split(" ");
                String getDesc = "";
                for (int i = 2; i < splitBySpace2.length; i++) {
                    getDesc = getDesc + splitBySpace2[i] + " ";
                }

                t = new Deadline(getDesc.trim(), splitBySlash[1]);
                if (splitBySpace[1].equals("1")) {
                    t.markAsDone();
                }
                dukeList.add(t);
            } else if (command.equals("E")) {
                splitBySlash = line.split("/");

                //Splits String the second time to get the description of event task
                String[] splitBySpace2 = splitBySlash[0].split(" ");
                String getDesc = "";
                for (int i = 2; i < splitBySpace2.length; i++) {
                    getDesc = getDesc + splitBySpace2[i] + " ";
                }

                t = new Event(getDesc.trim(), splitBySlash[1]);
                if (splitBySpace[1].equals("1")) {
                    t.markAsDone();
                }
                dukeList.add(t);
            } else {
                String getDesc = "";
                for (int i = 2; i < splitBySpace.length; i++) {
                    getDesc = getDesc + splitBySpace[i] + " ";
                }

                t = new ToDo(getDesc.trim());
                if (splitBySpace[1].equals("1")) {
                    t.markAsDone();
                }
                dukeList.add(t);
            }
        }

        return dukeList;
    }

    /**
     * Writes list of tasks into file.
     * @param tasks Contains list of tasks.
     * @throws IOException If unable to write file.
     */
    public void writeData(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(file);
        String tmpTxt = "";

        if (tasks.getSize() > 0) {
            for (int i = 0; i < tasks.getSize() - 1; i++) {
                tmpTxt = tmpTxt + tasks.getDukeList().get(i).format() + "\n";
            }
            tmpTxt = tmpTxt + tasks.getDukeList().get(tasks.getSize() - 1).format();
        }

        fw.write(tmpTxt);
        fw.close();
    }
}
