package seedu.duke;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    String path;
    List<Task> tasks = new ArrayList<>();

    public Storage(String path) throws IOException {
        this.path = path;
    }

    protected List<Task> load() throws IOException, InvalidTaskInputException, InvalidDateException {
        String inputLine;
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        while ((inputLine = br.readLine()) != null) {
            String[] input = inputLine.split("\\|", 3);
            String type = input[0].trim();
            String doneStatus = input[1].trim();
            String desc = input[2].trim();
            if (type.equalsIgnoreCase("T")) {
                addTodo(desc, doneStatus);
            } else if (type.equalsIgnoreCase("D")) {
                addDeadline(desc, doneStatus);
            } else if (type.equalsIgnoreCase("E")) {
                addEvent(desc, doneStatus);
            } else {
                throw new InvalidTaskInputException();
            }
        }
        return tasks;
    }

    protected void addTodo(String desc, String doneStatus) throws IOException {
        Task todo = new Todo(desc);
        if (doneStatus.equalsIgnoreCase("Y")) {
            todo.markAsDone();
        }
        tasks.add(todo);
    }

    protected void addDeadline(String desc, String doneStatus)
            throws InvalidTaskInputException, InvalidDateException {
        String[] descs = desc.split(" /by |\\|") ;
        if (descs.length == 1) { // invalid Deadline input format
            throw new InvalidTaskInputException();
        }

        String deadlineDesc = descs[0].trim();
        String deadlineTime = descs[1].trim();
        LocalDate formattedDeadlineTime = null;

        if (isValidDate(deadlineTime)) {
            formattedDeadlineTime = LocalDate.parse(deadlineTime);
        } else {
            throw new InvalidDateException();
        }

        Task deadline = new Deadline(deadlineDesc, formattedDeadlineTime);

        if (doneStatus.equalsIgnoreCase("Y")) {
            deadline.markAsDone();
        }
        tasks.add(deadline);
    }

    protected void addEvent(String desc, String doneStatus)
            throws InvalidTaskInputException, InvalidDateException {
        String[] descs = desc.split(" /at |\\|");
        if (descs.length == 1) { // invalid Event input format
            throw new InvalidTaskInputException();
        }
        String eventDesc = descs[0].trim();
        String eventTime = descs[1].trim();
        LocalDate formattedEventTime = null;
        if (isValidDate(eventTime)) {
            formattedEventTime = LocalDate.parse(eventTime);
        } else {
            throw new InvalidDateException();
        }
        Task event = new Event(eventDesc, formattedEventTime);

        if (doneStatus.equalsIgnoreCase("Y")) {
            event.markAsDone();
        }

        tasks.add(event);
    }

    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    protected void addToStorage(Task task) throws IOException {
        File file = new File(path);
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        String data = "";
        data += task.getType() + " | " + task.getStatusIcon() + " | " + task.getDescription();
        if (task instanceof Deadline || task instanceof Event) {
            data += " | " + task.getTime();
        }

        bw.write("\n" + data);
        bw.close();
        fw.close();
    }

    protected void changeToStorage(int index) throws IOException {
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String data = "";
        String line = null;
        int counter = 1;
        while ((line = br.readLine()) != null) {
            if (counter == index) {
                line = line.substring(0, 4) + "Y" + line.substring(5, line.length());
            }

            if (counter == 1) {
                data += line;
            } else {
                data += "\n" + line;
            }
            counter++;
        }

        FileOutputStream fileOutputStr = new FileOutputStream(path);
        fileOutputStr.write(data.getBytes());
        fileOutputStr.close();
    }

    protected void deleteInStorage(int index) throws IOException {
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String data = "";
        String line = null;
        int counter = 1;
        while ((line = br.readLine()) != null) {
            if (counter != index) {
                if (counter == 1 || counter == index + 1) {
                    data += line;
                } else {
                    data += "\n" + line;
                }
            }
            counter++;
        }

        FileOutputStream fileOutputStr = new FileOutputStream(path);
        fileOutputStr.write(data.getBytes());
        fileOutputStr.close();
    }
}
