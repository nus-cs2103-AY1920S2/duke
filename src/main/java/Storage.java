import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Makeshift database that stores all task list in a file.
 */
public class Storage {

    public static String filePath;

    /**
     * Creates a makeshift database in file at specified filepath.
     * @param filePath path to file that stores the task list.
     */
    public Storage(String filePath) {
        assert (new File(filePath)).exists() : "Storage file does not exist!";
        this.filePath = filePath;
    }

    /**
     * Loads list of tasks from the database.
     * @return list of tasks.
     * @throws FileNotFoundException if file annot be found.
     */
    public List<Task> load() throws FileNotFoundException {
        File f = new File(this.filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        List<Task> list = new ArrayList<>();

        while (s.hasNext()) {
            String[] parts = s.nextLine().split(" \\| ");
            switch (parts[0]) {
            case "T":
                list.add(new Todo(parts[2], Integer.parseInt(parts[1])));
                break;
            case "D":
                list.add(new Deadline(parts[2], Integer.parseInt(parts[1]), convertDateFormat(parts[3])));
                break;
            case "E":
                list.add(new Event(parts[2], Integer.parseInt(parts[1]), convertDateFormat(parts[3])));
                break;
            default:
            }
        }

        return list;
    }

    /**
     * Converts string indicating month to its corresponding integer as a string.
     * @param month string of month.
     * @return integer of month formatted as a string.
     */
    public String convertMonthToInt(String month) {
        switch (month) {
        case "Jan":
            return "01";
        case "Feb":
            return "02";
        case "Mar":
            return "03";
        case "Apr":
            return "04";
        case "May":
            return "05";
        case "Jun":
            return "06";
        case "Jul":
            return "07";
        case "Aug":
            return "08";
        case "Sep":
            return "09";
        case "Oct":
            return "10";
        case "Nov":
            return "11";
        default:
            return "12";
        }
    }

    /**
     * Converts format of date that can be parsed by java.time.LocalDateTime.
     *
     * @param date string of date.
     * @return formatted string that can be parsed by java.time.LocalDateTime.
     */
    public String convertDateFormat(String date) {
        String[] parts = date.split(" ");
        String year = parts[2];
        String month = convertMonthToInt(parts[0]);
        String day = String.format("%02d", Integer.parseInt(parts[1]));
        return year + "-" + month + "-" + day;
    }

    /**
     * Writes string to file.
     * @param textToAdd test to be written to file.
     * @throws IOException named file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be open for any other reason.
     */
    public void writeToFile(String textToAdd) throws IOException  {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Formats and write task list to file.
     * @param list list of tasks.
     * @throws IOException named file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be open for any other reason.
     */
    public void rewriteFile(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < list.size(); i++) {
            String text = list.get(i).toString();
            fw.write(text + "\n");
        }
        fw.close();
    }

    /**
     * Appends string to the back of file.
     * @param textToAppend string to be added to back of file.
     * @throws IOException named file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be open for any other reason.
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }
}