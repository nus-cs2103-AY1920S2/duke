package duke.other;

import duke.task.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the Storage of the Tasks. A Storage object corresponds to an file via a file path.
 */
public class Storage {
    protected String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns an ArrayList of Tasks loaded from the file specified by the file path.
     * @return An ArrayList of Tasks
     * @throws FileNotFoundException If the file is not found in the file path
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        Path path = Paths.get(filePath);
        File noteFile = new File(path.toString());
        if (Files.notExists(path)) {
            noteFile = new File("data/duke.txt");
        }
        return fileToArray(noteFile.toString());
    }

    /**
     * Returns an ArrayList of Tasks, after converting the Tasks in the file into the corresponding Tasks objects and
     * adding them into an ArrayList.
     * @param filePath file path of the input file of Tasks
     * @return ArrayList of Tasks containing all the Tasks from the input file
     * @throws FileNotFoundException If the file is not found in the file path
     */
    private static ArrayList<Task> fileToArray(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<>();
        while(sc.hasNext()) {
            String note = sc.nextLine();
            String[] noteArr = note.split(">");
            String noteType = noteArr[0];
            boolean taskIsDone = (noteArr[1]).equals("true")  ? true : false;
            String taskDesc = noteArr[2];

            if(noteType.equals("T")) {
                taskList.add(new Todo(taskDesc, taskIsDone));
            } else if(noteType.equals("E")) {
                if(noteArr.length == 5) {
                    taskList.add(new Event(taskDesc, LocalDate.parse(noteArr[3]), LocalTime.parse(noteArr[4]),
                            taskIsDone));
                } else if(noteArr.length == 4) {
                    taskList.add(new Event(taskDesc, LocalDate.parse(noteArr[3]), taskIsDone));
                }
            } else {
                if(noteArr.length == 5) {
                    taskList.add(new Deadline(taskDesc, LocalDate.parse(noteArr[3]), LocalTime.parse(noteArr[4]),
                            taskIsDone));
                } else if(noteArr.length == 4){
                    taskList.add(new Event(taskDesc, LocalDate.parse(noteArr[3]), taskIsDone));
                }
            }
        }
        return taskList;
    }

    /**
     * Returns a String to be stored in the file specified by the file path, after converting the Tasks objects into
     * Strings
     * @param tasks TaskList of all the Tasks
     * @return String of all Tasks, in the format for storage
     */
    public static String arrayToFile(TaskList tasks) {
        ArrayList<Task> taskList = tasks.taskList;
        String outputToFile = "";
        for(Task task : taskList) {
            String taskType = task.getClass().getSimpleName();
            String taskDesc = task.getDescription();
            boolean taskIsDone = task.isDone;

            if (taskType.equals("Todo")) {
                outputToFile += "T>" + taskIsDone + ">" + taskDesc + "\n";
            } else if (taskType.equals("Event")) {
                if (((Event) task).isTime) {
                    outputToFile += "E>" + taskIsDone + ">" + taskDesc + ">" + ((Event) task).getDate() + ">"
                            + ((Event) task).getTime() + "\n";
                } else {
                    outputToFile += "E>" + taskIsDone + ">" + taskDesc + ">" + ((Event) task).getDate() + "\n";
                }
            } else {
                if (((Deadline) task).isTime) {
                    outputToFile += "D>" + taskIsDone + ">" + taskDesc + ">" + ((Deadline) task).getDate() + ">"
                            + ((Deadline) task).getTime() + "\n";
                } else {
                    outputToFile += "D>" + taskIsDone + ">" + taskDesc + ">" + ((Deadline) task).getDate() + "\n";
                }
            }
        }
        return outputToFile;
    }


    /**
     * Writes the String of all the Tasks in storage format to the file of the specified file path.
     * @param filePath file path of the file
     * @param textToAdd String of all the Tasks in storage format
     * @throws IOException If the file path is invalid
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(textToAdd);
        bw.close();
    }

    /**
     * Runs the writeToFile method.
     * @param taskList TaskList of all the tasks
     */
    public void saveFile(TaskList taskList){
        try {
            writeToFile(filePath, arrayToFile(taskList));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }




}
