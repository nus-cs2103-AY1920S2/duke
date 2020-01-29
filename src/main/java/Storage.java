import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class handles all disk related functions, including writing to and
 * reading from disk.
 */
public class Storage {
    private final String fileDir = "./appData";
    private final String fileName = "duke.txt";
    private final String filePath = fileDir + "/" + fileName;

    private void createNewFile() {
        try {
            File dir = new File(this.fileDir);
            dir.mkdirs();
            File file = new File(this.filePath);
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Appends the String {@code data} provided with a new line to the file
     * {@code filePath}.
     * 
     * @param data String to be appended
     */
    public void writeToDisk(String data) {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Changes line {@code lineIdx} in file {@code filePath} from {@code 0} to
     * {@code 1} to indicate that the task has been marked as done.
     * 
     * @param lineIdx the line number to change (zero-indexed)
     */
    public void markAsDone(int lineIdx) {
        try {
            ArrayList<String> lines = this.readFromDisk();
            FileWriter fileWriter = new FileWriter(this.filePath, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            int currIdx = 0;
            for (String line : lines) {
                if (currIdx == lineIdx) {
                    bufferedWriter.write(line.replaceFirst("0", "1"));
                } else {
                    bufferedWriter.write(line);
                }
                bufferedWriter.newLine();
                currIdx++;
            }
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Removes line {@code lineIdx} in file {@code filePath}.
     * 
     * @param lineIdx the line number to remove (zero-indexed)
     */
    public void removeTask(int lineIdx) {
        try {
            ArrayList<String> lines = this.readFromDisk();
            FileWriter fileWriter = new FileWriter(this.filePath, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            int currIdx = 0;
            for (String line : lines) {
                if (currIdx != lineIdx) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
                currIdx++;
            }
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Reads the file {@code filePath} and returns an {@code ArrayList<String>} of
     * each individual line read.
     * 
     * If the file does not exist yet, {@code createNewFile} will be called to
     * create an empty file {@code filePath} to allow for subsequent writes to the
     * file.
     * 
     * @return an {@code ArrayList<String>} of each individual line read
     */
    public ArrayList<String> readFromDisk() {
        ArrayList<String> lines = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(this.filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            // file not found yet, create the file
            this.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lines;
    }
}