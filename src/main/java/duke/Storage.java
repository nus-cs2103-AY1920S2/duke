package duke;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Storage {
    private File file;
    private Scanner scanner;

    private static final String PIPE = "|";
    private static final String LF = "\n";

    public static final String DUKE_TXT_FILE_PATH = "data/duke.txt";

    public Storage(String filePath) throws IOException {
        file = new File(filePath);

        if (!file.exists()) {
            // Create file if it does not already exists
            file.createNewFile();
        }

        scanner = new Scanner(this.file);
    }

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
