package duke;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Interfaces with the Java File IO API, to open, read, and write to the data (text) file.
 */
public class Storage {
    private File file;
    private Scanner scanner;

    private static final String PIPE = "|";
    private static final String LF = "\n";

    public static final String DUKE_TXT_FILE_PATH = "data/duke.txt";

    /**
     * Constructs a new Storage object. Opens the file if it exists, else creates a new one.
     *
     * @param filePath The relative file path of the data (text) file on the computer.
     * @throws IOException If there are any IO-related exceptions while trying to open or create a file.
     */
    public Storage(String filePath) throws IOException {
        file = new File(filePath);

        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.exists()) {
            if (!file.createNewFile()) {
                System.out.println("error");
            }
        }

        scanner = new Scanner(this.file);
    }

    /**
     * Reads data (Tasks) from text file and converts them to a task-list array.
     *
     * @return The task-list containing all read Tasks from the data (text) file.
     */
    public TaskList load() {
        Task t;
        String[] strArr;
        TaskList taskList = new TaskList();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            strArr = line.split("\\|");

            String type = strArr[0];
            boolean isDone = strArr[1].equals("1");
            String name = strArr[2];
            t = null;

            if (type.equals("T")) {
                t = new Todo(name, isDone);
            } else if (type.equals("E")) {
                t = new Event(name, LocalDate.parse(strArr[3]), isDone);
            } else if (type.equals("D")) {
                t = new Deadline(name, LocalDate.parse(strArr[3]), isDone);
            }

            // Add task t to ArrayList
            taskList.addTask(t);
        }

        return taskList;
    }

    /**
     * Saves the current Tasks in the task-list to the data (text) file, overwriting current file contents.
     *
     * @param taskList The task-list containing all currently added Tasks.
     * @throws IOException If there are any IO-related exception, while trying to write to file.
     */
    public void saveTasksToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(file);

        StringBuilder sb = new StringBuilder();

        int len = taskList.getNumTasks();

        for (int i = 0; i < len; ++i) {
            Task t = taskList.getTask(i);

            String name = t.getName();
            String doneStatus = t.getDone() ? "1" : "0";
            String mnemonic = t.getMnemonic();

            if (t instanceof Todo) {
                sb.append(mnemonic + PIPE + doneStatus + PIPE + name);
            } else if (t instanceof Event) {
                sb.append(mnemonic + PIPE + doneStatus + PIPE + name + PIPE + ((Event) t).getAt());
            } else if (t instanceof Deadline) {
                sb.append(mnemonic + PIPE + doneStatus + PIPE + name + PIPE + ((Deadline) t).getBy());
            }

            if (i < (len - 1)) {
                sb.append(LF);
            }
        }

        fw.write(sb.toString());
        fw.close();
    }
}
