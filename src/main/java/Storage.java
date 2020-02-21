import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;

/**
 * Represents a Storage which stores tasks. Should the application close, tasks in the list will be saved
 * until the next running of the application.
 */
public class Storage {

    private final String fileName = "duke_save.txt";
    private int latestIndex = 0;
    private BufferedWriter bw;

    /**
     * Reads the text file holding a record of the list of tasks in string format, adding them into an Arraylist.
     * Activates when the application first runs. A new text file is created if there is no
     * record of tasks to be read.
     *
     * @return an arraylist holding all the tasks
     */
    public ArrayList<Task> readFile() {
        ArrayList<Task> list = new ArrayList<>();
        String line;
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(fileName));
            try {
                while ((line = br.readLine()) != null) {
                    list = processLines(line, list);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            try {
                File file = new File(fileName);
                file.createNewFile();
            } catch (Exception f) {
                f.printStackTrace();
            }
        }
        return list;
    }

    /**
     * Processes each line from the text file containing the tasks in string format, creating
     * new Task objects and adding them into the arraylist.
     *
     * @param line the line of each task in string format.
     * @param list the list containing the tasks.
     * @return a list containing the tasks.
     */
    public ArrayList<Task> processLines(String line, ArrayList<Task> list) {

        int timeStartIndex = 0;
        int descEndIndex = 0;
        line = line.substring(3);
        String desc;
        String time;
        boolean done;
        String newLine = line.substring(7);
        if (line.charAt(1) == 'E') {
            done = line.charAt(4) == 'N' ? false : true;
            for (int i = 7; i < line.length() - 5; i++) {
                if (line.substring(i, i + 5).equals("(at: ")) {

                    timeStartIndex = i + 4;
                    descEndIndex = i - 1;
                    break;
                }
            }

            desc = line.substring(7, descEndIndex);
            time = line.substring(timeStartIndex);
            time = time.substring(0, time.length() - 1);

            Event event = new Event(desc, time, ++latestIndex);
            event.isDone = done;
            list.add(event);


        } else if (line.charAt(1) == 'D') {
            done = line.charAt(4) == 'N' ? false : true;

            for (int i = 7; i < line.length() - 5; i++) {
                if (line.substring(i, i + 5).equals("(by: ")) {

                    timeStartIndex = i + 4;
                    descEndIndex = i - 1;
                    break;
                }
            }

            desc = line.substring(7, descEndIndex);
            time = line.substring(timeStartIndex);
            time = time.substring(0, time.length() - 1);
            Deadline deadline = new Deadline(desc, time, ++latestIndex);
            deadline.isDone = done;
            list.add(deadline);



        } else if (line.charAt(1) == 'T') {
            done = line.charAt(4) == 'N' ? false : true;
            desc = newLine;

            Todo todo = new Todo(desc, ++latestIndex);
            list.add(todo);
            todo.isDone = done;


        } else {
            System.out.println("Weird thing found in text file...");
        }
        return list;
    }

    /**
     * Function to store the tasks in a text file when user wants to close the application.
     *
     * @param tasklist the tasklist containing a list of tasks.
     * @throws IOException If any issue with bufferedwriter.
     */
    public void writeFile(ArrayList<Task> tasklist) throws IOException {
        bw = new BufferedWriter(new FileWriter(fileName));
        for (Task t : tasklist) {
            bw.write(t.toString() + "\n");
        }
        bw.close();
    }


    public int returnInitialIndex() {
        return latestIndex;
    }
}