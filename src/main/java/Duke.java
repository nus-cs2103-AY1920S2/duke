import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    private static void deleteTask(int n) {
        System.out.println("Noted. I've removed this task:\n" + instructions.get(n-1).toString());
        instructions.remove(n-1);
        System.out.println("Now you have " + instructions.size() + " tasks in the list.");
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
            try {
                writeToFile();
            } catch (IOException e) {
                System.out.println("Oops! " + e.getMessage());
            }
        } else if (instruction.split(" ")[0].equals("delete")) {
            deleteTask(Integer.parseInt(instruction.split(" ")[1]));
            try {
                writeToFile();
            } catch (IOException e) {
                System.out.println("Oops! " + e.getMessage());
            }
        } else if (instruction.split(" ")[0].equals("todo")) {
            String task = instruction.replace("todo", "");
            if (!task.equals("")) {
                addTask(new ToDo(task));
                try {
                    writeToFile();
                } catch (IOException e) {
                    System.out.println("Oops! " + e.getMessage());
                }
            } else {
                throw new DukeException("EmptyToDo");
            }
        } else if (instruction.split(" ")[0].equals("deadline")) {
            String task = instruction.split("/")[0].replace("deadline", "");
            if (!task.equals("")) {
                String time = instruction.split("/")[1].replace("by ", "");
                addTask(new Deadline(task, time));
                try {
                    writeToFile();
                } catch (IOException e) {
                    System.out.println("Oops! " + e.getMessage());
                }
            } else {
                throw new DukeException("EmptyDeadline");
            }
        } else if (instruction.split(" ")[0].equals("event")) {
            String task = instruction.split("/")[0].replace("event", "");
            if (!task.equals("")) {
                String time = instruction.split("/")[1].replace("at ", "");
                addTask(new Event(task, time));
                try {
                    writeToFile();
                } catch (IOException e) {
                    System.out.println("Oops! " + e.getMessage());
                }
            } else {
                throw new DukeException("EmptyEvent");
            }
        } else {
            throw new DukeException("invalid");
        }
    }

    private static void readFile() throws FileNotFoundException {
        File file = new File("C:\\Users\\h1430\\Documents\\CS2103T\\duke\\src\\main\\java\\data\\duke.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(" \\| ");
            if (data[0].equals("T")) {
                ToDo todo = new ToDo(data[2]);
                addTask(todo);
                if (data[1].equals("1")) {
                    todo.setDone();
                }
            } else if (data[0].equals("D")) {
                Deadline deadline = new Deadline(data[2], data[3]);
                addTask(deadline);
                if (data[1].equals("1")) {
                    deadline.setDone();
                }
            } else if (data[0].equals("E")) {
                Event event = new Event(data[2], data[3]);
                addTask(event);
                if (data[1].equals("1")) {
                    event.setDone();
                }
            }
        }
    }

    private static void writeToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("C:\\Users\\h1430\\Documents\\CS2103T\\duke\\src\\main\\java\\data\\duke.txt");
        for (int i = 0; i < instructions.size(); i++)  {
            Task task = instructions.get(i);
            if (task instanceof ToDo) {
                String text = "T | " + (task.getStatus() ? "1" : "0") + " | " + task.getInstruction();
                fileWriter.write(text + System.lineSeparator());
            } else if (task instanceof Deadline) {
                String text = "D | " + (task.getStatus() ? "1" : "0") + " | " + task.getInstruction()
                        + " | " + ((Deadline) task).getTime();
                fileWriter.write(text + System.lineSeparator());
            } else if (task instanceof Event) {
                String text = "E | " + (task.getStatus() ? "1" : "0") + " | " + task.getInstruction()
                        + " | " + ((Event) task).getTime();
                fileWriter.write(text + System.lineSeparator());
            }
        }
        fileWriter.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            readFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
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