package akshay;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Akshay {
    private static String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    private static void say(String s) {
        System.out.println(line);
        System.out.println(s);
        System.out.println(line);
    }

    public static void main(String[] args) {
        say("Hello I am [AKSHAY]!\nHow may I help you?");
        ArrayList<Task> arr = new ArrayList<>(100);
        Scanner sc =  new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            String[] c = input.split(" ", 2);
            switch (c[0]) {
                case ("list"):
                    System.out.println(line);
                    System.out.println("Here are the items in your list:");
                    for (var i = 0; i < arr.size(); i++) {
                        Task t = arr.get(i);
                        System.out.println(i + 1 + ": " + t.toString());
                    }
                    System.out.println(line);
                    break;
                case ("done"):
                    Task curr = arr.get(Integer.parseInt(c[1]) - 1);
                    curr.mark();
                    say("Marked as done:\n" + curr.toString());
                    break;
                case ("todo"):
                    try {
                        Task todo = new Todo(c[1]);
                        arr.add(todo);
                        say("Added: " + todo.toString());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        say("OOPS!!! The description of a todo cannot be empty.");
                    }
                    break;
                case ("deadline"):
                    String[] dl = c[1].split("/by",2);
                    LocalDate d1 = LocalDate.parse(dl[1].strip());
                    Task d = new Deadline(dl[0], d1);
                    arr.add(d);
                    say("Added: " + d.toString());
                    break;
                case ("event"):
                    String[] ev = c[1].split("/at",2);
                    LocalDate d2 = LocalDate.parse(ev[1].strip());
                    Task e = new Event(ev[0], d2);
                    arr.add(e);
                    say("Added: " + e.toString());
                    break;
                case ("delete") :
                    try {
                        Task del = arr.get(Integer.parseInt(c[1]) - 1);
                        arr.remove(Integer.parseInt(c[1]) - 1);
                        say("Deleted item:\n" + del.toString());
                    } catch (Exception i) {
                        say("Failed to delete item!!!");
                    }
                    break;
                default:
                    try {
                        throw new DukeException();
                    } catch (DukeException de) {
                        say("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
            }
            save(arr);
            input = sc.nextLine();
        }
        say("Bye! Hope to see you again!");
    }
}

    public static void save(ArrayList<Task> data) throws IOException {
        StringBuilder s = new StringBuilder();
        for (Task datum : data) {
            s.append(datum.saveFormat()).append("\n");
        }
        FileWriter fileWriter = new FileWriter("./data/duke.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(s);
        printWriter.close();
    }
}