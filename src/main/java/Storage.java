import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;

/**
 * class which loading tasks from the file and saving tasks in the file into hard-disk
 */

public class Storage {
    private String command;
    private String task;

    public Storage() {
    }

    public Storage(String command, String task) {
        this.command = command;
        this.task = task;
    }

    /**
     * this method writes the command and task that entered by user and saved in into the hard-disk.
     * Then it load the data from the file and print it out for user
     *
     * @param command command from user ( todo, event, deadline )
     * @param task    task which entered by user like ( read book, return book ans etc )
     * @param f       f Object used
     * @throws IOException
     */
    public static void writeFile(String command, String task, File f) throws IOException {
        String filePath = "duke.txt"; // create a file for the given file path
        FileWriter fw = new FileWriter(filePath, true); // create a file-writer and set append to true.
        BufferedWriter br = new BufferedWriter(fw); // inin
        br.write(command + " || " + task); // initialize BufferedWriter
        br.newLine(); // create platform-specific line separators automatically
        br.close(); // close bufferedWrite
        fw.close(); // close file writer
        try {
            Scanner s = new Scanner(f);
            System.out.println("Load data from duke text file ");
            while (s.hasNext()) {
                System.out.println("     " + s.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}









