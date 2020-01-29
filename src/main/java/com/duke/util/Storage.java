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

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
