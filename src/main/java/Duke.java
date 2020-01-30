import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
<<<<<<< HEAD
import java.io.FileWriter;
=======
import java.time.LocalDate;
>>>>>>> branch-Level-8

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I 'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________");

        Scanner myScanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        readFile("./list.txt", list);

        while (true) {

            String word = myScanner.nextLine();
            String[] arrSplit = word.split(" " , 2);
            String keyword = arrSplit[0];

            try {

                if (keyword.equals("bye")) {
                    writeFile("./list.txt", list);
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (keyword.equals("list")) {
                    System.out.println("Here are the task in your list");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i));
                    }

                } else if (keyword.equals("done")) {
                    int taskNumber = Integer.valueOf(arrSplit[1]);
                    list.get(taskNumber - 1).markAsDone();

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(taskNumber - 1).getStatusIcon() +
                            " " + list.get(taskNumber - 1).getTask());

                } else if (keyword.equals("delete")) {
                    int taskNumber = Integer.valueOf(arrSplit[1]);
                    System.out.println("Noted. I've removed this task");
                    System.out.println(list.get(taskNumber - 1));
                    list.remove(taskNumber - 1);
                    System.out.println("Now you have " + list.size() + " in the list.");

                } else if (keyword.equals("todo") || keyword.equals("deadline") || keyword.equals("event")) {

                    if (arrSplit.length <= 1) {
                        throw new DukeException("I think u need more arguments");
                    } else {

                        String therest = arrSplit[1];
                        String[] arrSplit2 = therest.split("/", 2);

                        if (keyword.equals("todo")) {
                            list.add(new Todo(arrSplit2[0]));
                        } else if (keyword.equals("deadline")) {
                            list.add(new Deadline(arrSplit2[0], LocalDate.parse(arrSplit2[1])));
                        } else if (keyword.equals("event")) {
<<<<<<< HEAD
                            list.add(new Event(arrSplit2[0], arrSplit2[1]));

=======
                            list.add(new Event(arrSplit2[0], LocalDate.parse(arrSplit2[1])));
>>>>>>> branch-Level-8
                        }

                        System.out.println("Got it. I 've added this task:");
                        System.out.println(list.get(list.size() - 1));
                        System.out.println("Now you have " + list.size() + " in the list.");
                    }
                } else {
                    throw new DukeException("I DK how to process this -> " + word);
                }
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            finally {
                System.out.println("________________________________________");
            }
        }
    }

    public static void readFile(String file, ArrayList<Task> taskList) {
        try {

            File f = new File(file);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String current = s.nextLine();
                String[] arrSplit = current.split("/");
                Task currentTask = null;

                switch (arrSplit[0]) {
                    case "T":
                        currentTask = new Todo(arrSplit[2]);
                        break;
                    case "D":
                        currentTask = new Deadline(arrSplit[2], arrSplit[3]);
                        break;
                    case "E":
                        currentTask = new Event(arrSplit[2], arrSplit[3]);
                        break;
                }

                if (arrSplit[1].equals("1")) { // 1 meaning done
                    currentTask.markAsDone();
                }
                taskList.add(currentTask);
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String file, ArrayList<Task> taskList) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            for (Task current : taskList) {
                if (current instanceof Todo) {
                    writer.write("T" + "/" + current.checkDone() + "/" + current.getTask() +
                            System.lineSeparator());
                } else if (current instanceof Deadline) {
                    writer.write("D" + "/" + current.checkDone() + "/" + current.getTask() + "/" +
                            ((Deadline) current).getTime() + System.lineSeparator());
                } else if (current instanceof Event){
                    writer.write("E" + "/" + current.checkDone() + "/" + current.getTask() + "/" +
                            ((Event) current).getTime() + System.lineSeparator());
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
