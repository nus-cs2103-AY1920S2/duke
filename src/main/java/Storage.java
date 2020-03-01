import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class handles the program's interaction with the save file
 * on the disk. It is responsible for loading from the save file on
 * initialisation, and writing any updates to the list to the save file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads a previously saved task list from the hard disk
     * upon starting up the program.
     *
     * @param list Working task list of the program, for loading the saved data onto.
     * @throws FileNotFoundException If the file specified by the filepath cannot be found.
     */
    public void load(TaskList list) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);

        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            StringTokenizer st = new StringTokenizer(nextLine, "|");
            String taskType = st.nextToken();
            String taskDone = st.nextToken();

            Task newTask;
            switch (taskType) {
                case "D":
                    newTask = new Deadline(st.nextToken(), st.nextToken());
                    break;
                case "E":
                    newTask = new Event(st.nextToken(), st.nextToken());
                    break;
                default:
                    newTask = new ToDo(st.nextToken());
                    break;
            }
            if (taskDone.equals("1")) {
                newTask.markDone();
            }
            list.add(newTask);
        }
    }

    /**
     * Saves any changes to the task list to the hard disk
     * immediately by overwriting the original file.
     *
     * @param list Updated working task list of the program, for rewriting the file with.
     * @throws IOException If an error occurs in writing the file.
     */
    public void save(TaskList list) {
        try {
            FileWriter fw = new FileWriter(filePath);
            String textToWrite = "";
            int listLength = list.size();
            for (int i = 0; i < listLength; i++) {
                textToWrite = textToWrite + list.get(i).getSaveDisplay();
            }
            fw.write(textToWrite);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
