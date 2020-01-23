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


        String[] arr = new String[100];
        Scanner sc =  new Scanner(System.in);
        String input = "";
        int count = 0;
        while (!input.equals("bye")) {
            input = sc.nextLine();
            switch (input) {
                case ("list"):
                    System.out.println(line);
                    for (var i = 0; i < count; i++) {
                        System.out.println(i + 1 + ": " + arr[i]);
                    }
                    System.out.println(line);
                    break;
                default:
                    arr[count] = input;
                    count++;
                    say("Added: " + input);

            }
        }
        say("Bye! Hope to see you again!");
    }
}
