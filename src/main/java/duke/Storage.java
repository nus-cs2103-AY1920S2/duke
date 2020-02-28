package duke;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates Storage object
 */
public class Storage {
    private String directory = System.getProperty("user.dir");
    private String fileName = "filedata.txt";
    private Path path = Paths.get(directory, fileName);
    private ArrayList<Task> lst = new ArrayList<Task>();

    public Storage() {};

    /**
     * Reads file stored in hard drive
     * @return ArrayList of Tasks stored
     */
    public ArrayList<Task> readFile() {//returns tasklist + task number (appended to the back)
        int tasks = 0;
        try {
            List<String> input = Files.readAllLines(path);
            for (int i = 0; i < input.size(); i++) {
                tasks++;
                String[] temp = input.get(i).split("\\|");
                //is there a way to add different child class instances without 3 diff if-else statement
                if (temp[0].equals("T")) {
                    Todo todo = new Todo(temp[2]);
                    if (todo.getStatusIcon().equals("Y")) {
                        todo.doAct();
                        tasks--;
                    }
                    lst.add(todo);
                } else {
                    LocalDate localdate = setDate(temp);
                    if (temp[0].equals("D")) {
                        Deadline deadline = new Deadline(temp[2], localdate);
                        addtask(deadline, tasks);
                        deadline.setTime(temp[3]);
                        lst.add(deadline);
                    } else {
                        Event event = new Event(temp[2], localdate);
                        addtask(event, tasks);
                        event.setTime(temp[3]);
                        lst.add(event);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lst;
    }

    /**
     * Overwrites new data to stored file in hard drive
     * @param lst from Tasklist from ByeCommand
     */
    public void writeToFile(TaskList lst){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (int i = 0; i < lst.getSize(); i++) {
                Task task = lst.getTask(i);
                if (task instanceof Todo) {
                    writer.write("T" + "|" + task.getStatusIcon() + "|" + task.getD());
                    writer.newLine();
                } else {
                    writer.write(task.getType() + "|" + task.getStatusIcon() + "|" + task.getD() + "|" + task.getTime());
                    writer.newLine();
                }

            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Formats command to fit date into LocalDate format
     * @param temp String array that contains date
     * @return LocalDate object or Deadline/Event object
     */
    private LocalDate setDate(String[] temp) {
        String[] temp1 = temp[3].split(" ");
        String[] temp2 = temp1[1].split("/");
        int year = Integer.parseInt(temp2[2]);
        int month = Integer.parseInt(temp2[1]);
        int day = Integer.parseInt(temp2[0]);
        int time = Integer.parseInt(temp1[2]);
        LocalDate localdate = LocalDate.of(year, month, day);
        return localdate;
    }

    /**
     * Increase task number if Task is done
     * @param task Task from file
     * @param tasks integer containing task number
     */
    private void addtask(Task task, int tasks) {
        if (task.getStatusIcon().equals("Y")) {
            task.doAct();
            tasks--;
        }
    }
}