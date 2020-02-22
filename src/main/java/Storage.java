import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

/**
 *deals with loading tasks from the file and
 * saving tasks in the file
 */
public class Storage {

    private List<String> allInstructions = new ArrayList<>();
    private List<Task> doneTasks = new ArrayList<>();
    private String filePath;
    private File file;
    private TaskList tasklist = new TaskList();

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            this.file.createNewFile();
            this.file = new File(this.filePath);
        }
    }

    TaskList readFileContents() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String instruction = s.nextLine();
            System.out.println(instruction);
            String[] arr = instruction.split(" | ");
            if (arr[0].equals("E")) {
                Event e = new Event(arr[4], arr[6] + " " + arr[7]);
                System.out.println(e);
                if (Integer.parseInt(arr[2]) == 1) {
                    e.markAsDone();
                }
                doneTasks.add(e);
            }
            if (arr[0].equals("T")) {
                Todo t = new Todo(arr[4]);
                if (Integer.parseInt(arr[2]) == 1) {
                    System.out.println(t);
                    t.markAsDone();
                }
                doneTasks.add(t);
            }
            if (arr[0].equals("D")) {
                Deadline d = new Deadline(arr[4], arr[6] + " " + arr[7]);
                if (Integer.parseInt(arr[2]) == 1) {
                    d.markAsDone();
                }
                doneTasks.add(d);
            }
        }
        tasklist.setTask(doneTasks);
        return tasklist;
    }

    void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * run through all the tasks and prints it out in .txt file
     */
    public void load(List<Task> doneTasks) {
        try {
            String tasks = "";
            for (int i = 0; i < doneTasks.size(); i++) {

                Task t = doneTasks.get(i);
                if (t instanceof Todo) {
                    tasks += "T | " + t.getIsTaskDone() + " | "
                            +  t.getDescription()
                            + System.lineSeparator();
                }

                if (t instanceof  Deadline) {
                    Deadline d = (Deadline) t;
                    tasks += "D | " + t.getIsTaskDone() + " | "
                            + t.getDescription() + " | "
                            + d.getBy() + System.lineSeparator();
                }

                if (t instanceof Event) {
                    Event e = (Event) t;
                    tasks += "E | " + t.getIsTaskDone() + " | "
                            + t.getDescription() + " | "
                            + e.getAt() + System.lineSeparator();
                }
            }
            writeToFile(filePath, tasks);
        } catch (IOException e) {
            System.out.println(" Something went wrong: " + e.getMessage());
        }
    }

}
