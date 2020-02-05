import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList <Task> load() {
        // returns the list of tasks from the duke.txt
        ArrayList <Task> arr = new ArrayList <Task>();
        try {
            Scanner sc = new Scanner(new File (filePath));
            while (sc.hasNextLine()) {                                                                                      // looks at duke.txt line by line and creates the respective task and saves it in an arraylist
                String line = sc.nextLine();
                String taskType = "";
                String taskFormattedDate = "";
                String taskName = line.split("\\s")[4];

                if (line.split("\\s")[0].equals("[T]")) {
                    taskType = "todo";
                    Task curr = new Task(taskType, taskName);
                    if (line.split("\\s")[2] == "1") {
                        curr.setDone();
                    }
                    arr.add(curr);
                } else if (line.split("\\s")[0].equals("[D]")) {
                    taskType = "deadline";
                    taskFormattedDate = line.split("by: ")[1].substring(0, line.split("by: ")[1].length() - 1);
                    Task curr = new Task(taskType, taskName);
                    if (Integer.parseInt(line.split("\\s")[2]) == 1) {
                        curr.setDone();
                    }

                    // TESTING LINE
                    //System.out.println(taskFormattedDate);


                    curr.addProcessedDate(taskFormattedDate);
                    arr.add(curr);
                } else {
                    taskType = "event";
                    taskFormattedDate = line.split("at: ")[1].substring(0, line.split("at: ")[1].length() - 1);
                    Task curr = new Task(taskType, taskName);
                    if (line.split("\\s")[2].equals(1)) {
                        curr.setDone();
                    }
                    curr.addProcessedDate(taskFormattedDate);
                    arr.add(curr);
                }
            }
            return arr;
        }
        catch (FileNotFoundException e){
            return new ArrayList <Task>();
        }

    }

    public static void fileUpdate(File dataFile, ArrayList <Task> arr) {
        try {
            writeToFile("I love you baby\n");
            //dataFile.delete();
            new FileWriter("./" + "data/duke.txt", false).close();
            for (int i = 0; i < TaskList.arr.size(); i++) {
                writeToFile(TaskList.arr.get(i).getIcon() + " | " + TaskList.arr.get(i).getStatusBinary() + " | " + TaskList.arr.get(i).getDescription() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("./" + "data/duke.txt",true);
        fw.write(textToAdd);
        fw.close();
    }
}
