import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AutoResponder {
    public static void main(String[] args) {
        String logo = "                _        _____                                 _           \n" +
                "     /\\        | |      |  __ \\                               | |          \n" +
                "    /  \\  _   _| |_ ___ | |__) |___  ___ _ __   ___  _ __   __| | ___ _ __ \n" +
                "   / /\\ \\| | | | __/ _ \\|  _  // _ \\/ __| '_ \\ / _ \\| '_ \\ / _` |/ _ \\ '__|\n" +
                "  / ____ \\ |_| | || (_) | | \\ \\  __/\\__ \\ |_) | (_) | | | | (_| |  __/ |   \n" +
                " /_/    \\_\\__,_|\\__\\___/|_|  \\_\\___||___/ .__/ \\___/|_| |_|\\__,_|\\___|_|   \n" +
                "                                        | |                                \n" +
                "                                        |_|                                ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What do you wish to do?");

        Scanner sc = new Scanner(System.in);
        List<String> todoList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("--------------------------------------------");
                System.out.println("Farewell. Thank you for using AutoResponder.");
                System.out.println("--------------------------------------------");
                break;
            }

            System.out.println("--------------------------------------------");
//            if (input.equals("list")) {
//                for (int i = 1; i <= todoList.size(); ++i) {
//                    System.out.println(i + ". " + todoList.get(i - 1));
//                }
//            } else {
//                todoList.add(input);
//                System.out.println("Task added: " + input);
//            }
            System.out.println(input);
            System.out.println("--------------------------------------------");

        }

    }
}
