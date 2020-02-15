package lcduke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** Ths creates a Storage object.
 */
public class Storage {
    private File savedTaskList;
    private String filePath;
    private static int StorageNo = 0;
    private static FileWriter fw;

    /** This is the constructor to create the Storage Object.
     *
     * @param filePath FilePath of user's hard disk.
     */
    protected Storage(String filePath) {
        this.filePath = filePath;
        this.savedTaskList = new File(filePath);
    }

    protected String[] load() throws FileNotFoundException {
        String[] totalTasks = new String[100];
        Scanner scanSavedTaskList = new Scanner(this.savedTaskList);
        int i = 0;
        while (scanSavedTaskList.hasNext()) {
            totalTasks[i] = scanSavedTaskList.nextLine();
            StorageNo++;
            i++;
        }
        return totalTasks;
    }

    protected int getStorageNo() {
        return StorageNo;
    }

    protected void save() throws IOException {
        fw = new FileWriter(this.filePath);
        int i = 0;
        while (i < TaskList.totalTasksCount) {
            fw.write(TaskList.totalTasks[i].toString() + System.lineSeparator());
            i++;
        }
        fw.close();
    }
}
