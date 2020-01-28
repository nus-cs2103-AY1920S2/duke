import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> list = new ArrayList<>();

        //add data in file into the arraylist in bot
        while(s.hasNext()) {
            String str = s.nextLine();
            String[] arr = str.split("\\|");

            //trim whitespaces
            for(int i = 0; i < arr.length; i++) {
                arr[i] = arr[i].trim();
            }

            if(arr[0].equals("T")) {
                Task task = new ToDo(arr[2]);

                if(arr[1].equals("1")) {
                    task.setIsDone();
                }

                list.add(task);

            } else if(arr[0].equals("D")) {
                Task task = new Deadline(arr[2], arr[3]);

                if(arr[1].equals("1")) {
                    task.setIsDone();
                }

                list.add(task);
            } else {
                Task task = new Event(arr[2], arr[3]);

                if(arr[1].equals("1")) {
                    task.setIsDone();
                }

                list.add(task);
            }
        }
        return list;
    }

    public void write(TaskList tasks) throws IOException {
        String textToAdd = "";

        //Create string of text to add to file
        for(int i = 0; i < tasks.getRecord().size(); i++) {
            Task current = tasks.getRecord().get(i);

            //Add type
            if(current instanceof ToDo) {
                textToAdd = textToAdd + "T | ";
            } else if(current instanceof Deadline) {
                textToAdd = textToAdd + "D | ";
            } else {
                textToAdd = textToAdd + "E | ";
            }

            //Add boolean
            if(current.getIsDone()) {
                textToAdd = textToAdd + "1 ";
            } else {
                textToAdd = textToAdd + "0 ";
            }

            //Add description
            if(current instanceof ToDo) {
                textToAdd = textToAdd + "| " + current.getDescription();
            } else if(current instanceof Deadline) {
                textToAdd = textToAdd + "| " + current.getDescription();
            } else {
                textToAdd = textToAdd + "| " + current.getDescription();
            }

            //Add at and by for events and deadlines
            if(current instanceof Deadline) {
                textToAdd = textToAdd + "| " + ((Deadline) current).by + "\n";
            } else if(current instanceof Event) {
                textToAdd = textToAdd + "| " + ((Event) current).at + "\n";
            } else {
                textToAdd = textToAdd + "\n";
            }

        }

        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}

