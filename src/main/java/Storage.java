import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String filePath;

    /**
     * Constructor for Storage class
     *
     * @param filePath The filepath to get the Hard Disk data from.
     * @throws IOException
     */
    public Storage(String filePath) throws IOException {
        // deals with loading tasks from the file and saving tasks in the dukeStorage.txt file.
        this.filePath = filePath;
        File file = new File(filePath);
        file.createNewFile();
    }

    /**
     * Load hard disk information into an ArrayList for TaskList class to use.
     *
     * @return Returns an ArrayList of Task, loaded from the Hard Disk.
     * @throws FileNotFoundException
     * @throws GrapieExceptions
     */
    public List<Task> load() throws FileNotFoundException, GrapieExceptions {
        List<Task> storingList = new ArrayList<>();

        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            /*
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
            //System.out.println(dataSplited.length);

            if (dataSplited.length == 3) {
                //is a todo
                Task task = new Todo(dataSplited[2]);
                if (dataSplited[1].equals("O")) {
                    task.isDone = true;
                }
                storingList.add(task);

            } else if (dataSplited.length == 4) {
                //is a event or deadline
                if (dataSplited[0].equals("E")) {
                    //event
                    Event task = new Event(dataSplited[2], dataSplited[3]);
                    if (dataSplited[1].equals("O")) {
                        task.isDone = true;
                    }
                    storingList.add(task);

                } else {
                    //deadline
                    Deadline task = new Deadline(dataSplited[2], dataSplited[3]);
                    if (dataSplited[1].equals("O")) {
                        task.isDone = true;
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
     * @param type Todo, Event or Deadline.
     * @param time The date and time for the Tasks.
     * @throws IOException
     */
    public void convertToHardDiskFormatAndStore(Task task, String type, String time) throws IOException {
            /*
            T | 1 | read book
            D | 0 | return book | June 6th
            E | 0 | project meeting | Aug 6th 2-4pm
            T | 1 | join sports club
            */

            /*
            [T][O] read book
            [D][X] return book (by: June 6th)
            [E][X] project meeting (at: Aug 6th 2-4pm)
            [T][O] join sports club
             */

        String doneOrNotDone = "";
        if (task.isDone) {
            doneOrNotDone += "O";
        } else {
            doneOrNotDone += "X";
        }

        String newDescription = "";
        if (type.equals("T")) {
            //todo
            newDescription += type + " | " + doneOrNotDone + " | " + task.description;
        } else {
            //event & deadline
            newDescription += type + " | " + doneOrNotDone + " | " + task.description + " | " + time;
        }

        File file = new File(filePath);
        FileWriter fr = new FileWriter(file, true);

        //System.out.println("size of list is: " + storingList.size());
        if (file.length() == 0) {
            fr.write(newDescription);
        } else {
            fr.write("\n" + newDescription);
        }

        //System.out.println("size of list is: " + storingList.size());
        //Scanner myReader = new Scanner(file);
        fr.close();
    }

    /**
     * Modify the done value in the dukeStorage.txt file.
     * Change not done (X) into done (O)
     *
     * @param lineNumber The line number to be deleted from hard disk.
     * @throws IOException
     */
    public void replaceLineFromHardDisk(int lineNumber) throws IOException {
        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);

        String newData = "";
        int counter = 1;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            //System.out.println(data);

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

//        System.out.println(newData);
        FileOutputStream fileOut = new FileOutputStream(filePath);
        fileOut.write(newData.getBytes());
        fileOut.close();
    }

    /**
     * Delete the corresponding line from dukeStorage.txt file according to the task deleted.
     *
     * @param lineNumber The line number to be deleted from hard disk.
     * @throws IOException
     */
    public void deleteLineFromHardDisk(int lineNumber) throws IOException {
        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);

        String newData = "";
        boolean firstLineDone = false;
        int counter = 1;

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            if (counter == lineNumber) {
                counter++;
            } else {
                if (!firstLineDone) {
                    newData += data;
                    firstLineDone = true;
                } else {
                    newData += "\n" + data;
                }

                counter++;
            }
        }

//        System.out.println(newData);

        FileOutputStream fileOut = new FileOutputStream(filePath);
        fileOut.write(newData.getBytes());
        fileOut.close();
    }
}
