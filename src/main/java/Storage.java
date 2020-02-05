import java.io.*;
import java.util.*;

/**
 * Represents a Storage which stores tasks. Should the application close, tasks in the list will be saved
 * until the next running of the application.
 */
public class Storage {

    private final String fileName = "../../../duke_save.txt";
    private int latest_index = 0;
    private BufferedWriter bw;

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
                System.out.println(e);
            }
        } catch (FileNotFoundException e) {
            try {
                File file = new File(fileName);
                file.createNewFile();
            } catch (Exception f) {
                System.out.println(f);
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

            Event event = new Event(desc, time, ++latest_index);
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
            Deadline deadline = new Deadline(desc, time, ++latest_index);
            deadline.isDone = done;
            list.add(deadline);



        } else if (line.charAt(1) == 'T'){
            boolean done = line.charAt(4) == 'N' ? false : true;
            desc = new_line;

            Todo todo = new Todo(desc, ++latest_index);
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
    public void writeFile(ArrayList<Task> temp_list) throws Exception {
        bw = new BufferedWriter(new FileWriter(fileName));
        for (Task t : temp_list) {
            bw.write(t.toString() + "\n");
        }
        bw.close();
    }


    public int returnInitialIndex() {
        return latest_index;
    }
}