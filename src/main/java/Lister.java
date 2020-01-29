import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Lister {
    private ArrayList<Task> store;
    private PrintWriter writer;

    public Lister() { }

    private void loadData() {
        store = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader( new FileReader("../../../data/duke.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                int x = line.indexOf('|');
                String command = line.substring(0, x-1);
                switch (command) {
                case "T":
                    ToDo task = new ToDo(line.substring(8), "");
                    if (line.substring(4, 5).equals("1")) {
                        task.markAsDone() ;
                    }
                    this.store.add(task);
                    break;
                case "E":
                    String details = line.substring(8);
                    int y = details.indexOf('|');
                    System.out.println(details);
                    Event event = new Event(details.substring(0, y-1), details.substring(y+2));
                    if (line.substring(4, 5).equals("1")) {
                        event.markAsDone() ;
                    }
                    this.store.add(event);
                    break;
                case "D":
                    String detail = line.substring(8);
                    int z = detail.indexOf('|');
                    System.out.println(detail);
                    Deadline deadline = new Deadline(detail.substring(0, z-1), detail.substring(z+2));
                    if (line.substring(4, 5).equals("1")) {
                        deadline.markAsDone() ;
                    }
                    this.store.add(deadline);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void storeData() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < store.size(); i++) {
            Task curr = store.get(i);
            switch (curr.getType()) {
            case TODO:
                sb.append("T | " + (curr.isDone ? 1 : 0) + " | " + curr.description + "\n");
                break;
            case EVENT:
                sb.append("E | " + (curr.isDone ? 1 : 0) + " | " + curr.description + " | " + curr.time + "\n");
                System.out.println("curr time " + curr.time);
                System.out.println("curr description " + curr.description);
                break;
            case DEADLINE:
                sb.append("D | " + (curr.isDone ? 1 : 0) + " | " + curr.description + " | " + curr.time + "\n");
                break;
            }
        }

        try {
            writer = new PrintWriter("../../../data/duke.txt");
            writer.write(sb.toString());
            writer.flush();
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe);
        }

    }

    public void record(String command) {
        loadData();
        if (command.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < store.size(); i++) {
                System.out.println((i + 1) + "." + store.get(i).toString());
            }
        } else {
            try {
                int x = getIndex(command);
                switch (command.substring(0, x)) {
                    case "delete":
                        int b = Integer.valueOf(command.substring(x + 1)) - 1;
                        store.remove(b);
                        System.out.println("Noted. I've removed this task:\n" + store.get(b).toString());
                        System.out.println("Now you have " + store.size() + " tasks in the list.");
                        break;
                    case "done":
                        int y = Integer.valueOf(command.substring(x + 1)) - 1;
                        store.get(y).markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n" + store.get(y).toString());
                        break;
                    case "deadline":
                        int z = command.indexOf('/');
                        Task newDeadline = new Deadline(command.substring(x + 1, z - 1), command.substring(z + 4));
                        store.add(newDeadline);
                        printTask(newDeadline);
                        break;
                    case "event":
                        int a = command.indexOf('/');
                        Task newEvent = new Event(command.substring(x + 1, a - 1), command.substring(a + 4));
                        store.add(newEvent);
                        printTask(newEvent);
                        break;
                    case "todo":
                        Task newToDo = new ToDo(command.substring(x + 1), "");
                        store.add(newToDo);
                        printTask(newToDo);
                        break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            storeData();
        }
    }

    private int getIndex(String command) throws DukeException {
        int x = 0;
        try {
            x = command.indexOf(' ');
            if (x < 0) {
                throw new StringIndexOutOfBoundsException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            switch (command) {
                case "todo":
                    throw new DukeException("The description of a todo cannot be empty");
                default:
                    throw new DukeException("I'm sorry but I don't know what that means :(");
            }
        }
        return x > 0 ? x : 0;
    }

    private void printTask(Task task) {
        System.out.println("Got it. I've added this task:\n" + task.toString());
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }
}
