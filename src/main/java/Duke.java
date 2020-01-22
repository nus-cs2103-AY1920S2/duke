import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String fill = "----------------------------------------";
    static String indent = "    ";

    /**
     * Formats a string to an output produced by Duke.
     * @param s String to be formatted.
     * @return Formatted string ready to be printed.
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> lst = new ArrayList<>(100);
        String getInput = null;

        System.out.println(dukeFormat("Hello I'm your mum. What can I do for you?"));
        getInput = sc.next();

        while (true) {
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
                res += "\n    You have " + String.valueOf(lst.size()) + " tasks in the list.";
                System.out.println(dukeFormat(res));

            } else if (getInput.equals("done")) {

                // validate input for list index
                int getNumber = sc.nextInt();
                while (!(getNumber == (int)getNumber) || getNumber <= 0 || getNumber > lst.size()) {
                    System.out.println(dukeFormat("Invalid input, please try again."));
                    getNumber = sc.nextInt();
                }

                Task currTask = lst.get(getNumber - 1);
                currTask.setDone(true);
                System.out.println(dukeFormat("Sure I will mark this task as done.\n" + indent + currTask));
                break;

            } else {
                System.out.println(dukeFormat("Invalid input"));
            }
            getInput = sc.next();
        }
    }
}
