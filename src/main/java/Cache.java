package main.java;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;

public class Cache {

    private File file;

    public Cache(String filepath) {
        this.file = new File(filepath);
    }

    public ArrayList<Task> readFile() {
        ArrayList<Task> dukeList = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String[] command = s.nextLine().split("/");
                String taskType = command[0].trim();
                boolean isDone;
                isDone = command[1].trim().equals("\u2713");

                switch (taskType) {
                    case "deadline":
                        String description = command[2].trim();
                        String by = command[3].trim();
                        dukeList.add(new Deadline(description, by));
                        if (isDone) {
                            dukeList.get(dukeList.size() - 1).markAsDone();
                        }
                        break;
                    case "event":
                        description = command[2].trim();
                        String at = command[3].trim();
                        dukeList.add(new Event(description, at));
                        if (isDone) {
                            dukeList.get(dukeList.size() - 1).markAsDone();
                        }
                        break;
                    case "todo":
                        description = command[2].trim();
                        dukeList.add(new ToDo(description));
                        if (isDone) {
                            dukeList.get(dukeList.size() - 1).markAsDone();
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e1) {
            System.out.println(e1.getMessage());
        }

        return dukeList;
    }

    public void saveFile (ArrayList<Task> dukeList) throws IOException {

        try {
            FileWriter writer = new FileWriter(file);
            for (Task task : dukeList) {
                if (task instanceof ToDo) {
                    String str = "todo" + "/" + task.getStatusIcon() + "/" + task.getDescription() + "\n";
                    writer.write(str);
                } else if (task instanceof Event) {
                    String str = "event" + "/" + task.getStatusIcon() + "/"
                            + task.getDescription() + "/" + ((Event) (task)).getAt() + "\n";
                    writer.write(str);
                } else {
                    String str = "deadline" + " /" + task.getStatusIcon() + " /"
                            + task.getDescription() + "/ " + ((Deadline) (task)).getBy() + "\n";
                    writer.write(str);
                }
            }
            writer.close();
        } catch (IOException e2) {
            System.out.println(e2.getMessage());
        }

    }

}
