import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public String filePath;

    Storage (String filePath) {
        this.filePath = filePath;
    }


    public ArrayList<Task> getTasksFromFile(String path) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<Task>(100);
        File file = new File(path);
        Scanner in = new Scanner(file);
        while(in.hasNextLine()) {
            String line = in.nextLine();
            String taskComponents[] = line.split(" \\| ");
            Task t;
            switch(taskComponents[0]) {
                case "T":
                    t = new ToDo(taskComponents[2], stringToBoolean(taskComponents[1]));
                    tasks.add(t);
                    break;
                case "D":
                    t = new Deadline(taskComponents[2], taskComponents[3], stringToBoolean(taskComponents[1]));
                    tasks.add(t);
                    break;
                case "E":
                    t = new Event(taskComponents[2], taskComponents[3], stringToBoolean(taskComponents[1]));
                    tasks.add(t);
                    break;
            }
        }
        return tasks;
    }

    public void addTasksToFile(ArrayList<Task> tasks) throws IOException {
        //File file = new File(filePath);
        File directory = new File("data");
        if (!directory.exists()){
            directory.mkdirs();
        }

        FileWriter fw = new FileWriter(filePath);
        int size = tasks.size();
        String str = "";
        Task t;
        for(int i = 0; i < size; ++i) {
            t = tasks.get(i);
            str = str + t.addToFile() + "\n";
        }
        fw.write(str);
        fw.close();
    }

    public static boolean stringToBoolean(String str) {
        if(str.equals("0")) {
            return false;
        } else {
            return true;
        }
    }
}
