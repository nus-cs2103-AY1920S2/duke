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

    /**
     * Loads the list of tasks from data.txt.
     *
     * @return ArrayList <Task> This returns the contents of the data.txt file.
     */

    public ArrayList <Task> load() {
        // returns the list of tasks from the data.txt
        ArrayList <Task> arr = new ArrayList <Task>();
        try {
            Scanner sc = new Scanner(new File (filePath));
            while (sc.hasNextLine()) {                                                                                      // looks at duke.txt line by line and creates the respective task and saves it in an arraylist
                String line = sc.nextLine();
                String taskType = "";
                String taskFormattedDate = "";
                String[] lineMinusDate = line.split("\\(")[0].split("\\s");
                String taskName = "";
//                int isTaskDone = Integer.getInteger(lineMinusDate[2]);
//                if (isTaskDone == 1) {
//
//                }
                for (int i = 4; i < lineMinusDate.length; i++) {
                    if (i == 4) {
                        taskName += lineMinusDate[i];
                    } else {
                        taskName += " " + lineMinusDate[i];
                    }
                }

                if (line.split("\\s")[0].equals("[T]")) {
                    taskType = "todo";
                    Task curr = new Task(taskType, taskName);
                    if (Integer.parseInt(line.split("\\s")[2]) == 1) {
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
                    if (Integer.parseInt(line.split("\\s")[2]) == 1) {
                        curr.setDone();
                    }
                    curr.fileDate = taskFormattedDate;
                    arr.add(curr);
                }
            }
            return arr;
        } catch (FileNotFoundException e){
            return new ArrayList <Task>();
        }

    }

    /**
     * Updates the data.txt file with the information provided by the user.
     * @param dataFile This is the file path for data.txt
     * @param arr this is the most updated ArrayList <Task>
     */

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

    /**
     * Appends a line of strings to the data.txt file.
     * @param textToAdd This is the line of strings to add to data.txt
     * @throws IOException On input error
     */

    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("./" + "data/duke.txt",true);
        fw.write(textToAdd);
        fw.close();
    }
}
