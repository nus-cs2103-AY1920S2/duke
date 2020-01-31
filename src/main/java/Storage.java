/**
 * load()
 * save()
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage{
    String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException{
        ArrayList<Task> arr = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while(s.hasNextLine()){
            String line = s.nextLine();
            String [] taskArr = line.split("/");
            Command type = Command.convert(taskArr[0]);
            boolean isCompleted = toBool(taskArr[1]);
            String task = taskArr[2];
            switch(type){
                case TODO:
                    try{
                        arr.add(new Todo(task, isCompleted));
                    }catch(Exception e){
                        System.out.println("load fail todo");
                    }
                    break;
                case DEADLINE:
                    try{
                        arr.add(new Deadline(task, taskArr[3], isCompleted));
                    }catch(Exception e){
                        System.out.println("load fail deadline");
                    }
                    break;
                case EVENT:
                    try{
                        arr.add(new Event(task, taskArr[3], isCompleted));
                    }catch(Exception e){
                        System.out.println("load fail event");
                    }
                    break;
            }
        }
        return arr;
    }

    private static boolean toBool(String com){
        if(com.equals("1")) return true;
        else return false;
    }

    private String toTxt(ArrayList<Task> taskArr){
        String main = "";
        for(Task x: taskArr){
            String type = "";
            int complete = x.getComplete()? 1: 0;
            String task = x.getTask();
            if(x instanceof Todo){
                type = "T";
            }else if(x instanceof Deadline){
                type = "D";
                Deadline d = (Deadline) x;
                task += "/" + d.getTiming();
            }else if(x instanceof Event){
                type = "E";
                Event e = (Event) x;
                task += "/" + e.getTiming();
            }
            main += type + "/" + complete + "/" + task +"\n";
        }
        return main;
    }

    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void save(ArrayList<Task> taskArr){
        try{
            writeToFile(toTxt(taskArr));
        }catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}