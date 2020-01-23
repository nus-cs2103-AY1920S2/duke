package akshay;

import java.util.Scanner;

public class Akshay {
    private static String line = "~~~~~~~~~~~~~~~~~~~~~~~~";

    private static void say(String s) {
        System.out.println(line);
        System.out.println(s);
        System.out.println(line);
    }

    public static void main(String[] args) {
        say("Hello I am [AKSHAY]!\nHow may I help you?");
        Task[] arr = new Task[100];
        Scanner sc =  new Scanner(System.in);
        int count = 0;
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            String[] c = input.split(" ", 2);
            switch (c[0]) {
                case ("list"):
                    System.out.println(line);
                    System.out.println("Here are the items in your list:");
                    for (var i = 0; i < count; i++) {
                        Task t = arr[i];
                        System.out.println(i + 1 + ": " + t.toString());
                    }
                    System.out.println(line);
                    break;
                case ("done"):
                    Task curr = arr[Integer.parseInt(c[1]) - 1];
                    curr.mark();
                    say("Marked as done:\n" + curr.toString());
                    break;
                case ("todo"):
                    Task todo = new Todo(c[1]);
                    arr[count] = todo;
                    count++;
                    say("Added: " + todo.toString());
                    break;
                case ("deadline"):
                    String[] dl = c[1].split("/by",2);
                    Task d = new Deadline(dl[0], dl[1]);
                    arr[count] = d;
                    count++;
                    say("Added: " + d.toString());
                    break;
                case ("event"):
                    String[] ev = c[1].split("/at",2);
                    Task e = new Event(ev[0], ev[1]);
                    arr[count] = e;
                    count++;
                    say("Added: " + e.toString());
                    break;
                default:
                    say("Please enter correct input.");
            }
            input = sc.nextLine();
        }
        say("Bye! Hope to see you again!");
    }
}
