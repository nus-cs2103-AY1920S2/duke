import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles the writing and reading to file on disk, also handles deletion of
 * tasks and marking tasks as done.
 */
public class Storage {
    /**
     * Writes task to the file myfile.txt
     * 
     * @param s String object to be written to file
     * @throws IOException Handles errors if file is not found
     */
    public void writeToFile(String s) throws IOException {
        File file = new File("./myfile.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

        if (!file.exists()) {
            file.createNewFile();
        }
        writer.write(s);
        writer.newLine();
        writer.close();
    }

    /**
     * Reads from storage and returns tasks as a String
     * 
     * @return Returns a String object after reading the text file of saved tasks
     *         from disk.
     * @throws IOException Handles errors if file is not found
     */
    public String readFromFile() throws IOException {
        File file = new File("./myfile.txt");
        StringBuilder content = new StringBuilder();
        String line;
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }

        reader.close();
        return content.toString();
    }

    /**
     * Removes a line in storage file when user deletes a task
     * 
     * @param tasks String of tasks
     * @param index Index of task to be removed
     * @throws IOException Handles errors if file is not found
     */
    public void removeLine(String tasks, int index) throws IOException {
        File file = new File("./myfile.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
        String[] lines = tasks.split("\\r?\\n");

        for (int i = 0; i < lines.length; i++) {
            if (i == index) {
                // do nth
            } else {
                writer.write(lines[i]);
                writer.newLine();
            }
        }
        writer.close();

    }

    /**
     * Changes task from undone to done when user marks a tasks as done
     * 
     * @param tasks String of tasks
     * @param index Index of task to be marked as done
     * @throws IOException Handles errors if file is not found
     */
    public void changeToDone(String tasks, int index) throws IOException {
        File file = new File("./myfile.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
        String[] lines = tasks.split("\\r?\\n");

        for (int i = 0; i < lines.length; i++) {
            if (i == index) {
                writer.write(lines[i].replaceFirst("0", "1"));
            } else {
                writer.write(lines[i]);
            }
            writer.newLine();
        }
        writer.close();

    }
}