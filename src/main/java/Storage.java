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



            if (readTaskArray[0].equals("T")) {

                String taskDescription = "";
                for (int i = 4; i < readTaskArray.length; i ++) { // Extract the description of the task
                    taskDescription += " " + readTaskArray[i];
                }


                ToDo newTask = new ToDo(taskDescription);

                if (readTaskArray[2].equals("1")) { // Sets status as done if it is done
                    newTask.setStatusDone();
                }

                list.add(newTask);




            } else if (readTaskArray[0].equals("D")) {

                String taskDescription = "";
                int indexOfSlash = 0;
                String timing = "";

                for (int i = 4; i < readTaskArray.length; i++) {

                    if (readTaskArray[i].equals("|")) {
                        indexOfSlash += i;
                        break;
                    }
                    taskDescription += " " + readTaskArray[i];
                }

                for (int i = indexOfSlash + 1; i < readTaskArray.length; i ++) {
                    timing += " " + readTaskArray[i];
                }

                LocalDate timingLocalDate = LocalDate.parse(timing.trim());

                Deadline newTask = new Deadline(taskDescription, timingLocalDate);

                if (readTaskArray[2].equals("1")) { // Sets status as done if it is done
                    newTask.setStatusDone();
                }

                list.add(newTask);





            } else if (readTaskArray[0].equals("E")) {

                String taskDescription = "";
                int indexOfSlash = 0;
                String timing = "";

                for (int i = 4; i < readTaskArray.length; i++) {

                    if (readTaskArray[i].equals("|")) {
                        indexOfSlash += i;
                        break;
                    }
                    taskDescription += " " + readTaskArray[i];
                }

                for (int i = indexOfSlash + 1; i < readTaskArray.length; i ++) {
                    timing += " " + readTaskArray[i];
                }

                LocalDate timingLocalDate = LocalDate.parse(timing.trim());

                Event newTask = new Event(taskDescription, timingLocalDate);

                if (readTaskArray[2].equals("1")) { // Sets status as done if it is done
                    newTask.setStatusDone();
                }

                list.add(newTask);

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
            if (t.getType() == "todo") {

                String taskMessage = "T | " + t.getStatus() + " |" + t.getDescription() + "\n";
                writeToFile(taskMessage);

            } else if (t.getType() == "deadline") {

                String taskMessage = "D | " + t.getStatus() + " |" + t.getDescription()
                        + " | " + t.getBy() + "\n";
                writeToFile(taskMessage);

            } else if (t.getType() == "event") {

                String taskMessage = "E | " + t.getStatus() + " |" + t.getDescription()
                        + " | " + t.getBy() + "\n";
                writeToFile(taskMessage);

            }
        }

        fw.close();
    }


}
