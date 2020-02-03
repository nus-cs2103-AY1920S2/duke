import javafx.concurrent.Task;

import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.PrintWriter;

public class Duke {
    public static void main(String[] args) throws DukeException, IOException {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        File myfile = new File("Duke.txt");
        boolean check = myfile.exists();
        myfile.createNewFile();
        Scanner fsc = new Scanner(myfile);
        Scanner sc = new Scanner(System.in);

        String Hello = "Hello! I'm Duke\nWhat can i do for you?";
        System.out.println(Hello);
        int flag = 0;
        ArrayList<task> mylist = new ArrayList<>();

        if (check == false) {

        } else {
            while (fsc.hasNextLine()) {
                String currline = fsc.nextLine();
                String[] temparr = currline.split(" /n ");

                if (temparr[0].equals("T")) {
                    ToDo td = new ToDo(temparr[2]);

                    if (temparr[1].equals("1")) {
                        td.markDone();
                    } else {

                    }

                    mylist.add(td);
                } else  if (temparr[0].equals("D")) {
                    DeadLine d = new DeadLine(temparr[2], temparr[3]);

                    if (temparr[1].equals("1")) {
                        d.markDone();
                    } else {

                    }

                    mylist.add(d);
                } else if (temparr[0].equals("E")) {
                    Event e = new Event(temparr[2], temparr[3]);

                    if (temparr[1].equals("1")) {
                        e.markDone();
                    } else {

                    }

                    mylist.add(e);
                } else {

                }
            }
            System.out.println("You currently have " + mylist.size() + " items from the previous session\n");

        }



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
                        updateTxtFile(mylist, myfile);
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
                        updateTxtFile(mylist, myfile);
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
                        updateTxtFile(mylist, myfile);
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
                        updateTxtFile(mylist, myfile);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! You're missing some descriptions for your event.", e);
                    }

                } else if (inarr[0].equals("delete")) {
                    try {
                        int toremove = Integer.parseInt(inarr[1]);
                        removetask(mylist, toremove);
                        updateTxtFile(mylist, myfile);
                    } catch(IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! That task doesn't exist or you failed to include a number.", e);
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

    private static void updateTxtFile (ArrayList<task> ls, File fl) throws IOException {
        PrintWriter pw = new PrintWriter(fl);
        pw.write("");
        pw.close();

        PrintWriter toWrite = new PrintWriter(fl);
        String finalToWrite = "";

        for (int i = 0; i < ls.size(); i++) {
            String temp = ls.get(i).toString();
            String[] temparr = temp.split("] ");
            String[] temparr1 = temparr[0].split("]");
            String toAppend = "";
            if (temparr1[0].equals("[T")) {
                toAppend = toAppend + "T /n ";

                if (temparr1[1].equals("[✓")) {
                    toAppend = toAppend + "1 /n ";
                } else {
                    toAppend = toAppend + "0 /n ";
                }

                toAppend = toAppend + temparr[1];
            } else if (temparr1[0].equals("[D")) {
                toAppend = toAppend + "D /n ";

                if (temparr1[1].equals("[✓")) {
                    toAppend = toAppend + "1 /n ";
                } else {
                    toAppend = toAppend + "0 /n ";
                }

                String[] temparr2 = temparr[1].split(" \\(by: ");

                toAppend = toAppend + temparr2[0] + " /n " + temparr2[1].substring(0, temparr2[1].length() - 1);
            } else if (temparr1[0].equals("[E")) {
                toAppend = toAppend + "E /n ";

                if (temparr1[1].equals("[✓")) {
                    toAppend = toAppend + "1 /n ";
                } else {
                    toAppend = toAppend + "0 /n ";
                }

                String[] temparr2 = temparr[1].split(" \\(at: ");

                toAppend = toAppend + temparr2[0] + " /n " + temparr2[1].substring(0, temparr2[1].length() - 1);
            } else {

            }
            toAppend = toAppend + "\n";
            finalToWrite = finalToWrite + toAppend;
        }

        toWrite.write(finalToWrite);
        toWrite.close();
    }
}
