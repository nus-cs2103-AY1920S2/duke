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

    public ArrayList<Task> readFile() {
        ArrayList<Task> list = new ArrayList<>();
        String line;
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(fileName));
            try {
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
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

/*
    /**
     * Returns lateral location of the specified position.
     * If the position is unset, NaN is returned.
     *
     * @param x  X coordinate of position.
     * @param y Y coordinate of position.
     * @param zone Zone of position.
     * @return Lateral location.
     * @throws IllegalArgumentException  If zone is <= 0.
     */
    public ArrayList<Task> processLines(String line, ArrayList<Task> list) {

        int time_start_index = 0;
        int desc_end_index = 0;
        line = line.substring(3);
        String desc;
        String time;
        String new_line = line.substring(7);
        if (line.charAt(1) == 'E') {
            boolean done = line.charAt(4) == 'N' ? false : true;
            for (int i = 7; i < line.length() - 5; i++) {
                if (line.substring(i, i + 5).equals("(at: ")) {

                    time_start_index = i + 4;
                    desc_end_index = i - 1;
                    break;
                }
            }

            desc = line.substring(7, desc_end_index);
            time = line.substring(time_start_index);
            time = time.substring(0, time.length() - 1);

            Event event = new Event(desc, time, ++latestIndex);
            event.isDone = done;
            list.add(event);


        } else if (line.charAt(1) == 'D') {
            boolean done = line.charAt(4) == 'N' ? false : true;

            for (int i = 7; i < line.length() - 5; i++) {
                if (line.substring(i, i + 5).equals("(by: ")) {

                    time_start_index = i + 4;
                    desc_end_index = i - 1;
                    break;
                }
            }

            desc = line.substring(7, desc_end_index);
            time = line.substring(time_start_index);
            time = time.substring(0, time.length() - 1);
            Deadline deadline = new Deadline(desc, time, ++latestIndex);
            deadline.isDone = done;
            list.add(deadline);



        } else if (line.charAt(1) == 'T'){
            boolean done = line.charAt(4) == 'N' ? false : true;
            desc = new_line;

            Todo todo = new Todo(desc, ++latestIndex);
            list.add(todo);
            todo.isDone = done;


        } else {
            System.out.println("Weird thing found in text file...");
        }
        return list;
    }

    /*
    /**
     * Returns lateral location of the specified position.
     * If the position is unset, NaN is returned.
     *
     * @param x  X coordinate of position.
     * @param y Y coordinate of position.
     * @param zone Zone of position.
     * @return Lateral location.
     * @throws IllegalArgumentException  If zone is <= 0.
     */
    public void writeFile(ArrayList<Task> temp_list) throws IOException {
        bw = new BufferedWriter(new FileWriter(fileName));
        for (Task t : temp_list) {
            bw.write(t.toString() + "\n");
        }
        bw.close();
    }


    public int returnInitialIndex() {
        return latestIndex;
    }
}