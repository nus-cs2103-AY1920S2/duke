import java.util.ArrayList;
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
        String input = "";
        int count = 0;
        while (!input.equals("bye")) {
            input = sc.nextLine();
            String[] c = input.split(" ");
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
                default:
                    arr[count] = new Task(input);
                    count++;
                    say("Added: " + input);
            }
        }
        say("Bye! Hope to see you again!");
    }
}
