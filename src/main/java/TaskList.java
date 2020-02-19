package main.java;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 *
 * Class to keep track of tasks in local memory
 * */
public class TaskList{

    protected String filepath;
    protected ArrayList<Task> TL;

    /**
     *
     * Constructor
     * @param String filepath
     * @param ArrayList where tasks will be stored locally
     * */
    public TaskList(String filepath, ArrayList<Task> TL) {
        this.filepath = filepath;
        this.TL = TL;
    }

    /**
     *
     * Loads the data from storage into local memory
     * @throws FileNotFoundException
     *
     */
    public void loadFromStorage() throws FileNotFoundException{
        Scanner s = new Scanner(new File(filepath));
        while(s.hasNextLine()) {
            Task t = new Task(s.nextLine());
            TL.add(t);
        }
        s.close();
    }

    public ArrayList<Task> getTL() {
        return this.TL;
    }

    /**
     *
     * Gets a task from the arraylist from a keyword
     * @param String kw
     * @return String task
     * */
    public String getTaskFromKeyword(String kw) {
        String s = "";
        for(int i = 0; i < TL.size(); i++) {
            if(TL.get(i).toString().contains(kw)) {
                Task t = TL.get(i);
                s += t.toString() + "\n";
            }
        }
        return s;
    }

}