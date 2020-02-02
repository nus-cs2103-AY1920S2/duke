import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static String filePath;

    public Storage (String filePath) {

        this.filePath = filePath;
    }

    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public static void readFile(ArrayList<Task> list) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {

            String readTask = s.nextLine();
            String[] readTaskArr = readTask.split(" ");



            if (readTaskArr[0].equals("T")) {

                String taskDesc = "";
                for (int i = 4; i < readTaskArr.length; i ++) { // Extract the description of the task
                    taskDesc += " " + readTaskArr[i];
                }


                ToDo newTask = new ToDo(taskDesc);

                if (readTaskArr[2].equals("1")) { // Sets status as done if it is done
                    newTask.setStatusDone();
                }

                list.add(newTask);




            } else if (readTaskArr[0].equals("D")) {

                String taskDesc = "";
                int indexOfSlash = 0;
                String timing = "";

                for (int i = 4; i < readTaskArr.length; i++) {

                    if (readTaskArr[i].equals("|")) {
                        indexOfSlash += i;
                        break;
                    }
                    taskDesc += " " + readTaskArr[i];
                }

                for (int i = indexOfSlash + 1; i < readTaskArr.length; i ++) {
                    timing += " " + readTaskArr[i];
                }

                LocalDate timingLocalDate = LocalDate.parse(timing.trim());

                Deadline newTask = new Deadline(taskDesc, timingLocalDate);

                if (readTaskArr[2].equals("1")) { // Sets status as done if it is done
                    newTask.setStatusDone();
                }

                list.add(newTask);





            } else if (readTaskArr[0].equals("E")) {

                String taskDesc = "";
                int indexOfSlash = 0;
                String timing = "";

                for (int i = 4; i < readTaskArr.length; i++) {

                    if (readTaskArr[i].equals("|")) {
                        indexOfSlash += i;
                        break;
                    }
                    taskDesc += " " + readTaskArr[i];
                }

                for (int i = indexOfSlash + 1; i < readTaskArr.length; i ++) {
                    timing += " " + readTaskArr[i];
                }

                LocalDate timingLocalDate = LocalDate.parse(timing.trim());

                Event newTask = new Event(taskDesc, timingLocalDate);

                if (readTaskArr[2].equals("1")) { // Sets status as done if it is done
                    newTask.setStatusDone();
                }

                list.add(newTask);

            }
        }
    }

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
