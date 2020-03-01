package grapie.functions;

import grapie.exceptions.GrapieExceptions;
import grapie.tasks.Deadline;
import grapie.tasks.Event;
import grapie.tasks.Task;
import grapie.tasks.Todo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//need edit3ing
public class Storage {
    private String filePath;
    List<Task> storingList;

    /**
     * Loads data from persistent storage
     *
     * @param filePath The filepath to get the Hard Disk data from.
     * @throws IOException Throws exception.
     */
    public Storage(String filePath, List<Task> storingList) throws IOException {
        // deals with loading tasks from the file and saving tasks in the dukeStorage.txt file.

        this.filePath = filePath;
        try {
            File taskFile = new File("data/dukeStorage.txt");
            if (!taskFile.exists()) {
                taskFile.getParentFile().mkdirs();
                taskFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.storingList = storingList;
    }

    /**
     * Load hard disk information into an ArrayList for Grapie.Command.TaskList class to use.
     *
     * @return Returns an ArrayList of Grapie.Tasks.Task, loaded from the Hard Disk.
     * @throws FileNotFoundException Throws exception.
     * @throws GrapieExceptions      Throws exception.
     */
    public List<Task> load() throws GrapieExceptions, FileNotFoundException {

        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            /*
            //tags
            T | 1 | activity | tag | date

            T | 1 | read book
            D | 0 | return book | June 6th
            E | 0 | project meeting | Aug 6th 2-4pm
            T | 1 | join sports club
            */

            String[] dataSplited = data.split("\\|");

            //trim off empty spaces at front and back of string
            for (int i = 0; i < dataSplited.length; i++) {
                dataSplited[i] = dataSplited[i].trim();
            }

            if (dataSplited.length == 4) {
                //is a todo
                Task task = new Todo(dataSplited[2]);
                if (dataSplited[1].equals("O")) {
                    task.isDone = true;
                }

                if (!dataSplited[3].trim().equals("")) {
                    String[] tagSplit = dataSplited[3].trim().split("\\s+");
                    for (int i = 0; i < tagSplit.length; i++) {
                        task.tagList.add(tagSplit[i]);
                    }
                }

                storingList.add(task);
            } else if (dataSplited.length == 5) {
                //is a event or deadline
                if (dataSplited[0].equals("E")) {
                    //event
                    Event task = new Event(dataSplited[2], dataSplited[4]);
                    if (dataSplited[1].equals("O")) {
                        task.isDone = true;
                    }

                    if (!dataSplited[3].trim().equals("")) {
                        String[] tagSplit = dataSplited[3].trim().split("\\s+");
                        for (int i = 0; i < tagSplit.length; i++) {
                            task.tagList.add(tagSplit[i]);
                        }
                    }
                    storingList.add(task);

                } else {
                    //deadline
                    Deadline task = new Deadline(dataSplited[2], dataSplited[4]);
                    if (dataSplited[1].equals("O")) {
                        task.isDone = true;
                    }
                    if (!dataSplited[3].trim().equals("")) {
                        String[] tagSplit = dataSplited[3].trim().split("\\s+");
                        for (int i = 0; i < tagSplit.length; i++) {
                            task.tagList.add(tagSplit[i]);
                        }
                    }

                    storingList.add(task);
                }
            }
        }
        return storingList; //return the filled list
    }

    /**
     * Convert task into correct format, and store in dukeStorage.txt file.
     *
     * @param task The task to be converted into the new format.
     * @param type Grapie.Tasks.Todo, Grapie.Tasks.Event or Grapie.Tasks.Deadline.
     * @param time The date and time for the Tasks.
     * @throws IOException Throws exception.
     */
    public void convertAndStore(Task task, String type, String time) throws IOException {
        String doneOrNotDone = "";
        if (task.isDone) {
            doneOrNotDone += "O";
        } else {
            doneOrNotDone += "X";
        }

        String tags = "";
        int numTags = task.tagList.size();
        if (numTags != 0) {
            for (int i = 0; i < numTags; i++) {
                tags += " " + task.tagList.get(i);
            }
        }


        String newDescription = "";
        if (type.equals("T")) {
            //todo
            newDescription += type + " | " + doneOrNotDone + " | " + task.description + " | " + tags;
        } else {
            //event & deadline
            newDescription += type + " | " + doneOrNotDone + " | " + task.description + " | " + tags + " | "
                    + time;
        }

        File file = new File(filePath);
        FileWriter fr = new FileWriter(file, true);

        if (file.length() == 0) {
            fr.write(newDescription);
        } else {
            fr.write("\n" + newDescription);
        }

        fr.close();
    }

    /**
     * Modify the done value in the dukeStorage.txt file.
     * Change not done (X) into done (O)
     *
     * @param lineNumber The line number to be deleted from hard disk.
     * @throws IOException Throws exception.
     */
    public void replaceLineFromHardDisk(int lineNumber) throws IOException {
        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);

        String newData = "";
        int counter = 1;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            if (counter == lineNumber) {
                data = data.substring(0, 4) + "O" + data.substring(5, data.length());
            }

            if (counter == 1) {
                newData += data;
            } else {
                newData += "\n" + data;
            }

            counter++;
        }

        FileOutputStream fileOut = new FileOutputStream(filePath);
        fileOut.write(newData.getBytes());
        fileOut.close();
    }

    /**
     * Store the tags into the hard disk.
     *
     * @param lineNumber The line number to store the tag at.
     * @param tag        The tag to be stored.
     * @throws IOException Throw away exception.
     */
    public void addTagToDisk(int lineNumber, String tag) throws IOException {
        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);

        String newData = "";
        int counter = 1;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            if (counter == lineNumber) {
                String[] dataSplited = data.split("\\|");

                if (dataSplited.length == 4) {
                    data = dataSplited[0] + "|" + dataSplited[1] + "|" + dataSplited[2] + "|" + dataSplited[3]
                            + " " + tag;
                } else {
                    data = dataSplited[0] + "|" + dataSplited[1] + "|" + dataSplited[2] + "|" + dataSplited[3]
                            + " " + tag + "|" + dataSplited[4];
                }
            }

            if (counter == 1) {
                newData += data;
            } else {
                newData += "\n" + data;
            }

            counter++;
        }

        FileOutputStream fileOut = new FileOutputStream(filePath);
        fileOut.write(newData.getBytes());
        fileOut.close();
    }

    /**
     * Delete the corresponding line from dukeStorage.txt file according to the task deleted.
     *
     * @param lineNumber The line number to be deleted from hard disk.
     * @throws IOException Throws exception.
     */
    public void deleteLineFromHardDisk(int lineNumber) throws IOException {
        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);

        String newData = "";
        boolean isFirstLineDone = false;
        int counter = 1;

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            if (counter != lineNumber) {
                if (!isFirstLineDone) {
                    newData += data;
                    isFirstLineDone = true;
                } else {
                    newData += "\n" + data;
                }
            }
            counter++;
        }

        FileOutputStream fileOut = new FileOutputStream(filePath);
        fileOut.write(newData.getBytes());
        fileOut.close();
    }
}
