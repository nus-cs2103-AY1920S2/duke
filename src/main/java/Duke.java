import main.java.*;
import java.lang.StringBuilder;
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
        System.out.println("Greetings, I am\n" + logo);
        System.out.println("How may I be of assistance today?");

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("These items are in your list: \n");
                for (int i = 0; i < dukeList.size(); i++){
                    System.out.println(i + 1 + ". " + dukeList.get(i));
                }
                command = sc.nextLine();
                continue;
            }
            else if (command.contains("done")){
                String[] splitCommand = command.split(" ");
                int index = Integer.parseInt(splitCommand[1]);
                dukeList.get(index - 1).markAsDone();
                System.out.println("You have completed this task.\n");
                System.out.println(dukeList.get(index - 1));
                command = sc.nextLine();
                continue;
            }
            else if (command.contains("delete")) {
                Duke.deleteTask(command, dukeList);
                command = sc.nextLine();
                continue;
            }
            try {
                Duke.addCommand(command, dukeList);
            } catch (CommandException | DescriptionException e) {
                command = sc.nextLine();
                continue;
            }
            System.out.println("Understood. I have added: " + dukeList.get(dukeList.size() - 1));
            System.out.println("Items in the list: " + dukeList.size());
            command = sc.nextLine();
        }

        System.out.println("It was my pleasure to help you.\n");

    }

    public static void addCommand(String str, ArrayList<Task> dukeList) throws CommandException, DescriptionException {
        if (str.contains("deadline")) {
            String[] splitStr = str.split("/by ");
            if (splitStr.length < 2) {
                throw (new DescriptionException());
            }
            String description = splitStr[0];
            String timing = splitStr[1];
            String[] splitCommand = description.split(" ");

            StringBuilder builder = new StringBuilder();
            for(int i = 1; i < splitCommand.length; i ++) {
                builder.append(splitCommand[i]);
                builder.append(" ");
                description = builder.toString();
                dukeList.add(new Deadline(description, timing));
            }
        }
        else if (str.contains("event")) {
            String[] splitStr = str.split("/at ");
            if (splitStr.length < 2) {
                throw (new DescriptionException());
            }
            String description = splitStr[0];
            String timing = splitStr[1];
            String[] splitCommand = description.split(" ");

            StringBuilder builder = new StringBuilder();
            for(int i = 1; i < splitCommand.length; i ++) {
                builder.append(splitCommand[i]);
                builder.append(" ");
                description = builder.toString();
                dukeList.add(new Event(description, timing));
            }
        }
        else if (str.contains("todo")) {
            String[] splitStr = str.split("todo ");

            if (splitStr.length == 1) {
                throw (new DescriptionException());
            }
            String description = splitStr[1];
            dukeList.add(new ToDo(description));
        }
        else {
            throw (new CommandException());
        }
    }
    public static void deleteTask(String str, ArrayList<Task> dukeList) {
        String[] splitStr = str.split(" ");
        int index = Integer.parseInt(splitStr[1]) - 1;
        System.out.println(dukeList.get(index) + " has been removed.");
        dukeList.remove(index);
        System.out.println("Items in the list: " + dukeList.size());
    }
}
