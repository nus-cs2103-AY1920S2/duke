import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String filePath;
    public Storage(String filePath) throws IOException {
        // deals with loading tasks from the file and saving tasks in the file
        this.filePath = filePath;

        File file = new File(filePath);
        //boolean exists = tmpDir.exists();
        file.createNewFile();
    }

    public List<Task> load() throws FileNotFoundException, GrapieExceptions {
        List<Task> storingList = new ArrayList<>();

        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            //System.out.println("data is: " + data);
            //System.out.println(data);

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
                //System.out.println(task);

            } else if (dataSplited.length == 4) {
                //is a event or deadline
                if (dataSplited[0].equals("E")) {
                    //event
                    Event task = new Event(dataSplited[2], dataSplited[3]);
                    if (dataSplited[1].equals("O")) {
                        task.isDone = true;
                    }
                    storingList.add(task);
                    //System.out.println(task);

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

            newDescription += type + " | " + doneOrNotDone + " | " + task.description;
        } else {
            //event & deadline
//                String doneOrNotDone = description.substring(4,5);
//                String todoDescription = description.substring(7, description.length());

            newDescription += type + " | " + doneOrNotDone + " | " + task.description + " | " + time;
        }

        File file = new File(filePath);
        FileWriter fr = new FileWriter(file, true);

        //System.out.println("size of list is: " + storingList.size());
        if (file.length() == 0) {
            //System.out.println("nou");
            fr.write(newDescription);
        } else {
            fr.write("\n" + newDescription);

        }

        //System.out.println("size of list is: " + storingList.size());

        Scanner myReader = new Scanner(file);

        fr.close();
    }

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
