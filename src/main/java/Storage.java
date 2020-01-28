import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {

    String path;

    Storage(String filePath) {
        this.path = filePath;
    }

    //if duke.txt is empty-> raise exception, else create list and return it
    ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<Task>();
        try (FileReader fr = new FileReader(path)) {
            Scanner sc = new Scanner(fr);
            if (!sc.hasNext()) {
                throw new DukeException("List is currently empty");
            }

            while (sc.hasNext()) {
                String line = sc.nextLine();
                Task ob = eval(line);
                list.add(ob);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    Task eval(String line)throws IOException {
        int i = line.indexOf("|");
        String type = line.substring(0, i);
        switch (type) {
        case "T":
            type = "todo";
            break;
        case "E":
            type = "event";
            break;
        case "D":
            type = "deadline";
            break;
        default:
        }
        String sentence;
        String task;

        int j = line.indexOf("|",i + 1);
        String done = line.substring(i + 1, j);
        int d = Integer.valueOf(done);
        i = j;
        if (!type.equals("todo")) {
            j = line.indexOf("|",i + 1);
            task = line.substring(i + 1, j);
            String time = "";
            time = line.substring(j + 1);
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(time, format);
            if (type.equals(("deadline"))) {
                return new Deadline(task, dateTime, d);
            } else {
                sentence = type + " " + task + " /at " + time;
                return new Event(d, task, dateTime);
            }
        } else {
            task = line.substring(j + 1);
            sentence = type + " " + task;
            return new Todo(d, task);
        }
    }

    void save(TaskList tasks)throws IOException {
        try (FileWriter fw = new FileWriter(path,false)) {
            String s = "";
            for (int i = 0; i < tasks.list.size(); i++) {
                Task ob = tasks.list.get(i);
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                switch (ob.getType()) {
                case "deadline":
                    s = s + "D|" + ob.getDone() + "|"
                            + ob.getTaskName() + "|" + ((Deadline)ob).getBy().format(format) + "\n";
                    break;
                case "todo":
                    s = s + "T|" + ob.getDone()
                            + "|" + ob.getTaskName() + "\n";
                    break;
                case "event":
                    s = s + "E|" + ob.getDone()
                            + "|" + ob.getTaskName() + "|" + ((Event)ob).getAt().format(format) + "\n";
                    break;
                default:
                }
            }
            fw.write(s);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
