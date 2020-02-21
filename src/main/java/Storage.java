import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles saving and loading of task list data into hard drive.
 */
public class Storage {
    private File listPath;
    private File prevListPath;
    private File arrayPath;
    private File prevArrayPath;
    private Ui ui;

    /**
     * Constructor for storage class to handle saving and loading to and from hard disk.
     */
    public Storage() {
        this.ui = new Ui();

//        String rootDir = System.getProperty("user.dir");
        String rootDir = System.getProperty("user.dir");
        StringBuilder path = new StringBuilder();
        path.append(rootDir);

        File dir = new File(path + "/data");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        this.listPath = new File(dir + "/mainList.txt");
        this.prevListPath = new File(dir + "/mainList_prev.txt");
        this.arrayPath = new File(dir + "/duke.txt");
        this.prevArrayPath = new File(dir + "/duke_prev.txt");

        try {
            listPath.createNewFile();
            prevArrayPath.createNewFile();
            arrayPath.createNewFile();
            prevArrayPath.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Writes to respective list and array files to store the updated data in the hard disk.
     *
     * @param taskList Updated task list based on user commands
     * @throws IOException If error is encountered in FileWriter
     */
    public void save(ArrayList<Task> taskList) throws IOException { // saves to both duke.txt and array.txt
        // save mainList array

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
        FileWriter fw = new FileWriter(listPath);
        String text = "";
        if (taskList.size() != 0) {
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i) == null) {
                    break;
                }
                int indexNum = i + 1;
                String item = " " + indexNum + ". " + taskList.get(i).toString();
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
        Scanner s = new Scanner(arrayPath);
        if (arrayPath.length() == 0) {
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

    private ArrayList<Task> loadPrevious() throws IOException {
        Scanner s = new Scanner(prevArrayPath);
        if (prevArrayPath.length() == 0) {
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
                        ui.showError("Format error! Honk!!");
                }
            }
            return ml;
        }
    }

    /**
     * Loads task list as a String.
     *
     * @return String representing indexed task list
     * @throws IOException If list file not found in hard disk
     */
    public String loadList() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(listPath))) {
            String line = null;
            String printedList = "";
            boolean isEmpty = true;
            while ((line = br.readLine()) != null) {
                printedList += line + "\n";
                isEmpty = false;
            }

            if (isEmpty) {
                return "  You haven't added any tasks. Honk...";
            }
            return printedList;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Writes to respective prev list and prev array files to store the data in the hard disk before
     * it was updated. This allows user to carry out the undo command.
     *
     * @param taskList Task list before update
     * @throws IOException If error is encountered in FileWriter
     */
    public void saveLastState(ArrayList<Task> taskList) throws IOException {
        // save prev mainList array
        FileWriter af = new FileWriter(prevArrayPath);
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

        // save prev list
        FileWriter fw = new FileWriter(prevListPath);
        String text = "";
        if (taskList.size() != 0) {
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i) == null) {
                    break;
                }
                int indexNum = i + 1;
                String item = " " + indexNum + ". " + taskList.get(i).toString();
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
     * Saves the current state, as the previous state, and retrieves the saved state as the current state.
     * When a user does 'undo' after an 'undo', the list goes back to its initial state before any 'undo'
     * command was inputted.
     *
     * @return Updated ArrayList of tasks after the undo command
     * @throws IOException When load() or loadPrevious() encounters IO error
     */
    public ArrayList<Task> undoAndLoad() throws IOException {
        ArrayList<Task> currentTasks = new ArrayList<>(load());
        ArrayList<Task> previousTasks = new ArrayList<>(loadPrevious());

        save(previousTasks);
        saveLastState(currentTasks);

        return load();
    }
}