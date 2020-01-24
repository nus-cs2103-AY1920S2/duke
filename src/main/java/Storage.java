import java.io.*;
import java.io.File;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileWriter;

public class Storage {

    private String fileLocation = "./Data/Tasks.txt"; //hard-coded relative file location of stored tasks
    private File f;

    // initialization of Storage
    public Storage() {
        this.f = new File(this.fileLocation);
    }

    // return true or false if the file exists
    public boolean existFile() {
      return this.f.exists();
    }

    // read file and output last-updated tasklist
    public ArrayList<Task> getTaskFromStorage() {
        ArrayList<Task> result = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.f));
            String line;
            while ((line = (reader.readLine())) != null) {
                line = line.trim();
                result.add(this.translateTask(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist, please create Tasks.txt file under the Data directory!");
        } catch (IOException e) {
            System.out.println("Something occurred trying to read the file");
        }
        return result;
    }

    // method to translate task, to be ported to another class
    public Task translateTask(String line) {
        String[] contents = line.split("\\|");
        String taskType = contents[0];
        Task result;
        switch (taskType) {
            case "T":
                result = new ToDo(contents[2]); //as the file is hardcoded, the contents[1] should be the description
                break;
            case "E":
                result = new Event(contents[2], contents[3]);
                break;
            default:
                result = new Deadline(contents[2], contents[3]); // means deadline
        }
        if (contents[1].equals("1")) {
            result.setDone();
        }
        return result;
    }

    public void writeToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.f);
            int counter = 0;
            String result = "";
            for (Task t : tasks) {
                result += this.convertTaskToString(t);
                if (counter != tasks.size() - 1) {
                    result += "\n"; //next line
                }
            }
            fw.write(result);
            fw.close();
        } catch(IOException e) {
            System.out.println("Error occurred saving tasks!");
        }
    }

    public String convertTaskToString(Task t) {
        String taskType = t.Tasktype;
        String result = "";
        switch(taskType) {
            case "T":
                result += "T|" + this.returnDone(t) + "|" + t.getTaskname();
                break;
            case "E":
                Event e = (Event) t;
                result += "E|" + this.returnDone(t) + "|" + t.getTaskname() + "|" + e.DateTime;
                break;
            case "D":
                Deadline d = (Deadline) t;
                result += "D|" + this.returnDone(t) + "|" + t.getTaskname() + "|" + d.DateTime;
                break;
        }
        return result;
    }

    public String returnDone(Task t) {
        if (t.getDone()) {
            return "1";
        } else {
            return "0";
        }
    }
}
