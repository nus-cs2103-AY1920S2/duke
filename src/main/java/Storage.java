import java.util.ArrayList;
import java.util.List;

import java.io.FileWriter;
import java.io.IOException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// contains all the instructions taken in by parser
// and returns a list of tasks

public class Storage {

    private List<String> allInstructions = new ArrayList<>();
    private List<Task> doneTasks = new ArrayList<>();
    private String filePath;
    private File file;

    // reads the instructions from txt file
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            this.file.createNewFile();
            this.file = new File( this.filePath);
        }
    }

    TaskList readFileContents() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while(s.hasNextLine()) {
            String instruction = s.nextLine();
            allInstructions.add(instruction);
            }
        TaskList taskL = new TaskList();
        taskL.setAllInstructions(allInstructions);
        return taskL;
        }

    void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void load() {
        try {
            String tasks = "";
            for (int i=0 ;i< doneTasks.size(); i++) {

                Task t = doneTasks.get(i);
                if (t instanceof Todo) {
                    tasks += "T | " + t.getIsTaskDone() + " | " +  t.getDescription() + System.lineSeparator();
                }

                if (t instanceof  Deadline) {
                    Deadline d = (Deadline) t;
                    tasks += "D | " + t.getIsTaskDone() + " | " + t.getDescription() + " | " + d.getBy() + System.lineSeparator();
                }

                if (t instanceof Events) {
                    Events e = (Events) t;
                    tasks += "E | " + t.getIsTaskDone() + " | " + t.getDescription() + " | " + e.getAt() + System.lineSeparator();
                }
            }
            writeToFile(filePath, tasks);
        } catch (IOException e) {
            System.out.println(" Something went wrong: " + e.getMessage());
        }
    }

    void setDoneTasks(List<Task> i) {
        this.doneTasks = i;
    }

}
