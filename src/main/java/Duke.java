import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {
    static String fill = "----------------------------------------";
    static String indent = "    ";

    /**
     * Formats a string to an output produced by Duke.
     * @param s string to be formatted.
     * @return formatted string ready to be printed.
     */
    public static String dukeFormat(String s) {
        return indent + fill + "\n" + indent + s + "\n" + indent + fill;
    }

    /**
     * Formats a list to an output produced by Duke.
     * @param lst list to be formatted.
     * @return Formatted list ready to be printed.
     */
    public static String dukeFormatList(ArrayList<Task> lst) {
        String res = "";
        res += indent + fill + "\n";
        for (int i = 0; i < lst.size(); i++) {
            res += indent + String.valueOf(i + 1) + ". " + lst.get(i) + "\n";
        }
        res += indent + fill;
        return res;
    }

    /**
     * Saves the current state of the task list in persistent storage.
     * @param file file name specified
     * @param lst updated list to be saved
     */
    public static void save(String file, ArrayList<Task> lst) {
        try {
            FileOutputStream fos= new FileOutputStream ("src/main/data/tasks.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lst);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> lst = new ArrayList<>(100);
        String getInput = null;

        try {
            File savedData = new File("src/main/data/tasks.ser");
            FileInputStream fis = new FileInputStream(savedData);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Task> lstSaved = (ArrayList<Task>) ois.readObject();
            ois.close();
            lst = lstSaved;
            System.out.println(indent + "Retrieving my little boy's history..");
        } catch (FileNotFoundException e) {
            System.out.println(indent + "Initialising new list for my little boy..");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(dukeFormat("Hello I'm your mum. What can I do for you?"));
        getInput = sc.next();

        while (true) {
            try {
                if (getInput.equals("bye")) {
                    // exits program
                    System.out.println(dukeFormat("Bye, have a good day!"));
                    break;
                } else if (getInput.equals("list")) {
                    // prints list
                    System.out.println(dukeFormatList(lst));
                } else if (getInput.equals("todo") || getInput.equals("deadline") || getInput.equals("event")) {
                    // Adds a todo, deadline or event based on user input

                    String res = "";
                    String line = sc.nextLine();
                    res += "As your mummy, I have added this task to your list:\n    ";
                    if (getInput.equals("todo")) {
                        Todo todo = new Todo(line);
                        lst.add(todo);
                        res += todo;
                    } else if (getInput.equals("deadline")) {
                        int indexCut = line.indexOf("/by");
                        String desc = line.substring(0, indexCut - 1);
                        String by = line.substring(indexCut + 4);
                        Deadline deadline = new Deadline(desc, by);
                        lst.add(deadline);
                        res += deadline;
                    } else {
                        int indexCut = line.indexOf("/at");
                        String desc = line.substring(0, indexCut - 1);
                        String at = line.substring(indexCut + 4);
                        Event event = new Event(desc, at);
                        lst.add(event);
                        res += event;
                    }

                    save("src/main/data/tasks.ser", lst);
                    res += "\n    You have " + String.valueOf(lst.size()) + " tasks in the list.";
                    System.out.println(dukeFormat(res));

                } else if (getInput.equals("done")) {

                    // validate input for list index
                    try {
                        String getNumberString = sc.next();
                        int getNumber = Integer.valueOf(getNumberString);
                        Task currTask = lst.get(getNumber - 1);
                        currTask.setDone(true);
                        System.out.println(dukeFormat("Sure I will mark this task as done.\n" + indent + currTask));
                    } catch (NumberFormatException e) {
                        System.out.println(dukeFormat("Please input an integer."));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(dukeFormat("Please try again, your number is out of range."));
                    }

                } else if (getInput.equals("delete")) {
                    try {
                        // validate input for list index
                        String getNumberString = sc.next();
                        int getNumber = Integer.valueOf(getNumberString);
                        Task currTask = lst.get(getNumber - 1);
                        lst.remove(getNumber - 1);
                        System.out.println(dukeFormat("Sure I will delete this task.\n" + indent + currTask + "\n    Now you have " + String.valueOf(lst.size()) + " tasks."));
                    } catch (NumberFormatException e) {
                        System.out.println(dukeFormat("Please input an integer."));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(dukeFormat("Please try again, your number is out of range."));
                    }
                } else {
                    throw new DukeException("Invalid Input");
                }
            } catch (DukeException e) {
                System.out.println(dukeFormat("Please try again, your input is invalid."));
            } catch (Exception e) {
                System.out.println(e.getClass());
            }

            getInput = sc.next();
        }
    }
}
