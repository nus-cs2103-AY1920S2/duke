import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static String filePath;


    /**
     * Constructor for Storage class
     * Takes in a filepath in the user's hard drive and
     * stores the directory
     *
     * @param filePath directory of the stores tasklist file
     */

    public Storage (String filePath) {

        this.filePath = filePath;
    }

    /**
     * Method writes the required text into the text file in filePath
     * Makes use of FileWriter to append the text messages to the
     * text file in FilePath
     *
     * @param textToAdd is the required String to be written to the file
     */

    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Method reads all the tasks stored in the text file in filePath directory
     * and converts it into Task objects and stores it in the static object taskList
     * in the TaskList directory
     *
     * @param list ArrayList from TaskList file to take in the stored
     *             tasks from the text file in filePath directory
     */

    public static void readFile(ArrayList<Task> list) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {

            String readTask = s.nextLine();
            String[] readTaskArray = readTask.split(" ");


            switch (readTaskArray[0]) {
                case "T": {

                    int indexOfSlashForTag = 0;

                    String taskDescription = "";
                    for (int i = 4; i < readTaskArray.length; i++) { // Extract the description of the task
                        if(readTaskArray[i].equals("|")) {
                            indexOfSlashForTag = i;
                            break;
                        } else {
                            taskDescription += " " + readTaskArray[i];
                        }
                    }


                    ToDo newTask = new ToDo(taskDescription);

                    if (readTaskArray[2].equals("1")) { // Sets status as done if it is done
                        newTask.setStatusDone();
                    }

                    //Upload the tag

                    if (indexOfSlashForTag != 0) {
                        String uploadingTaskTag = "";
                        for (int i = indexOfSlashForTag + 1; i < readTaskArray.length; i++) {
                            uploadingTaskTag += readTaskArray[i];
                        }
                        newTask.setTag(uploadingTaskTag);

                    }

                    list.add(newTask);


                    break;
                }
                case "D": {

                    String taskDescription = "";
                    int indexOfSlashForDescription = 0;
                    String timing = "";
                    int indexOfSlashForTag = 0;

                    for (int i = 4; i < readTaskArray.length; i++) {

                        if (readTaskArray[i].equals("|")) {
                            indexOfSlashForDescription += i;
                            break;
                        }
                        taskDescription += " " + readTaskArray[i];
                    }


                    for (int i = indexOfSlashForDescription + 1; i < readTaskArray.length; i++) {
                        if(readTaskArray[i].equals("|")) {
                            indexOfSlashForTag = i;
                            break;
                        } else {
                            timing += " " + readTaskArray[i];
                        }
                    }

                    LocalDate timingLocalDate = LocalDate.parse(timing.trim());

                    Deadline newTask = new Deadline(taskDescription, timingLocalDate);

                    if (readTaskArray[2].equals("1")) { // Sets status as done if it is done
                        newTask.setStatusDone();
                    }

                    if (indexOfSlashForTag != 0) {
                        String uploadingTaskTag = "";
                        for (int i = indexOfSlashForTag + 1; i < readTaskArray.length; i++) {
                            uploadingTaskTag += readTaskArray[i];
                        }
                        newTask.setTag(uploadingTaskTag);

                    }

                    list.add(newTask);


                    break;
                }
                case "E": {

                    String taskDescription = "";
                    int indexOfSlashForDescription = 0;
                    String location = "";
                    int indexOfSlashForTag = 0;

                    for (int i = 4; i < readTaskArray.length; i++) {

                        if (readTaskArray[i].equals("|")) {
                            indexOfSlashForDescription += i;
                            break;
                        }
                        taskDescription += " " + readTaskArray[i];
                    }

                    for (int i = indexOfSlashForDescription + 1; i < readTaskArray.length; i++) {
                        if(readTaskArray[i].equals("|")) {
                            indexOfSlashForTag = i;
                            break;
                        } else {
                            location += " " + readTaskArray[i];
                        }
                    }


                    Event newTask = new Event(taskDescription, location);

                    if (readTaskArray[2].equals("1")) { // Sets status as done if it is done
                        newTask.setStatusDone();
                    }

                    if (indexOfSlashForTag != 0) {
                        String uploadingTaskTag = "";
                        for (int i = indexOfSlashForTag + 1; i < readTaskArray.length; i++) {
                            uploadingTaskTag += readTaskArray[i];
                        }
                        newTask.setTag(uploadingTaskTag);

                    }

                    list.add(newTask);

                    break;
                }
            }
        }
    }

    /**
     * Method updates the list of tasks in the text file in the filePath directory,
     * is used whenever there is a change of the task.
     *
     * @param list is the ArrayList of Tasks to be written into the text file
     *             in the filePath directory
     */

    public static void updateFile(ArrayList<Task> list) throws IOException {

        FileWriter fw = new FileWriter(filePath);

        for (Task t : list) {
            switch (t.getType()) {
                case "todo": {

                    String taskMessage = "T | " + t.getStatus() + " |" + t.getDescription();

                    if (!t.getTag().equals("")) {
                        taskMessage += " | " + t.getTag();
                    }

                    writeToFile(taskMessage + "\n");

                    break;
                }
                case "deadline": {

                    String taskMessage = "D | " + t.getStatus() + " |" + t.getDescription()
                            + " | " + t.getBy();

                    if (!t.getTag().equals("")) {
                        taskMessage += " | " + t.getTag();
                    }

                    writeToFile(taskMessage + "\n");

                    break;
                }
                case "event": {

                    String taskMessage = "E | " + t.getStatus() + " |" + t.getDescription()
                            + " | " + t.getAt();

                    if (!t.getTag().equals("")) {
                        taskMessage += " | " + t.getTag();
                    }

                    writeToFile(taskMessage + "\n");

                    break;
                }
            }
        }

        fw.close();
    }


}
