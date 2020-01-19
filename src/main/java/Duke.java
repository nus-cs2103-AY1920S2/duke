import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;

public class Duke {
    final static String taskFilePath = "../../../data/duke.txt";
    static int i;
    static List<Task> tasks;

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        System.out.println("____________________________________________________________");

        tasks = new ArrayList<>();
        String command = "";

        while (!(command).equalsIgnoreCase(Operation.BYE.toString())) {
            String[] current = sc.nextLine().split(" ");
            command = current[0];
            if ((command).equalsIgnoreCase(Operation.BYE.toString())) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            }

            if (command.equalsIgnoreCase(Operation.LIST.toString())) {
                tasks.clear();
                try {
                    File file = new File(taskFilePath);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    BufferedReader rd = new BufferedReader(new FileReader(taskFilePath));
                    i = 0;
                    String line;
                    while ((line = rd.readLine()) != null) {
                        String type = line.split("|")[0];
                        if (type.equals("E")) {
                            tasks.add(i, EventObj.parse(line));
                        } else if (type.equals("D")) {
                            tasks.add(i, Deadline.parse(line));
                        } else if (type.equals("T")) {
                            tasks.add(i, Task.parse(line));
                        } else {

                        }
                        i++;
                    }
                    rd.close();

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                System.out.println("    ____________________________________________________________");
                System.out.println("      Here are the tasks in your list: \n");
                for (int count = 0; count < i; count++) {
                    System.out.print("     " + (count + 1) + ". ");
                    System.out.println(tasks.get(count));
                }

                System.out.println("    ____________________________________________________________");
            } else if (command.equalsIgnoreCase(Operation.TODO.toString()) || command.equalsIgnoreCase(Operation.DEADLINE.toString())
                    || command.equalsIgnoreCase(Operation.EVENT.toString())) {
                try {
                    addTask(current);
                } catch (DukeException ex) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + ex.getMessage());
                    System.out.println("    ____________________________________________________________");
                }

            } else if (command.equalsIgnoreCase(Operation.DONE.toString())) {
                int value = Integer.parseInt(current[1]);
                try {
                    Task cur = tasks.get(value - 1);
                    cur.markAsDone();
                    deleteTask(value);
                    tasks.add(value - 1, cur);
                    StringBuilder sb = new StringBuilder();
                    for (Task t : tasks) {
                        sb.append(t.print() + "\n");
                    }
                    writeToFile(sb.toString());
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Nice! I've marked this task as done: \n");
                    System.out.println("    " + cur.getStatusIcon() + " "
                            + cur.getDescription());
                    System.out.println("    ____________________________________________________________");
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Please enter a valid task number");
                    System.out.println("    ____________________________________________________________");
                }

            } else if (command.equalsIgnoreCase(Operation.DELETE.toString())) {
                try {
                    deleteTask(Integer.parseInt(current[1]));
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Please enter a valid task number");
                    System.out.println("    ____________________________________________________________");
                }

            } else {
                try {
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (Exception ex) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + ex.getMessage());
                    System.out.println("    ____________________________________________________________");
                }
            }
        }

    }

    public static void deleteTask(int value) {
        Task cur = tasks.get(value - 1);
        tasks.remove(cur);
        StringBuilder sb = new StringBuilder();

        if (tasks.size() > 0) {
            for (Task t : tasks) {
                sb.append(t.print() + "\n");
            }
            writeToFile(sb.toString());
        } else{
            try {
                new FileWriter(taskFilePath, false).close();
            } catch (IOException ex) {
                System.out.println("file not cleared");
            }
        }



        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task: \n");
        System.out.println("    " + cur);
        i--;
        System.out.println("      Now you have " + i + " tasks in the list.  \n");
        System.out.println("    ____________________________________________________________");
    }


    public static void addTask(String[] current) throws DukeException {
        String[] words = Arrays.stream(current).skip(1).toArray(String[]::new);
        String command = current[0];

        if (words.length == 0) {
            throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
        }
        if (command.equalsIgnoreCase(Operation.DEADLINE.toString())) {
            int position = 0;
            boolean specifyDate = false;
            for (String w : words) {
                if (w.equals("/by")) {
                    String description = String.join(" ", List.of(words).subList(0, position));
                    String date = List.of(words).stream().skip(position + 1).collect(Collectors.joining(" "));
                    Task t = new Deadline(description, date);
                    tasks.add(i, t);
                    specifyDate = true;
                    break;
                } else {
                    position++;
                }

            }

            if (!specifyDate) {
                String description = String.join(" ", words);
                Task t = new Deadline(description, "");
                tasks.add(i, t);
            }

        } else if (command.equalsIgnoreCase(Operation.EVENT.toString())) {
            int position = 0;
            boolean specifyDate = false;
            for (String w : words) {
                if (w.equals("/at")) {
                    String description = String.join(" ", List.of(words).subList(0, position));
                    String datetime = List.of(words).stream().skip(position + 1).collect(Collectors.joining(" "));
                    Task t = new EventObj(description, datetime);
                    tasks.add(i, t);
                    specifyDate = true;
                    break;
                } else {
                    position++;
                }

            }

            if (!specifyDate) {
                String description = String.join(" ", words);
                Task t = new EventObj(description, "");
                tasks.add(i, t);
            }

        } else {
            Task t = new Task(String.join(" ", words));
            tasks.add(i, t);

        }

        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(t.print() + "\n");
        }
        writeToFile(sb.toString());


        System.out.println("    ____________________________________________________________");
        System.out.println("      Got it. I've added this task:  \n");

        System.out.print("      ");
        Task t = tasks.get(i);
        if (t instanceof Deadline) {
            System.out.println(t.toString());
        } else if (t instanceof EventObj) {
            System.out.println(((EventObj) t).toString());
        } else {
            System.out.println(t);
        }
        i++;
        System.out.println("      Now you have " + i + " tasks in the list.  \n");
        System.out.println("    ____________________________________________________________");
    }

    static void writeToFile(String mycontent) {
        try {
            new FileWriter(taskFilePath, false).close();
        } catch (IOException ex) {
            System.out.println("file not cleared");
        }
        BufferedWriter writer = null;
        try {
            File file = new File(taskFilePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            writer.write(mycontent);
            writer.flush();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
