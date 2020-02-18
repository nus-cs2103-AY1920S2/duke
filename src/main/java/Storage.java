import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
/**
 * This class loads, stores and updates user's todo-list in a local txt file.
 */

public class Storage {
    public Storage() {
    }

    /**
     * This method add a new item to the txt file.
     */
    public void addTxt(String s, Ui ui) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter("data/output.txt", true);
            fileWriter.append(s);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            ui.printIOerr();
        }
    }

    /**
     * This method add items to tasklist based based on the txt file.
     */
    public TaskList loadTxt(Ui ui) throws DateTimeParseException {
        TaskList list = new TaskList();
        try {
            BufferedReader file = new BufferedReader(new FileReader("data/output.txt"));
            String line;
            while ((line = file.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }
                line = line.trim();
                boolean done;
                String[] splitted;
                String[] tmp;
                splitted = line.split(" ", 2);
                tmp = splitted[1].split(" ", 2);

                if (line.charAt(4) == '\u2713') {
                    done = true;
                } else {
                    done = false;
                }
                if (line.charAt(1) == 'E') {
                    tmp[1] = tmp[1].replaceAll("\\(at: ","");
                    tmp[1] = tmp[1].replaceAll("\\)","");
                    list.addItem(new Event(tmp[0], LocalDate.parse(tmp[1]), done));
                } else if (line.charAt(1) == 'D') {
                    tmp[1] = tmp[1].replaceAll("\\(by: ","");
                    tmp[1] = tmp[1].replaceAll("\\)","");
                    list.addItem(new Deadline(tmp[0], LocalDate.parse(tmp[1]), done));
                } else {
                    list.addItem(new Todo(splitted[1], done));
                }
            }
            ui.printLoad();
            file.close();
        } catch (DateTimeParseException e) {
            ui.printDateErr();
        } catch (IOException e) {
            ui.printIOerr();
        }
        return list;
    }

    /**
     * This method updates items from the txt file based on user input.
     */
    public void updateTxt(String prev, String now, Ui ui) throws IOException {
        try {
            BufferedReader file = new BufferedReader(new FileReader("data/output.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();
            inputStr = inputStr.replace(prev, now);
            FileOutputStream fileOut = new FileOutputStream("data/output.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        } catch (IOException e) {
            ui.printIOerr();
        }
    }
}
