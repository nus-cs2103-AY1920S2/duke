import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> dukeList = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Greetings from\n" + logo);
        System.out.println("Is that anything that I can do for you?");

        String nxt = sc.nextLine();
        String newString;
        String[] splitBySpace;

        while (!nxt.equals("bye")) {
            newString = "";
            splitBySpace = nxt.split(" ");
            try {
                //If command is not list
                if (!nxt.equals("list")) {
                    String first = splitBySpace[0];
                    //Command: Set task as done
                    if (first.equals("done")) {
                        int num = Integer.parseInt(splitBySpace[1]);
                        dukeList.get(num-1).markAsDone();
                        System.out.println("The following has been marked as done:");
                        System.out.println("  " + dukeList.get(num - 1));
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
                            dukeList.add(t);
                            System.out.println("I have added this task: \n  " + dukeList.get(dukeList.size() - 1));
                            System.out.println("Now you have " + dukeList.size() + " task(s) in the list.");
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
                            dukeList.add(t);
                            System.out.println("I have added this task: \n  " + dukeList.get(dukeList.size() - 1));
                            System.out.println("Now you have " + dukeList.size() + " task(s) in the list.");
                        }
                        //Command: to do task
                    } else if (first.equals("todo")) {
                        if (splitBySpace.length == 1) {
                            throw new DukeException("todo");
                        } else {
                            for (int i = 1; i < splitBySpace.length - 1; i++) {
                                newString = newString + splitBySpace[i] + " ";
                            }
                            newString = newString + splitBySpace[splitBySpace.length - 1];
                            Task t = new ToDo(newString);
                            dukeList.add(t);
                            System.out.println("I have added this task: \n  " + dukeList.get(dukeList.size() - 1));
                            System.out.println("Now you have " + dukeList.size() + " task(s) in the list.");
                        }
                        //Command: delete
                    } else if (first.equals("delete")) {
                        if (splitBySpace.length == 1) {
                            throw new DukeException("delete");
                        } else if (splitBySpace.length != 2) {
                            throw new DukeException("delete argument not found");
                        } else {
                            int numToRemove = Integer.parseInt(splitBySpace[1]) - 1;
                            System.out.println("Noted. I have removed this task:");
                            System.out.println("  " + dukeList.get(numToRemove));
                            dukeList.remove(numToRemove);
                            System.out.println("Now you have " + dukeList.size() + " task(s) in the list.");
                        }
                    } else {
                        throw new DukeException("don't understand");
                    }
                    //Print out the list
                } else {
                    if (dukeList.size() == 0) {
                        System.out.println("The list is empty.");
                    } else {
                        for (int i = 0; i < dukeList.size(); i++) {
                            System.out.println((i + 1) + "." + dukeList.get(i));
                        }
                    }
                }
            } catch (DukeException e) {
                System.out.println(e);
            }

            //Get next input
            nxt = sc.nextLine();
        }

        System.out.println("さらbye. Hope to see you again soon! ( ﾟ▽ﾟ)/");
    }
}
