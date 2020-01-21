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

            } else {
                Task task = new Task(getInput);
                lst.add(task);
                System.out.println(dukeFormat("added: " + getInput));
            }
            getInput = sc.next();
        }
    }
}
