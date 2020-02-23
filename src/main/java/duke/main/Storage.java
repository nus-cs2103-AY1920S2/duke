package duke.main;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {


    public static String dataPath = "data/duke.txt";


    public TaskList load() throws IOException {

        File f = new File(dataPath);

        Scanner s;

        try {
            s = new Scanner(f);
        } catch(FileNotFoundException e){
            new File("data").mkdir();
            f.createNewFile();
            return new TaskList(new ArrayList<Task>());
        }

        TaskList tasks = new TaskList(new ArrayList<Task>());
        while(s.hasNext()) {
            // convert string back to task
            String taskString = s.nextLine();
            String type = taskString.substring(0,3);
            boolean isDone = !taskString.substring(3,6).equals("[✘]");
            String desc = "";
            String at = "";
            String by = "";
            switch (type) {
                case "[T]":
                    desc = taskString.substring(taskString.indexOf(" ") + 1);
                    Todo todo = new Todo(desc);
                    tasks.add(todo);
                    break;
                case "[D]":
                    desc = taskString.substring(taskString.indexOf(" ") + 1, taskString.indexOf(" (by"));
                    by = taskString.substring(taskString.indexOf("(by:") + 5, taskString.length() - 1);
                    LocalDate d = LocalDate.parse(by);
                    d.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
                    Deadline deadline = new Deadline(desc, d.toString());
                    tasks.add(deadline);
                    break;
                case "[E]":
                    desc = taskString.substring(taskString.indexOf(" ") + 1, taskString.indexOf(" (at"));
                    at = taskString.substring(taskString.indexOf("(at:") + 5, taskString.length() - 1);
                    Event event = new Event(desc, at);
                    tasks.add(event);
                    break;
            }

        }
        return tasks;
    }

    public void updateData(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(dataPath); // to always append to file
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).toString() + "\n");
        }
        fw.close();
    }








}
