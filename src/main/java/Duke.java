import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import java.util.stream.*;



import main.java.Deadline;
import main.java.DukeException;
import main.java.DukeGreeting;
import main.java.Event;
import main.java.Storage;
import main.java.Task;
import main.java.Todo;
import main.java.Ui;

public class Duke {

    /**Declaration of variables */
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> Tasks = new ArrayList<>();
        final String FILEPATH;
        final File FILE;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Storage storage;

    /**
     * Constructor
     * @param String filepath
     */
    public Duke(String filepath) {
        this.FILEPATH = filepath;
        this.FILE = new File(filepath);
        this.storage = new Storage(filepath);
    }

    /**
     * Main method
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        
        Duke d = new Duke("data/list.txt");

        d.run();
    }

    /**
     * Run program
     * @throws DukeException
     */
    public void run() throws DukeException {
        /** Welcome Message */
        DukeGreeting dg = new DukeGreeting();
        dg.showWelcomeMessage();
        dg.showInstructions();
        while(true) {
            String line = sc.nextLine();
            Ui ui = new Ui(line);
            String command = ui.getCommand();
            if (command.equals("bye")) {
                dg.showGoodbyeMessage();
                sc.close();
                System.exit(0);
            } else if (command.equals("list")) {
                try {
                    ui.showList();
                    storage.printFileContents(FILEPATH);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (command.equals("done")) {
                if (ui.getDescription().equals(null)) {
                    throw new DukeException(ui.errmessage());
                } else {
                    String desc = ui.getDescription();
                    Task t = new Task(desc);
                    t.markAsDone();
                    try {
                        String upd8task = storage.getTask(desc, FILEPATH).replaceAll("["+"\u2718"+"]", "["+"\u2713"+"]");
                        String f = File.createTempFile("temp", null).getAbsolutePath();
                        storage.removeLine(desc, FILEPATH, f);
                        storage.writeToFile(FILEPATH, upd8task + '\n');
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (command.equals("todo")) {
                if (ui.getDescription().equals(null)) {
                    throw new DukeException(ui.errmessage());
                } 
                Todo todo = new Todo(ui.getDescription());
                Tasks.add(todo);
                try {
                    storage.writeToFile(FILEPATH, todo.toString()+'\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ui.addedTask(todo);
            } else if (command.equals("deadline")) {
                if (ui.getDescription().equals(null)) {
                    throw new DukeException(ui.errmessage());
                } 
                String desc =ui.getDescription();
                String date = (desc.split("/by "))[1];
                LocalDateTime ldt = LocalDateTime.parse(date, formatter);
                String sldt = ldt.format(formatter);
                Deadline deadline = new Deadline(desc, sldt);
                Tasks.add(deadline);
                try {
                    storage.writeToFile(FILEPATH, deadline.toString()+'\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ui.addedTask(deadline);
            } else if (command.equals("event")) {
                if (ui.getDescription().equals(null)) {
                    throw new DukeException(ui.errmessage());
                } 
                String desc = ui.getDescription();
                String date = (desc.split("/at "))[1];
                LocalDateTime ldt = LocalDateTime.parse(date, formatter);
                String sldt = ldt.format(formatter);
                Event event = new Event(desc, sldt);
                Tasks.add(event);
                try {
                    storage.writeToFile(FILEPATH, event.toString()+'\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ui.addedTask(event);
            } else if (command.equals("delete")) {
                if (ui.getDescription().equals(null)) {
                    throw new DukeException(ui.errmessage());
                } else {
                    try {
                        String desc = ui.getDescription();
                        long currsize = storage.getLineCount(FILE)-1;
                        ui.deleteTask(desc);
                        String f = File.createTempFile("temp", null).getAbsolutePath();
                        storage.removeLine(desc, FILEPATH, f);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (command.equals("tasks")) {
                try {
                    String rest = ui.getDescription();
                    String[] q = rest.split("/on ");
                    String date = q[1];
                    LocalDateTime ldt = LocalDateTime.parse(date, formatter);
                    String sldt = ldt.format(formatter);
                    System.out.println("Here are the tasks in your list for this date:");
                    System.out.println(storage.getTask(sldt, FILEPATH));
                } catch (IOException e) {
                    e.printStackTrace();
                }
         } else if(command.equals("find")) {
            try {
                String rest = ui.getDescription();
                System.out.println("Here are the tasks in your list that matches:" + rest);
                System.out.println(storage.getTask(rest, FILEPATH));
            } catch (Exception e) {
                e.printStackTrace();
            }
         } else {
                throw new DukeException("Oops I'm sorry, what is this?");
            }
        }
    }

}
