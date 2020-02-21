import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {
    protected String filepath;
    protected DateTimeFormatter df = DateTimeFormatter.ofPattern("d MMM yyyy");

    public static final char TASK_DONE_STATUS = 'O';
    public static final int SPLIT_TASK_LINE = 2;
    public static final int SYMBOLS_POSITION = 0;
    public static final int COMMAND_CHARACTER_POSITION = 1;
    public static final int DONE_STATUS_POSITION = 4;
    public static final int DESCRIPTION_POSITION = 1;
    public static final int DATETIME_STRING_LENGTH = 6;

    public Storage(String filepath) {
        assert filepath != null : "filepath should not be an empty string";
        this.filepath = filepath;
    }

    /**
     * Reads file specified in filepath and converts file content into a TaskList.
     * @return TaskList.
     * @throws FileNotFoundException Throws exception if file is not found in the specified file path.
     */
    public TaskList getTaskFromMemory() throws FileNotFoundException {
        TaskList taskList = new TaskList();
        File f = new File(filepath);
        Scanner fs = new Scanner(f);
        while (fs.hasNextLine()) {
            String currentLine = fs.nextLine();
            String[] input = currentLine.split(" ", SPLIT_TASK_LINE);
            char command = input[SYMBOLS_POSITION].charAt(COMMAND_CHARACTER_POSITION);
            boolean isDone = (input[SYMBOLS_POSITION].charAt(DONE_STATUS_POSITION) == TASK_DONE_STATUS);

            if (command == 'T') {
                Task t = new Todo(input[DESCRIPTION_POSITION]);
                if (isDone) {
                    t.markAsDone();
                }
                taskList.add(t);
            } else if (command == 'D') {
                int byStart = input[DESCRIPTION_POSITION].indexOf(" (by:");
                String description = input[DESCRIPTION_POSITION].substring(0, byStart);
                String by = input[DESCRIPTION_POSITION].substring(byStart + DATETIME_STRING_LENGTH,
                        input[DESCRIPTION_POSITION].length() - 1);
                LocalDate byDate = LocalDate.parse(by, df);
                Task t = new Deadline(description, byDate);
                if (isDone) {
                    t.markAsDone();
                }
                taskList.add(t);
            } else if (command == 'E') {
                int atStart = input[DESCRIPTION_POSITION].indexOf(" (at:");
                String description = input[DESCRIPTION_POSITION].substring(0, atStart);
                String at = input[DESCRIPTION_POSITION].substring(atStart + DATETIME_STRING_LENGTH,
                        input[DESCRIPTION_POSITION].length() - 1);
                LocalDate atDate = LocalDate.parse(at, df);
                Task t = new Event(description, atDate);
                if (isDone) {
                    t.markAsDone();
                }
                taskList.add(t);
            }
        }
        return taskList;
    }

    /**
     * Overwrite target file specified in filepath with content of TaskList provided.
     * @param taskList User's TaskList.
     */
    public void writeTaskToMemory(TaskList taskList) {
        try {
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < taskList.size(); i++) {
                Task current = taskList.get(i);
                sb.append(current.toString() + "\n");
            }
            File f = new File(filepath);
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(filepath);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            Ui.sendReply(e.getMessage());
        }
    }
}
