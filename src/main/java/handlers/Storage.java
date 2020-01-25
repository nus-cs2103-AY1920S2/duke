package handlers;

import java.io.*;
import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import exceptions.DukeException;

public class Storage {
    protected String filePath;
    protected BufferedWriter wr;
    protected BufferedReader reader;

    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            wr = new BufferedWriter(new FileWriter(this.filePath));
            reader = new BufferedReader(new FileReader(this.filePath));
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public void save(ArrayList<Task> tasks) {
        try {
            //BufferedWriter wr =
            for (Task t : tasks) {
                wr.write(t.saveString());
                wr.newLine();
            }
            wr.close();
        } catch (IOException err){
            err.printStackTrace();
        }
    }

    public ArrayList<Task> load() throws DukeException{
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            String l;
            while ((l = reader.readLine()) != null){
                String[] data = l.split("|");
                Task t = null;
                switch(data[0]) {
                    case "T":
                        t = new Todo(data[1], data[2]);
                        break;
                    case "D":
                        t = new Deadline(data[1], data[2], data[3]);
                        break;
                    case "E":
                        t = new Event(data[1], data[2], data[3]);
                        break;
                }
                if (t != null) {
                    tasks.add(t);
                }
            }
            reader.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
        return tasks;
    }
}
