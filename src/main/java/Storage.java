import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles saving and loading of task list data into hard drive.
 */
public class Storage {
    private String listPath;
    private String arrayPath;
    private Ui ui;

    /**
     * Constructor for storage class to handle saving and loading to and from hard disk.
     *
     * @param listPath Relative file path for storing the list
     * @param arrayPath Relative file path for storing the array
     */
    public Storage(String listPath, String arrayPath) {
        this.listPath = listPath;
        this.arrayPath = arrayPath;
        this.ui = new Ui();
    }

    /**
     * Writes to respective list and array files to store the updated data in the hard disk.
     *
     * @param taskList Updated task list based on user commands
     * @throws IOException If error is encountered in FileWriter
     */
    public void save(ArrayList<Task> taskList) throws IOException { // saves to both duke.txt and array.txt
        // save mainList array
        File arrayFile = new File(arrayPath);
        arrayFile.createNewFile();

        FileWriter af = new FileWriter(arrayPath);
        String stringList = "";
        for (Task task : taskList) {
            if (task instanceof Event) {
                Event e = (Event) task;
                String doneStr = e.isDone ? "1" : "0";
                String eventString = "event|" + doneStr + "|" + e.description + "|" + e.atDate + "|"
                        + e.time24Hr + System.lineSeparator();
                stringList += eventString;
            } else if (task instanceof  Deadline) {
                Deadline d = (Deadline) task;
                String doneStr = d.isDone ? "1" : "0";
                String deadlineString = "deadline|" + doneStr + "|" + d.description + "|" + d.byDate + "|"
                        + d.time24Hr + System.lineSeparator();
                stringList += deadlineString;
            } else if (task instanceof Todo) {
                Todo t = (Todo) task;
                String doneStr = t.isDone ? "1" : "0";
                String todoString = "todo|" + doneStr + "|" + t.description + System.lineSeparator();
                stringList += todoString;
            }
        }
        af.write(stringList);
        af.close();

        // save list
        File listFile = new File(listPath);
        listFile.createNewFile();

        FileWriter fw = new FileWriter(listPath);
        String text = "";
        if (taskList.size() != 0) {
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i) == null) {
                    break;
                }
                int indexNum = i + 1;
                String item = " " + indexNum + "." + taskList.get(i).toString();
                if (i != taskList.size() - 1) {
                    item += "\n";
                }
                text += item;
            }
        }
        fw.write(text);
        fw.close();
    }

    /**
     * Loads task list as an ArrayList.
     *
     * @return ArrayList of Tasks based on stored data
     * @throws IOException If array file not found on hard disk
     */
    public ArrayList<Task> load() throws IOException {
        File f = new File(arrayPath);
        f.createNewFile();
        File lf = new File(listPath);
        lf.createNewFile();

        Scanner s = new Scanner(f);
        if (f.length() == 0) {
            return new ArrayList<>();
        } else {
            ArrayList<Task> ml = new ArrayList<>();
            while (s.hasNextLine()) {
                String[] taskArr = s.nextLine().split("\\|");
                String type = taskArr[0];
                boolean isDone = Integer.parseInt(taskArr[1]) != 0;
                String description = taskArr[2];

                switch (type) {
                case "todo":
                    Todo addTodo = new Todo(description, isDone);
                    ml.add(addTodo);
                    break;
                case "deadline":
                    String byDate = taskArr[3];
                    String deadlineTime = taskArr[4];
                    Deadline addDeadline = new Deadline(description, byDate, deadlineTime, isDone);
                    ml.add(addDeadline);
                    break;
                case "event":
                    String atDate = taskArr[3];
                    String eventTime = taskArr[4];
                    Event addEvent = new Event(description, atDate, eventTime, isDone);
                    ml.add(addEvent);
                    break;
                default:
                    ui.showError("Format error! Honk!");
                }
            }
            return ml;
        }
    }

    /**
     * Loads task list as a String.
     *
     * @return String representing indexed task list
     * @throws FileNotFoundException If list file not found in hard disk
     */
    public String loadList() throws FileNotFoundException {
        File f = new File(listPath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        if (f.length() == 0) { // if the file is empty or doesn't exist
            return "  You haven't added any tasks. Honk...";
        }
        String printedList = "";
        while (s.hasNextLine()) {
            printedList += s.nextLine() + "\n";
        }
        return printedList;
    }
}