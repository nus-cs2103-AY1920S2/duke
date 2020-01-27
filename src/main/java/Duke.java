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
import main.java.Task;
import main.java.Todo;

public class Duke {

    public Duke() {}
    public static void main(String[] args) throws DukeException{

        
        /**Declaration of variables */
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> Tasks = new ArrayList<>();
        DukeGreeting dg = new DukeGreeting();
        Duke d = new Duke();
        final String FILEPATH = "data/list.txt";
        final File FILE = new File(FILEPATH);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        /**Welcome Message  */
        dg.showWelcomeMessage();
        dg.showInstructions();
        
        /**Run program */
        while(true) {
            String[] x = sc.nextLine().split(" ",2);
            String command = x[0];
            if (command.equals("bye")) {
                dg.showGoodbyeMessage();
                sc.close();
                System.exit(0);
            } else if (command.equals("list")) {
                try {
                    System.out.println("Here are the tasks in your list:");
                    d.printFileContents(FILEPATH);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (command.equals("done")) {
                if (x.length == 1) {
                    throw new DukeException("Tell me which task you have completed!!");
                } else {
                    String rest = x[1];
                    Task t = new Task(rest);
                    //int index = Integer.valueOf(rest);
                    t.markAsDone();
                    try {
                        //d.writeToFile(FILEPATH, d.stringifyList(Tasks));
                        String upd8task = d.getTask(x[1], FILEPATH).replaceAll("["+"\u2718"+"]", "["+"\u2713"+"]");
                        String f = File.createTempFile("temp", null).getAbsolutePath();
                        d.removeLine(x[1], FILEPATH, f);
                        d.writeToFile(FILEPATH, upd8task + '\n');
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (command.equals("todo")) {
                if (x.length == 1) {
                    throw new DukeException("Tell me the description of the task!!");
                } 
                String rest = x[1];
                Todo todo = new Todo(rest);
                Tasks.add(todo);
                try {
                    d.writeToFile(FILEPATH, todo.toString()+'\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("-------------------------------------------------------------");
                System.out.println("Got it. I've added this task: ");
                System.out.println(todo + "\n" + "Now you have " + Tasks.size() + " tasks in the list." + "\n");
                System.out.println("-------------------------------------------------------------");
            } else if (command.equals("deadline")) {
                if (x.length == 1) {
                    throw new DukeException("Tell me the description of the task!!");
                } 
                String rest = x[1];
                String[] q = rest.split("/by ");
                String date = q[1];
                LocalDateTime ldt = LocalDateTime.parse(date, formatter);
                String sldt = ldt.format(formatter);
                Deadline deadline = new Deadline(q[0], sldt);
                Tasks.add(deadline);
                try {
                    d.writeToFile(FILEPATH, deadline.toString()+'\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("-------------------------------------------------------------");
                System.out.println("Got it. I've added this task: ");
                System.out.println(deadline +"\n" + "Now you have " + Tasks.size() + " tasks in the list." + "\n");
                System.out.println("-------------------------------------------------------------");

            } else if (command.equals("event")) {
                if (x.length == 1) {
                    throw new DukeException("Tell me the description of the task!!");
                } 
                String rest = x[1];
                String[] q = rest.split("/at ");
                String date = q[1];
                LocalDateTime ldt = LocalDateTime.parse(date, formatter);
                String sldt = ldt.format(formatter);
                Event event = new Event(q[0], sldt);
                Tasks.add(event);
                try {
                    d.writeToFile(FILEPATH, event.toString()+'\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("-------------------------------------------------------------");
                System.out.println("Got it. I've added this task: ");
                System.out.println(event + "\n" + "Now you have " + Tasks.size() + " tasks in the list." + "\n");
                System.out.println("-------------------------------------------------------------");
            } else if (command.equals("delete")) {
                if (x.length == 1) {
                    throw new DukeException("Tell me which task you want to delete!!");
                } else {
                    try {
                        String rest = x[1];
                        //int index = Integer.valueOf(rest);
                        long currsize = d.getLineCount(FILE)-1;
                        //Tasks.get(index-1).deleteTask();
                        System.out.println("Now you have " + currsize + " tasks in the list." + "\n");
                        System.out.println("-------------------------------------------------------------");
                        //Tasks.remove(index-1);
                        String f = File.createTempFile("temp", null).getAbsolutePath();
                        d.removeLine(x[1], FILEPATH, f);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (command.equals("tasks")) {
                try {
                    String rest = x[1];
                    String[] q = rest.split("/on ");
                    String date = q[1];
                    LocalDateTime ldt = LocalDateTime.parse(date, formatter);
                    String sldt = ldt.format(formatter);
                    System.out.println("Here are the tasks in your list for this date:");
                    d.getTask(sldt, FILEPATH);
                } catch (IOException e) {
                    e.printStackTrace();
                }
         } else {
                throw new DukeException("Oops I'm sorry, what is this?");
            }
        }

    }

    public void printFileContents(String filepath) throws FileNotFoundException {
            File f = new File(filepath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                System.out.println(s.nextLine()); 
            }
            s.close();
    }

    public void writeToFile(String filepath, String textToAdd) throws IOException {
            FileWriter fw = new FileWriter(filepath, true);
            fw.write(textToAdd);
            fw.close(); 
    }

    public String stringifyList(ArrayList<Task> l) {
        String s = "";
        for (Task t : l) {
            s += t.toString();
            s+= "\n";
        }
        return s;
    }

    public void removeLine(String lineContent, String filepath, String temp) throws IOException {
        File file = new File(filepath);
        File tempo = new File(temp);
        PrintWriter out = new PrintWriter(new FileWriter(temp));
        Files.lines(file.toPath())
            .filter(line -> !line.contains(lineContent))
            .forEach(out::println);
        out.flush();
        out.close();
        tempo.renameTo(file);
    }

    public String getTask(String lineContent, String filepath) throws IOException {
        File file = new File(filepath);
        return Files.lines(file.toPath())
                    .filter(line -> line.contains(lineContent))
                    .reduce("", (x,y) -> x+y+'\n');
    }

        /**
     * Count file rows.
     *
     * @param file file
     * @return file row count
     * @throws IOException
     */
    public long getLineCount(File file) throws IOException {
        try (Stream<String> lines = Files.lines(file.toPath())) {
            return lines.count();
        }
    }
}

/**things to fix:
 * 1. append to text file instead of overwrite
 * 2. try to do listifystring
 * 3. update instructions
 * 4. print out tasks from date
 */