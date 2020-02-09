package main.java;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class TaskList{

    protected String filepath;
    protected ArrayList<Task> TL;

    public TaskList(String filepath, ArrayList<Task> TL) {
        this.filepath = filepath;
        this.TL = TL;
    }

    public TaskList() {

    }

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