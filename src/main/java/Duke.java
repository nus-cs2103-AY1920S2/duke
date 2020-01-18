import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static ArrayList<String> arrList;

    public static void main(String[] args) {

        // Introduction

        String intro =
            "Hello! I'm Duke\n" +
            "What can I do for you?";

        System.out.println(stringWrapper((intro)));

        // Initialisation

        Scanner scanner = new Scanner(System.in);
        arrList = new ArrayList<>(100);

        // User Input

        while (true) {
            String input = scanner.nextLine();
            System.out.println(input);
            if (input.equals("list")) {
                String currentList = getCurrentList();
                System.out.println(stringWrapper(currentList));
            } else if (input.equals("bye")) {
                System.out.println(stringWrapper("Bye. Hope to see you again soon!"));
                break;
            } else {
                arrList.add(input);
                System.out.println(stringWrapper("added: " + input));
            }
        }

    }

    private static String getCurrentList() {
        String output = "";
        for (int i = 0; i < arrList.size(); i++) {
            if (i == arrList.size() - 1) {
                output += (i + 1) + ". " + arrList.get(i);
            } else {
                output += (i + 1) + ". " + arrList.get(i) + "\n";
            }
        }
        return output;
    }

    private static String stringWrapper(String string) {
        return "____________________\n" + string + "\n" + "____________________\n";
    }

}
