import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Saves and loads files when Duke starts and closes
 */
public class Storage {

    /**
     * Location of saved files
     */
    private String filePath;

    /**
     * Creates a Storage object that can save and load tasks
     *
     * @param filePath Location of saved files
     */
    public Storage (String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads previously saved files into Duke when Duke starts up
     *
     * @return An Arraylist of task that contains saved files
     * @throws IOException when reader takes in NULL input
     */
    public ArrayList<Task> loadFiles() throws IOException {
        ArrayList<Task> lst = new ArrayList<>();
        String directoryPath = "src/main/java/data";
        File directory = new File (directoryPath);

        try {
            if (!directory.exists()) {
                File file = new File ("data/duke.txt");
                 if (file.exists()) {
                     directoryPath = "data";
                } else {
                    System.out.println("directory does not exist");
                    directory = new File("data");
                    directory.mkdir();
                    File newFile = new File(directoryPath, "duke.txt");
                }
            }
            System.out.println (directoryPath);
            FileReader in = new FileReader(directoryPath + "/" + "duke.txt");
            BufferedReader br = new BufferedReader(in);
            String loadTask = null;

            while ((loadTask = br.readLine()) != null) {
                String type = loadTask.substring (0, 1);

                assert type.equals ("T") || type.equals ("E")
                        || type.equals ("D") : "Has to be a certain type of task";

                int indexOfDescription = loadTask.indexOf ("||");
                if (type.equals ("T")) {
                    String descriptionOfLoadedTask = loadTask.substring(indexOfDescription + 2);
                    Todo loadedT = new Todo(descriptionOfLoadedTask);
                    if (loadTask.substring(2, 3).equals("1")) {
                        loadedT.markAsDone();
                    }
                    lst.add(loadedT);

                } else if (type.equals ("D")) {
                    int timeIndex = loadTask.indexOf("|||");
                    String loadedDescription = loadTask.substring(indexOfDescription + 2, timeIndex);
                    LocalDate loadedDDate = LocalDate.parse (loadTask.substring(timeIndex + 3));
                    Deadline loadedD = new Deadline (loadedDescription, loadedDDate);
                    if (loadTask.substring (2, 3).equals ("1")) {
                        loadedD.markAsDone();
                    }
                    lst.add(loadedD);

                } else {
                        int timeIndex = loadTask.indexOf("|||");
                        String loadedDescription = loadTask.substring(indexOfDescription + 2, timeIndex);
                        LocalDate loadedEDate = LocalDate.parse (loadTask.substring (timeIndex + 3, timeIndex + 13));
                        LocalTime loadedStart = LocalTime.parse (loadTask.substring (timeIndex + 14, timeIndex + 19));
                        LocalTime loadedEnd = LocalTime.parse (loadTask.substring (timeIndex + 20));
                        Event loadedE = new Event (loadedDescription, loadedEDate, loadedStart, loadedEnd);
                        if (loadTask.substring (2, 3).equals ("1")) {
                            loadedE.markAsDone();
                        }
                        lst.add (loadedE);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println ("No files man");
        }
        return lst;
    }

    /**
     * Saves current task in Duke when Duke closes
     *
     * @param taskList List of task to be saved
     * @throws IOException when reader reads NULL input
     */
    public void saveFiles (TaskList taskList) throws IOException {
        ArrayList<Task> lst = taskList.getList();
        File file = new File (filePath);
        FileWriter fr;
        if (file.exists()) {
            fr = new FileWriter (file, false);
            BufferedWriter bw = new BufferedWriter(fr);
            for (int i = 0; i < lst.size(); i++) {
                Task savedTask = lst.get(i);
                bw.write (savedTask.saveFile() + "\n");
                bw.flush();
                fr.flush();
            }
        } else {
            BufferedWriter bw = new BufferedWriter(new FileWriter("data/duke.txt"));
            for (int i = 0; i < lst.size(); i++) {
                Task savedTask = lst.get(i);
                bw.write (savedTask.saveFile() + "\n");
                bw.flush();
            }
        }
    }

}
