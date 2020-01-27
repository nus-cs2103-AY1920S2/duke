import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] dukeList = new Task[100];
        int counter = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        String nxt;
        nxt = sc.nextLine();
        String newString;
        String[] splitBySpace;

        while (nxt.equals("bye") == false) {
            newString = "";
            splitBySpace = nxt.split(" ");
            try {
                //If command is not list
                if (nxt.equals("list") == false) {
                    StringTokenizer st = new StringTokenizer(nxt);
                    String first = st.nextToken();
                    //Command: Set task as done
                    if (first.equals("done")) {
                        int num = Integer.parseInt(st.nextToken());
                        dukeList[num - 1].markAsDone();
                        System.out.println("The following has been marked as done:");
                        System.out.println("  " + dukeList[num - 1]);
                        //Command: deadline
                    } else if (first.equals("deadline")) {
                        if (splitBySpace.length == 1) {
                            throw new DukeException("deadline");
                        } else {
                            for (int i = 1; i < splitBySpace.length - 1; i++) {
                                newString = newString + splitBySpace[i] + " ";
                            }
                            newString = newString + splitBySpace[splitBySpace.length - 1];
                            String[] splitBySlash = newString.split("/");
                            Task t = new Deadline(splitBySlash[0], splitBySlash[1]);
                            dukeList[counter] = t;
                            counter++;
                            System.out.println("I have added this task: \n  " + dukeList[counter - 1]);
                            System.out.println("Now you have " + counter + " task(s) in the list.");
                        }
                        //Command: event
                    } else if (first.equals("event")) {
                        if (splitBySpace.length == 1) {
                            throw new DukeException("event");
                        } else {
                            for (int i = 1; i < splitBySpace.length - 1; i++) {
                                newString = newString + splitBySpace[i] + " ";
                            }
                            newString = newString + splitBySpace[splitBySpace.length - 1];
                            String[] splitBySlash = newString.split("/");
                            Task t = new Event(splitBySlash[0], splitBySlash[1]);
                            dukeList[counter] = t;
                            counter++;
                            System.out.println("I have added this task: \n  " + dukeList[counter - 1]);
                            System.out.println("Now you have " + counter + " task(s) in the list.");
                        }
                        //Command is to do task
                    } else if (first.equals("todo")) {
                        if (splitBySpace.length == 1) {
                            throw new DukeException("todo");
                        } else {
                            for (int i = 1; i < splitBySpace.length - 1; i++) {
                                newString = newString + splitBySpace[i] + " ";
                            }
                            newString = newString + splitBySpace[splitBySpace.length - 1];
                            Task t = new ToDo(newString);
                            dukeList[counter] = t;
                            counter++;
                            System.out.println("I have added this task: \n  " + dukeList[counter - 1]);
                            System.out.println("Now you have " + counter + " task(s) in the list.");
                        }
                    } else {
                        throw new DukeException("don't understand");
                    }
                    //Print out the list
                } else {
                    for (int i = 0; i < counter; i++) {
                        System.out.println((i + 1) + "." + dukeList[i]);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }

            //Get next input
            nxt = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
