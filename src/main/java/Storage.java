import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 * Storage loads tasks from the file at <code>filePath</code> upon startup
 * and saves tasks into the file at <code>filePath</code>
 * every time there is changes to the to do list.
 */
public class Storage {
    private File f;

    /**
     * Constructor for Storage class.
     *
     * @param filePath relative directory of the file.
     */
    public Storage(String filePath) {
        this.f = new File(filePath);
    }

    /**
     * Load the data from the saved file into the current to do list. load() is called upon startup.
     * The file is read as <code>String</code> and being translated into a <code>Task</code>.
     *
     * @return ArrayList that contains all Task in the current saved file.
     * @throws DukeException Exception is thrown when there is no file with the <code>filePath</code>.
     */
    public ArrayList<Task> load() throws DukeException {
        if ((!f.exists())) {
            throw new DukeException("☹ OOPS!!! Error: There is no previous file stored! ☹ OOPS!!!");
        } else {
            ArrayList<String> stringTasks = new ArrayList<String>();
            ArrayList<Task> tasks = new ArrayList<Task>();
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String text;
                while ((text = br.readLine()) != null) {
                    stringTasks.add(text);
                }
            } catch (IOException e) {
                System.out.println("Error: no File detected. " + e.getMessage());
            }

            for (int i = 0; i < stringTasks.size(); i++) {
                String task = stringTasks.get(i);
                String type = task.charAt(1) + "";
                switch (type) {
                    case "T": {
                        if (task.charAt(4) == 'D') {
                            // means task done
                            task = task.substring(10);
                            Todo todo = new Todo(task);
                            todo.markAsDone();
                            tasks.add(todo);
                        } else {
                            task = task.substring(12);
                            Todo todo = new Todo(task);
                            tasks.add(todo);
                        }
                        break;
                    }
                    case "E": {
                        String date = task.substring(task.indexOf("at:") + 4, task.length() - 1);
                        if (task.charAt(4) == 'D') {
                            // means task done
                            String event = task.substring(10, task.indexOf("at:") - 2);
                            Event e = new Event(event, date);
                            e.markAsDone();
                            tasks.add(e);
                        } else {
                            String event = task.substring(12, task.indexOf("at:") - 2);
                            Event e = new Event(event, date);
                            tasks.add(e);
                        }
                        break;
                    }
                    case "F": {
                        String hours = task.substring(task.indexOf("needs") + 6, task.indexOf("hour(s))") - 1);
                        if (task.charAt(4) == 'D') {
                            // means task done
                            String fixedTaskDescription = task.substring(10, task.indexOf("needs") - 2);
                            FixedDurationTask fdt = new FixedDurationTask(fixedTaskDescription, Integer.parseInt(hours));
                            fdt.markAsDone();
                            tasks.add(fdt);
                        } else {
                            String fixedTaskDescription = task.substring(12, task.indexOf("needs") - 2);
                            FixedDurationTask fdt = new FixedDurationTask(fixedTaskDescription, Integer.parseInt(hours));
                            tasks.add(fdt);
                        }
                        break;
                    }
                    case "D": {
                        String dateFromStorage = task.substring(task.indexOf("by:") + 4, task.length() - 1);
                        if (task.charAt(4) == 'D') {
                            // means task done
                            String deadline = task.substring(10, task.indexOf("by:") - 2);
                            Deadline d = new Deadline(deadline, dateConverter(dateFromStorage));
                            d.markAsDone();
                            tasks.add(d);
                        } else {
                            String deadline = task.substring(12, task.indexOf("by:") - 2);
                            Deadline d = new Deadline(deadline, dateConverter(dateFromStorage));
                            tasks.add(d);
                        }
                        break;
                    }
                    default: {
                        throw new DukeException("Wrong type of data in Storage!");
                    }
                }
            }
            return tasks;
        }
    }

    private LocalDate dateConverter(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        return localDate;
    }

    /**
     * Saves the current to do list into the file at <code>filePath</code>.
     * save(Tasklist tasklist) is called every time a new Command is run.
     *
     * @param tasklist Tasklist containing all the tasks and methods to modify the list.
     */
    public void save(TaskList tasklist) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            ArrayList<Task> tasks = tasklist.getTasks();
            for (Task task : tasks) {
                bw.append(task.toString());
                bw.append("\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error when saving file. " + e.getMessage());
        }

    }

}
