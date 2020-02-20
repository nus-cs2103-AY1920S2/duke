import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Duke {
    private static ArrayList<Task> instructions = new ArrayList<>(100);

    private static void greet() {
        System.out.println("Hello! I'm Duke\n What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addTask(Task task) {
        instructions.add(task);
        System.out.println("Got it. I've added this task:\n" + task.toString() 
            + "\n Now you have " + instructions.size() + " tasks in the list.");
    }

    private static void doneTask(int n) {
        instructions.get(n-1).setDone();
        System.out.println("Nice! I've marked this task as done: \n" + instructions.get(n-1).toString());
    }

    private static void printList() {
        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i) != null) {
                System.out.println((i + 1) + ". " + instructions.get(i).toString());
            }
        }
    }

    /**
     * this method handles two types of error: invalid input (does not contain todo/deadline/event)
     * and empty task (if the input starts with todo/deadline/event and the content is not empty,
     * it is assumed that the input has correct structure)
    */
    private static void handleInstruction(String instruction) throws DukeException {
        if (instruction.equals("bye")) {
            exit();
        } else if (instruction.equals("list")) {
            printList();
        } else if (instruction.split(" ")[0].equals("done")) {
            doneTask(Integer.parseInt(instruction.split(" ")[1]));
        } else if (instruction.split(" ")[0].equals("todo")) {
            String task = instruction.replace("todo", "");
            if (!task.equals("")) {
                addTask(new ToDo(task));
            } else {
                throw new DukeException("EmptyToDo");
            }
        } else if (instruction.split(" ")[0].equals("deadline")) {
            String task = instruction.split("/")[0].replace("deadline", "");
            if (!task.equals("")) {
                String time = instruction.split("/")[1].replace("by ", "");
                addTask(new Deadline(task, time));
            } else {
                throw new DukeException("EmptyDeadline");
            }
        } else if (instruction.split(" ")[0].equals("event")) {
            String task = instruction.split("/")[0].replace("event", "");
            if (!task.equals("")) {
                String time = instruction.split("/")[1].replace("at ", "");
                addTask(new Event(task, time));
            } else {
                throw new DukeException("EmptyEvent");
            }
        } else {
            throw new DukeException("invalid");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        while (scanner.hasNextLine()) {
            String instruction = scanner.nextLine();
            try {
                handleInstruction(instruction);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}