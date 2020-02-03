import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private File f;

    public Storage(String filePath) {
        this.f = new File(filePath);
    }

    public ArrayList<Task> load() throws DukeException {
        if ((!f.exists())) {
            throw new DukeException("☹ OOPS!!! There is no previous file stored! ☹ OOPS!!!");
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
