package helper;

import task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class which loading tasks from the file and saving tasks in the file into hard-disk.
 */

public class Storage {

    /**
     * arrayList of task and filePath (duke.txt).
     */
    private ArrayList<Task> taskList;
    private String filePath;

    public Storage() {
    }

    public Storage(ArrayList<Task> taskList, String filePathDefault) {
        this.taskList = taskList;
        this.filePath = filePathDefault;
    }

    /**
     * method that read and display all the task recorded in duke.txt.
     *
     * @return command and task entered by user.
     * @throws FileNotFoundException
     */
    public String readFile() throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        String fileData = "Load data from duke text file \n";
        String data = "";
        while (s.hasNext()) {
            data += s.nextLine() + "\n";
        }
        return fileData + "\n" + data;
    }

    /**
     * method that write all the command and task into duke.txt.
     */

    public void saveIntoFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter br = new BufferedWriter(fw); // inin
            for (int i = 0; i < taskList.size(); i++) {
//                fw.write(taskList.get(i).toString());
                br.write(taskList.get(i).toString()); // initialize BufferedWriter
                br.newLine(); // create platform-specific line separators automatically
            }
            br.close(); // close bufferedWrite
            fw.close();
        } catch (IOException e) {
            // System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}









