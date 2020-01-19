import java.util.ArrayList;
import java.util.Scanner;

//deadline timing not working returns null
//event toString not done

public class Duke {

    public static void main(String[] args) {

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        printLogo();
        printGreeting();

        while (true) {
            //Split the input line into description and time portions
            String[] input = sc.nextLine().split("/");
            //Further split the description for command checking
            String[] descriptionTokens = input[0].split(" ");
            printDivider();
            if (input.length < 2) {  //Regular commands and Todo
                if (descriptionTokens[0].toLowerCase().equals("bye")) {
                    break;

                } else if (descriptionTokens[0].toLowerCase().equals("list")) {
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i + 1) + "." + taskList.get(i));
                    }

                } else if (descriptionTokens[0].toLowerCase().equals("done")) {
                    System.out.println("> Another task off the list. Good job!");
                    taskList.get(Integer.parseInt(descriptionTokens[1]) - 1).markAsDone();
                    System.out.println("  " + taskList.get(Integer.parseInt(descriptionTokens[1]) - 1));

                } else if (descriptionTokens[0].toLowerCase().equals("todo")) {
                    StringBuilder builder = new StringBuilder(descriptionTokens[1]);
                    for (int i = 2; i < descriptionTokens.length; i++) {
                        builder.append(" ");
                        builder.append(descriptionTokens[i]);
                    }

                    taskList.add(new Todo(builder.toString()));
                    System.out.println("> I've got your back. Adding the new task: ");
                    System.out.println("  " + taskList.get(taskList.size() - 1).toString());
                    System.out.println("  Now you've " + Task.getTotalTaskCount() + " task(s) in your list");

                }
            } else {
                //Concat the description tokens back to one string
                StringBuilder description = new StringBuilder(descriptionTokens[1]);
                for (int i = 2; i < descriptionTokens.length; i++) {
                    description.append(" ");
                    description.append(descriptionTokens[i]);
                }

                //Check what type of task
                if (descriptionTokens[0].toLowerCase().equals("deadline")) {
                    taskList.add(new Deadline(description.toString(), input[1].substring(3)));
                    System.out.println("> I've got your back. Adding the new task: ");
                    System.out.println("  " + taskList.get(taskList.size() - 1).toString());
                    System.out.println("  Now you've " + Task.getTotalTaskCount() + " task(s) in your list");

                } else if (descriptionTokens[0].toLowerCase().equals("event")) {
                    String[] endTokens = input[1].split("-");  //Get the end timing
                    String[] frontTokens = endTokens[0].split(" "); //Seperate start time from date

                    //Concat the date into one string
                    StringBuilder date = new StringBuilder(frontTokens[1]);
                    for (int i = 2; i < frontTokens.length - 1; i++) {
                        date.append(" ");
                        date.append(frontTokens[i]);
                    }

                    taskList.add(new Event(description.toString(), date.toString(), frontTokens[frontTokens.length - 1],
                            endTokens[1]));
                    System.out.println("> I've got your back. Adding the new task: ");
                    System.out.println("  " + taskList.get(taskList.size() - 1).toString());
                    System.out.println("  Now you've " + Task.getTotalTaskCount() + " task(s) in your list");

                }
            }
            printDivider();
        }
        System.out.println("> It's nice talking to you. See you soon! ;)");
    }

    private static void printDivider() {
        System.out.println("--------------------------------------------");
    }

    private static void printGreeting() {
        System.out.println("Hi! I'm Aelita, guardian of Lyoko.");
        System.out.println("How can I help you?");
        printDivider();
    }

    private static void printLogo() {
        final String logo = "     __             _   _     _\n"
                + "    /  \\           | | / \\   | |\n"
                + "   / /\\ \\     ___  | | \\_/ __| |__   ___ _\n"
                + "  / /__\\ \\   / _ \\ | |  _ |__   __| / _ | |\n"
                + " / ______ \\ |  __/ | | | |   | |   | |_|  |\n"
                + "/_/      \\_\\ \\___\\ |_| |_|   |_|    \\___/\\_\\";
        System.out.println(logo);
        System.out.println("============================================");
    }
}
