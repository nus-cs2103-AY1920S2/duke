import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File (filePath);
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String curr = s.nextLine();
                String type = curr.substring(0, 1);
                String isDone = curr.substring(2,3);
                Task add;
                if (type.equals("T")) {
                    add = new ToDo(curr.substring(4), isDone.equals("0")? false : true);
                } else {
                    String name = curr.substring(4, curr.indexOf('|', 4));
                    String date = curr.substring(curr.indexOf('|', 4) + 1);
                    if (type.equals("D")) {
                        add = new Deadline(name, Parser.extractDate(date), isDone.equals("0")? false : true);
                    } else {
                        add = new Event(name, Parser.extractDate(date), isDone.equals("0")? false : true);
                    }
                }
                tasks.add(add);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File for loading not found");
        }
        return tasks;
    }

    static void updateDrive(Task updatedTask) {
        File file = new File ("./data/duke.txt");
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.append(updatedTask.writeDrive());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            //System.out.println("Cannot find file");
        }
    }

    static void deleteDrive(int size) {
        ArrayList<String> tasks = new ArrayList<>();
        try {
            File file = new File("./data/duke.txt");
            FileReader fr = new FileReader(file);
            Scanner s = new Scanner(fr);
            int i = 1;
            while (s.hasNextLine()) {
                if (i == size) {
                    i++;
                    String line = s.nextLine();
                    continue;
                } else {
                    String line = s.nextLine();
                    tasks.add(line);
                    i++;
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
            for (String st : tasks) {
                writer.append(st);
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
        }
    }

}
