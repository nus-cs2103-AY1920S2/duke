import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Storage {
    private String path;

    /**
     * Constructor for the Storage class.
     *
     * @param path Path to where the data is loaded from and stored to.
     */
    public Storage(String path) {
        this.path = path;
        File storageFolder = new File("./data");

        if (!(storageFolder.exists())) {
            storageFolder.mkdir();
        }
        this.path = "./data/" + path;
    }


    /**
     * Saves the information into the filepath location.
     *
     * @param listOfTasks List of tasks.
     * @throws IOException when file cannot be saved.
     */
    public void saveData(TaskList listOfTasks) throws IOException {
        FileWriter wr = new FileWriter(path);
        wr.write("");
        wr.close();
        if (listOfTasks.getNumOfTasks() < 1) {
            return;
        }
        FileWriter taskAdd = new FileWriter(path, true);
        for (int i = 0; i < listOfTasks.getNumOfTasks(); i++) {
            try {
                taskAdd.write(listOfTasks.getTask(i) + "\n");
            } catch (DukeException e) {
                System.out.println("Index out of bounds!");
                //will never get triggered
            }
        }
        taskAdd.close();
    }

    /**
     * Loads the data from the filepath location.
     *
     * @return ArrayList of tasks.
     * @throws FileNotFoundException when the data cannot be read from the filepath.
     */
    public ArrayList<Task> loadData() throws FileNotFoundException, ParseException {
        File dataBank = new File(path);
        Scanner reader = new Scanner(dataBank);

        ArrayList<Task> listOfTasks = new ArrayList<Task>();

        while (reader.hasNext()) {
            String task = reader.nextLine();
            char initial = task.charAt(1);
            char status = task.charAt(4);
            String desc = task.substring(7);

            if (initial == 'T') {
                listOfTasks.add(new ToDo(desc));

            } else if (initial == 'E') {
                String[] subStringy = desc.split(" \\(at: ");
                listOfTasks.add(new Event(subStringy[0], subStringy[1].substring(0, subStringy[1].length() - 1)));

            } else if (initial == 'D') {
                String[] subStringy = desc.split(" \\(by: ");
                Date dueDate = new SimpleDateFormat("dd MMM yyyy HHmm").parse(subStringy[1]);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HHmm");
                String reString = dateFormat.format(dueDate);
                listOfTasks.add(new Deadline(subStringy[0], reString));
            }

            if (status == 'Y') {
                listOfTasks.get(listOfTasks.size() - 1).doTask();
            }
        }
        return listOfTasks;
    }
}
