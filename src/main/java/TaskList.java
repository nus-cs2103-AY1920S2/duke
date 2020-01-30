import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

class TaskList {
    /** List of tasks; assume no more than 100 tasks. */
    private ArrayList<Task> lTasks = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public boolean loadFromFile(Path path) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fileIn = null;
        ObjectInputStream objIn = null;
        ArrayList<Task> tasks = null;
        boolean loadSuccessful = false;
        try {
            fileIn = new FileInputStream(new File(path.toString())); // read serialized object from file as a String
		    objIn = new ObjectInputStream(fileIn);
            tasks = (ArrayList<Task>) objIn.readObject(); // supressed uncheck cast warning: no way to verify type of generic at runtime
        } finally {
            if(fileIn != null) {
                fileIn.close();
            }
            if(objIn != null) {
                objIn.close();
            }
            if (tasks != null) {
                lTasks = tasks;
                loadSuccessful = true;
            }
        }
        return loadSuccessful;
    }

    public boolean saveToFile(Path path) throws IOException{
        FileOutputStream  fileOut = new FileOutputStream(new File(path.toString())); // read serialized object from file as a String
        ObjectOutputStream  objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(lTasks);
        fileOut.close();
        objOut.close();
        return true;
    }

    // todo: make static date and time formatters
    // Stretch goal: add a command to print deadlines/events occurring on a specific date.

    public String[] addTodo(String... ss) throws IncorrectArgumentException {
        if (ss[0].equals("")) {
            throw new IncorrectArgumentException("Oops! Missing required arguments: Task Description");
        } else if (ss.length > 1) {
            throw new IncorrectArgumentException("Too many arguments!");
        }
        return add(new Todo(ss[0]));
    }
    
    public String[] addDeadline (String... ss) throws IncorrectArgumentException {
        if (ss[0].equals("")) {
            throw new IncorrectArgumentException("Oops! Missing required arguments: ".concat(ss.length == 1 ? "Task Description, by.." : "Task Description"));
        } else if (ss.length < 2) {
            throw new IncorrectArgumentException("Oops! Missing required arguments: /by..");
        } else if (ss.length > 2) {
            throw new IncorrectArgumentException("Too many arguments!");
        }

        LocalDate date = null;
        LocalTime time = null;
        try {
            String[] textDateTime = ss[1].split(" ");
            date = Task.toDate(textDateTime[0]);
            time = Task.toTime(textDateTime[1]);
        } catch (DateTimeParseException e) {
            throw new IncorrectArgumentException("Incorrect Date/Time format!\n     Correct format: yyyy-MM-dd HHmm");
        }
        return add(new Deadline(ss[0], date, time));
    }
    
    public String[] addEvent (String... ss) throws IncorrectArgumentException {
        if (ss[0].equals("")) {
            throw new IncorrectArgumentException("Oops! Missing required arguments: ".concat(ss.length == 1 ? "Task Description, at.." : "Task Description"));
        } else if (ss.length < 2) {
            throw new IncorrectArgumentException("Oops! Missing required arguments: /at..");
        } else if (ss.length > 2) {
            throw new IncorrectArgumentException("Too many arguments!");
        }

        LocalDate date = null;
        LocalTime time = null;
        try {
            String[] textDateTime = ss[1].split(" ");
            date = Task.toDate(textDateTime[0]);
            time = Task.toTime(textDateTime[1]);
        } catch (DateTimeParseException e) {
            throw new IncorrectArgumentException("Incorrect Date/Time format!\n     Correct format: yyyy-MM-dd HHmm");
        }
        return add(new Event(ss[0], date, time));
    }

    private String[] add(Task s) {
        lTasks.add(s);
        return new String[]{"Got it. I've added this task:", "  " + s.toString(), String.format("Now you have %d tasks in the list.", lTasks.size()) };
    }

    public String[] list() {
        String[] tasks = new String[lTasks.size() + 1];
        tasks[0] = lTasks.size() == 0 ? "You have no tasks in your list." : "Here are the tasks in your list:";
        for (int i = 1; i <= lTasks.size(); i++) {
            tasks[i] = String.format("%d.%s", i, lTasks.get(i - 1).toString());
        }
        return tasks;
    }

    public String[] done(int i) {
        return new String[]{ "Nice! I've marked this task as done:", lTasks.get(i - 1).done() };
    }

    public String[] delete(int i) {
        Task rem = lTasks.remove(i - 1);
        return new String[]{ "Noted. I've removed this task:", rem.toString(), String.format("Now you have %d tasks in the list.", lTasks.size()) };
    }
}