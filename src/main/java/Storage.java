import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * This class loads, stores and updates user's todo-list in a local txt file.
 */

public class Storage {
    File file = new File("output.txt");

    public Storage() {
    }


    /**
     * This method add a new item to the txt file.
     */
    public void addTxt(String s, Ui ui) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.append(s);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(ui.printIOerr());
        }
    }

    /**
     * This method add items to tasklist based based on the txt file.
     */
    public TaskList loadTxt(Ui ui) throws DateTimeParseException {
        TaskList list = new TaskList();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(" \\| ");
                if (data[0].equals("")) {
                    continue;
                }
                data[0] = data[0].trim();
                boolean done;
                String[] splitted;
                String[] tmp;
                splitted = data[0].split(" ", 2);
                tmp = splitted[1].split(" \\(", 2);
                if (data[0].charAt(4) == '0') {
                    done = true;
                } else {
                    done = false;
                }
                if (data[0].charAt(1) == 'E') {
                    tmp[1] = tmp[1].replaceAll("at: ","");
                    tmp[1] = tmp[1].replaceAll("\\)","");
                    list.addItem(new Event(tmp[0], LocalDate.parse(tmp[1]), done));
                } else if (data[0].charAt(1) == 'D') {
                    tmp[1] = tmp[1].replaceAll("by: ","");
                    tmp[1] = tmp[1].replaceAll("\\)","");
                    list.addItem(new Deadline(tmp[0], LocalDate.parse(tmp[1]), done));
                } else {
                    list.addItem(new Todo(splitted[1], done));
                }
            }
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
            BufferedReader file = new BufferedReader(new FileReader("output.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();
            inputStr = inputStr.replace(prev, now);
            FileOutputStream fileOut = new FileOutputStream("output.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        } catch (IOException e) {
            ui.printIOerr();
        }
    }
}
