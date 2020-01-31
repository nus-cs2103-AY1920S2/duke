package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {

    /***
     * Start reading the data when first launch
     */
    protected void startReading() {
        try {
            printFileContents("data/duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /***
     * Read file data and process its content
     * @param filePath
     * @throws FileNotFoundException
     */
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String sentence = s.nextLine();
            String type = sentence.substring(4, 5);
            boolean done = true;
            if (sentence.substring(7, 8).equals("x")) {
                done = false;
            }
            if (type.equals("T")) {
                String substr = sentence.substring(10);
                ToDo task = new ToDo(substr, done);
                Duke.commandList.add(task);

            } else if (type.equals("D")) {
                String substr = sentence.substring(10);
                String[] deadlineSplit = substr.split(" \\| by: ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                LocalDate date = LocalDate.parse(deadlineSplit[1], formatter);
                Deadline task = new Deadline(deadlineSplit[0], date, done);
                Duke.commandList.add(task);

            } else {
                String substr = sentence.substring(10);
                String[] eventSplit = substr.split(" \\| at: ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                LocalDate date = LocalDate.parse(eventSplit[1], formatter);
                Event task = new Event(eventSplit[0], date, done);
                Duke.commandList.add(task);

            }

        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /***
     * Update the content of the list in to the txt file
     */
    protected static void writeList() {
        String file2 = "data/duke.txt";
        String towrite = "";
        try {
            if (Duke.commandList.size() > 0) {
                for (int i = 0; i < Duke.commandList.size(); i++) {
                    int a = i + 1;
                    towrite+=a + ". " + Duke.commandList.get(i);
                    writeToFile(file2, towrite);
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
