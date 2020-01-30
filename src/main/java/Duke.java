import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

public class Duke {

    static ArrayList<Task> tasks = new ArrayList<Task>();
    static Scanner sc = new Scanner(System.in);
    static final String FILEPATH = "../../data/duke.txt";

    // TODO: Implement write method
    static void saveFile(String path, String text){

        try {
            File f = new File(path);

            FileWriter fw = new FileWriter(f.getAbsoluteFile());
            fw.write(text);
            fw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    static String taskToParse(ArrayList<Task> tasks) {

        StringBuilder s = new StringBuilder("");

        for (int i = 0; i < tasks.size(); i++){
            s.append(tasks.get(i).toParser() + "\n");
        }

        return s.toString();
    }

    static void printIntro() {
        System.out.print(
                "____________________________________________________________\n" +
                        "Hello! I'm Duke\n" +
                        "What can I do for you?\n" +
                        "____________________________________________________________\n\n");
    }

    static void printReply(Task task) {
        System.out.print(
                "____________________________________________________________\n" +
                "Got it! I've added the task: \n" + task.toString() + "\nNow you have " + Integer.toString(tasks.size()) +
                        " tasks in the list." +
                "\n____________________________________________________________\n");
    }

    static void printGoodbye() {
        System.out.print(
                "____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!" +
                        "\n____________________________________________________________\n");
    }

    static void printList(ArrayList<Task> tasks){
        System.out.print("____________________________________________________________\n" +
                "Here are the tasks in your list:\n");
        for (int i =  0; i < tasks.size(); i++){
            System.out.println(Integer.toString(i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.print("____________________________________________________________\n");
    }

    static void markTaskDone(Task task){
        task.markAsDone();
        System.out.println("____________________________________________________________\n"
            + "Nice! I've marked this task as done:\n" + task.toString()
                + "\n____________________________________________________________\n");

    }

    static String stringToTime(String s) throws DukeException{
        try{
            // Convert DATE to expected format
            LocalDate d = LocalDate.parse(s.split(" ")[0]);
            SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            Date dateObj = sdf.parse(s.split(" ")[1]);
            return d.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + new SimpleDateFormat("HH:mm aa").format(dateObj);
        } catch (Exception e){
            System.out.println("Please give a correct format (ie. MMM d yyyy hh:mm)");
            throw new DukeException(s);
        }
    }

    static void addTask(String input) throws DukeException, TodoException{
        try {
            if (input.toLowerCase().equals("list")) {
                printList(tasks);
            } else if (input.split(" ")[0].equals("done")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                markTaskDone(tasks.get(taskNumber));
                saveFile(FILEPATH,taskToParse(tasks));
            } else if (input.split(" ")[0].equals("todo")) {
                if (input.split(" ").length == 1){
                    throw new TodoException(input);
                }
                Task task = new Todo(input.split(" ", 2)[1]);
                tasks.add(task);
                saveFile(FILEPATH,taskToParse(tasks));
                printReply(task);
            } else if (input.split(" ")[0].equals("deadline")) {
                Task task = new Deadline(input.split("/by ", 2)[0].split(" ", 2)[1], stringToTime(input.split("/by ", 2)[1]);
                tasks.add(task);
                saveFile(FILEPATH,taskToParse(tasks));
                printReply(task);
            } else if (input.split(" ")[0].equals("event")) {
                Task task = new Event(input.split("/at", 2)[0].split(" ", 2)[1], stringToTime(input.split("/at ", 2)[1]);
                tasks.add(task);
                saveFile(FILEPATH,taskToParse(tasks));
                printReply(task);
            } else {
                throw new DukeException(input);
            }
        }
        catch (TodoException e){
            System.out.println(e.toString());
        }
        catch (DukeException e){
            System.out.println(e.toString());
        }
    }

    static void deleteTask(String input){
        int pos = Integer.parseInt(input.split(" ")[1]);
        Task task = tasks.remove(pos-1);
        System.out.println("____________________________________________________________\n"
                + " Noted. I've removed this task: \n  "
                + task.toString() + "\n Now you have " + tasks.size() + " tasks in the list.\n"
                + "____________________________________________________________");
    }

    static void readFile() throws FileNotFoundException {
        // Read every line in the list
        File f = new File(FILEPATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String input = s.nextLine();
            char c = input.charAt(0);
            if (c == 'T') {
                String description = input.split("/")[2];
                boolean isDone;
                if (input.split("/")[1].equals(" O ")){
                    isDone = true;
                } else {
                    isDone = false;
                }
                Task t = new Todo(description, isDone);
                tasks.add(t);
            } else if (c == 'D') {
                String description = input.split("/")[2];
                boolean isDone;
                if (input.split("/")[1].equals(" O ")){
                    isDone = true;
                } else {
                    isDone = false;
                }
                String deadline = input.split("/")[3];
                Task t = new Deadline(description, isDone, deadline);
                tasks.add(t);
            } else if (c == 'E') {
                String description = input.split("/")[2];
                boolean isDone;
                if (input.split("/")[1].equals(" O ")){
                    isDone = true;
                } else {
                    isDone = false;
                }
                String deadline = input.split("/")[3];
                Task t = new Event(description, isDone, deadline);
                tasks.add(t);
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        printIntro();
        //TODO: Read from file and save to arraylist
        try{
            readFile();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        String input = sc.nextLine();
            while (!input.toLowerCase().equals("bye")) {
                if (input.split(" ")[0].toLowerCase().equals("delete")) {
                    deleteTask(input);
                    saveFile(FILEPATH,taskToParse(tasks));
                }
                else {
                    addTask(input);
                }
            input = sc.nextLine();
        }
        printGoodbye();
    }
}
