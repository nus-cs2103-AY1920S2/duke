package com.duke.util;

import com.duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Represents a handler that interacts with the storage file in the hard-drive
 * through both loading and saving data into and from the current Duke session.
 */
public class Storage {
    private String filePath;

    /**
     * Contructs a <code>Storage</code> object with the given filePath.
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data associated to the task list from the hard-drive into the Duke session.
     * @return An <code>ArrayList</code> object that contains the all the tasks on the list
     * and their relevant status.
     * @throws FileNotFoundException If the file path provided is invalid.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        if (!s.hasNext()) {
            return new ArrayList<>();
        } else {
            ArrayList<Task> out = new ArrayList<>();
            while (s.hasNext()) {
                StringTokenizer st = new StringTokenizer(s.nextLine(),"|");
                String type = st.nextToken();
                int status = Integer.parseInt(st.nextToken());
                Task tba;
                if (type.equals("T")) {
                    tba = new Todo(st.nextToken());
                } else if (type.equals("D")) {
                    tba = new Deadline(st.nextToken(), st.nextToken());
                } else {
                    tba = new Event(st.nextToken(), st.nextToken());
                }
                if (status == 1) {
                    tba.isDone = true;
                }
                out.add(tba);
            }
            return out;
        }
    }

    /**
     * Saves the task data in the current Duke session into the hard-drive.
     * @param tl The task list that contains the updated task data.
     * @throws IOException When the file path provided is invalid.
     */
    public void save(TaskList tl) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        List<Task> ts = tl.tasks;
        int count= ts.size();
        if (count == 0) {
            fw.write("");
        } else {
            for (int i = 0; i < count - 1; i++) {
                fw.write(ts.get(i).generateWriteFormat());
                fw.write('\n');
            }
            fw.write(ts.get(count - 1).generateWriteFormat());

            fw.close();
        }
    }
}
