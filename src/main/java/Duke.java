import javafx.concurrent.Task;

import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;

public class Duke {
    public static void main(String[] args) throws DukeException, IOException {
        Scanner sc = new Scanner(System.in);

        String Hello = "Hello! I'm Duke\nWhat can i do for you?";
        System.out.println(Hello);

        Storage storage = new Storage();

        int flag = 0;
        ArrayList<task> mylist = new ArrayList<>();
        mylist = storage.readTxtFile();

        while(flag == 0) {
            String input = sc.nextLine();
            String[] inarr = input.split(" ");
            String Line = "_________________________________________________________________";

            System.out.println(Line);
            try {
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    flag = 1;
                } else if (input.equals("list")) {
                    printlist(mylist);
                } else if (inarr[0].equals("done")) {
                    try {
                        int tocheck = Integer.parseInt(inarr[1]);
                        task temp = mylist.get(tocheck - 1);
                        temp.markDone();

                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(mylist.get(tocheck - 1));
                        storage.updateTxtFile(mylist);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! That task doesn't exist or you failed to include a number.", e);
                    }
                } else if (inarr[0].equals("todo")) {
                    try {
                        ToDo temp = new ToDo(input.replaceFirst("todo ", ""));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(temp);
                        System.out.println("Now you have " + (mylist.size() + 1) + " tasks in the list.");
                        mylist.add(temp);
                        storage.updateTxtFile(mylist);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! You're missing some descriptions for your todo.", e);
                    }
                } else if (inarr[0].equals("deadline")) {
                    try {
                        String wodl = input.replaceFirst("deadline ", "");
                        String[] myarr = wodl.split(" /by ");
                        DeadLine temp = new DeadLine(myarr[0], myarr[1]);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(temp);
                        System.out.println("Now you have " + (mylist.size() + 1) + " tasks in the list.");
                        mylist.add(temp);
                        storage.updateTxtFile(mylist);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! You're missing some descriptions for your deadline.", e);
                    }
                } else if (inarr[0].equals("event")) {
                    try {
                        String woe = input.replaceFirst("event ", "");
                        String[] myarr = woe.split(" /at ");
                        Event temp = new Event(myarr[0], myarr[1]);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(temp);
                        System.out.println("Now you have " + (mylist.size() + 1) + " tasks in the list.");
                        mylist.add(temp);
                        storage.updateTxtFile(mylist);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! You're missing some descriptions for your event.", e);
                    }

                } else if (inarr[0].equals("delete")) {
                    try {
                        int toremove = Integer.parseInt(inarr[1]);
                        removetask(mylist, toremove);
                        storage.updateTxtFile(mylist);
                    } catch(IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! That task doesn't exist or you failed to include a number.", e);
                    }
                } else if (inarr[0].equals("find")) {
                    ArrayList<Integer> searchResults = new ArrayList<Integer>();
                    String keyword = input.replaceFirst("find ", "");

                    for (int i = 0; i < mylist.size(); i++) {
                        if (mylist.get(i).getName().contains(keyword)) {
                            searchResults.add(i);
                        } else {}
                    }

                    if (searchResults.size() == 0) {
                        System.out.println("Sorry, I can't seem to find anything related to " + keyword);
                    } else {
                        System.out.println("Here are the matching tasks in your list");

                        for (int i = 0; i < searchResults.size(); i++) {
                            System.out.println((i + 1) + "." + mylist.get(searchResults.get(i)));
                        }
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }

            System.out.println(Line);
        }
    }

    private static void printlist(ArrayList<task> ls) {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < ls.size(); i++) {
            System.out.println((i + 1) + "." + ls.get(i));
        }
    }

    private static void removetask (ArrayList<task> ls, int ind) {
        task temp = ls.remove(ind - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(temp);
        System.out.println("Now you have " + ls.size() + " tasks on the list.");
    }


}
