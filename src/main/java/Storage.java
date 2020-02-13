import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represent a storage object to handle storing of the list into files.
 */
public class Storage {

    protected String filepath;
    public static final int TASK_COMMAND = 0;
    public static final int DONE = 1;
    public static final int TASK_DESCRIPTION = 2;
    public static final int TIME = 3;

    /**
     * Constructor for storage.
     * @param filepath of the file
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Read the file and convert into a Arraylist of tasks.
     * @return ArrayList of task
     */
    public ArrayList<Task> readFile() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File f = new File(filepath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String word = s.nextLine();
                String[] parsed = TextParser.myThirdParser(word);
                Task currentTask = null;
                switch (parsed[TASK_COMMAND]) {
                case "T":
                    currentTask = new Todo(parsed[TASK_DESCRIPTION]);
                    break;
                case "D":
                    currentTask = new Deadline(parsed[TASK_DESCRIPTION], LocalDate.parse(parsed[TIME]));
                    break;
                case "E":
                    currentTask = new Event(parsed[TASK_DESCRIPTION], LocalDate.parse(parsed[TIME]));
                    break;
                default:
                }

                if (parsed[DONE].equals("1")) { // 1 means done
                    currentTask.markAsDone();
                }
                list.add(currentTask);
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    /**
     * Writes the arraylist of task into the file.
     * @param taskList ArrayList of tasks
     */
    public void writeFile(ArrayList<Task> taskList) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filepath);
            for (Task current : taskList) {
                if (current instanceof Todo) {
                    writer.write("T" + "/" + current.checkDone() + "/" + current.getTask()
                            + System.lineSeparator());
                } else if (current instanceof Deadline) {
                    writer.write("D" + "/" + current.checkDone() + "/" + current.getTask() + "/"
                            + ((Deadline) current).getDate() + System.lineSeparator());
                } else if (current instanceof Event) {
                    writer.write("E" + "/" + current.checkDone() + "/" + current.getTask() + "/"
                            + ((Event) current).getDate() + System.lineSeparator());
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


