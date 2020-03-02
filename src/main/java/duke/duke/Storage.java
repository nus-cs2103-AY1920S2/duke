package duke;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates Storage object.
 */
public class Storage {
    private String directory = System.getProperty("user.dir");
    private String fileName = "tasks.txt";
    private Path path = Paths.get(directory, "data", fileName);
    private ArrayList<Task> lst = new ArrayList<Task>();

    public Storage() {
    }

    /**
     * Reads file stored in hard drive.
     * @return ArrayList of Tasks stored
     */
    public ArrayList<Task> readFile() { //returns tasklist + task number (appended to the back)
        int tasks = 0;
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            List<String> input = Files.readAllLines(path);
            //System.out.println(input.size());
            for (int i = 0; i < input.size(); i++) {
                //System.out.println(i);
                tasks++;
                String[] temp = input.get(i).split("\\|");
                //is there a way to add different child class instances without 3 diff if-else statement
                if (temp[0].equals("T")) {
                    //System.out.println("YO");
                    Todo todo = new Todo(temp[2]);
                    addtask(todo, temp[1], tasks);
                    lst.add(todo);
                    //System.out.println(lst.get(0));
                } else {
                    //System.out.println(temp[0] + "HHEY");
                    //System.out.println(temp[1]);
                    //System.out.println(temp[2]);
                    LocalDate localdate = setDate(temp[3]);
                    if (temp[0].equals("D")) {
                        Deadline deadline = new Deadline(temp[2], localdate);
                        addtask(deadline, temp[1], tasks);
                        deadline.setTime(temp[4]);
                        lst.add(deadline);
                    } else {
                        Event event = new Event(temp[2], localdate);
                        addtask(event, temp[1], tasks);
                        event.setTime(temp[4]);
                        lst.add(event);
                    }
                }
            }
            Todo todo = new Todo(Integer.toString(tasks)); //dummy Todo for tasksnum
            todo.doAct();
            lst.add(todo);//add in tasknum
            System.out.println("Added");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(lst.get(0));
        return lst;
    }

    /**
     * Overwrites new data to stored file in hard drive.
     * @param lst from Tasklist from ByeCommand
     */
    public void writeToFile(TaskList lst) {
        //System.out.println("HEYY");
        try {
            BufferedWriter writer = Files.newBufferedWriter(path);;
            for (int i = 0; i < lst.getSize(); i++) {
                //System.out.println("HEYY");
                Task task = lst.getTask(i);
                if (task instanceof Todo) {
                    writer.write("T" + "|" + task.getStatusIcon() + "|" + task.getD());
                    //System.out.println("HEYY");
                    writer.newLine();
                } else {
                    writer.write(task.getType() + "|" + task.getStatusIcon() + "|"
                            + task.getD() + "|" + task.getRawDate() + "|" + task.getRawTime());
                    writer.newLine();
                }
            }
            writer.flush();
            writer.close();
            //System.out.println("HEYY");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Formats command to fit date into LocalDate format.
     * @param date String array that contains date
     * @return LocalDate object or Deadline/Event object
     */
    private LocalDate setDate(String date) {
        String[] temp2 = date.split("/");
        //System.out.print(date);
        int year = Integer.parseInt(temp2[2]);
        int month = Integer.parseInt(temp2[1]);
        int day = Integer.parseInt(temp2[0]);
        LocalDate localdate = LocalDate.of(year, month, day);
        return localdate;
    }

    /**
     * Increase task number if Task is done.
     * @param task Task from file
     * @param tasks integer containing task number
     */
    private void addtask(Task task, String status, int tasks) {
        if (status.equals("O")) {
            System.out.println("Done");
            task.doAct();
            tasks--;
            System.out.println(tasks + "fuck");
        }
    }
}