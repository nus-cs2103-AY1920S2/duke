import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Duke {
    static ArrayList<Task> commandList = new ArrayList<>();

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String sentence = s.nextLine();
            String type = sentence.substring(4, 5);
            boolean done = true;
            if (sentence.substring(7, 8).equals("✗")){
                done = false;
            }
            if (type.equals("T")){
                String substr = sentence.substring(10);
                ToDo task = new ToDo(substr, done);
                commandList.add(task);

            } else if (type.equals("D")) {
                String substr = sentence.substring(10);
                String[] deadlineSplit = substr.split(" \\| by: ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                LocalDate date = LocalDate.parse(deadlineSplit[1], formatter);
                Deadline task = new Deadline(deadlineSplit[0], date, done);
                commandList.add(task);

            } else {
                String substr = sentence.substring(10);
                String[] eventSplit = substr.split(" \\| at: ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                LocalDate date = LocalDate.parse(eventSplit[1], formatter);
                Event task = new Event(eventSplit[0], date, done);
                commandList.add(task);

            }

        }
    }

    private static void printList() {
        if (commandList.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < commandList.size(); i++) {
                int a = i + 1;
                System.out.print(a + ". " + commandList.get(i));
            }
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, I can't find any task in your list");
        }
    }

    private static void writeList() {
        String file2 = "data/duke.txt";
        String towrite = "";
        try {
            if (commandList.size() > 0) {
                for (int i = 0; i < commandList.size(); i++) {
                    int a = i + 1;
                    towrite+=a + ". " + commandList.get(i);
                    writeToFile(file2, towrite);
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            printFileContents("data/duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }







        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printList();
        System.out.println("____________________________________________________________");

        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner input = new Scanner(System.in);
        String word = input.nextLine();
        while (!word.equalsIgnoreCase("bye")) {
            System.out.println("____________________________________________________________");
            if (word.equalsIgnoreCase("list")) {
                printList();
            }



            else {
                String[] words = word.split(" ");

                if (words[0].equalsIgnoreCase("done") && words[1].matches("\\d+")) {
                    int doneTarget = Integer.parseInt(words[1]);
                    if (doneTarget > 0 && doneTarget <= commandList.size()) {
                        commandList.get(doneTarget - 1).setDone();
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println(commandList.get(doneTarget - 1));
                        writeList();

                    } else {
                        System.out.println("☹ OOPS!!! I'm sorry, I can't find that task");
                    }

                }

                else if (words[0].equalsIgnoreCase("delete") && words[1].matches("\\d+")) {
                    int deleteTarget = Integer.parseInt(words[1]);
                    if (deleteTarget > 0 && deleteTarget <= commandList.size()) {
                        Task task = commandList.get(deleteTarget - 1);
                        commandList.remove(deleteTarget - 1);
                        System.out.println("Noted. I've removed this task: ");
                        System.out.print(task);
                        System.out.println("Now you have "+commandList.size()+" tasks in the list.");
                        writeList();
                    } else {
                        System.out.println("☹ OOPS!!! I'm sorry, I can't find that task");
                    }

                }
                else if (words[0].equalsIgnoreCase("todo")){
                    if (word.contains("todo ") && !word.substring(5).isEmpty()) {
                        String substr = word.substring(5);
                        ToDo task = new ToDo(substr);
                        commandList.add(task);
                        System.out.println("Got it. I've added this task: ");
                        System.out.print(task);
                        System.out.println("Now you have " + commandList.size()+ " tasks in the list.");
                        writeList();
                    } else {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                }

                else if (words[0].equalsIgnoreCase("deadline")){
                    String substr = word.substring(9);
                    if (substr.contains(" /")) {
                        String[] deadlineSplit = substr.split(" /");
                        LocalDate date = LocalDate.parse(deadlineSplit[1]);
                        Deadline task = new Deadline(deadlineSplit[0], date);
                        commandList.add(task);
                        System.out.println("Got it. I've added this task: ");
                        System.out.print(task);
                        System.out.println("Now you have "+commandList.size()+" tasks in the list.");
                        writeList();
                    }
                    else {
                        System.out.println("☹ OOPS!!! I'm sorry, but you need to specify the deadline");
                    }

                }

                else if (words[0].equalsIgnoreCase("event")){
                    String substr = word.substring(6);
                    if (substr.contains(" /")) {
                        String[] eventSplit = substr.split(" /");
                        LocalDate date = LocalDate.parse(eventSplit[1]);
                        Event task = new Event(eventSplit[0], date);
                        commandList.add(task);
                        System.out.println("Got it. I've added this task: ");
                        System.out.print(task);
                        System.out.println("Now you have "+commandList.size()+" tasks in the list.");
                        writeList();
                    }
                    else {
                        System.out.println("☹ OOPS!!! I'm sorry, but you need to specify the event time");
                    }
                }
                else{
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }


            }
            System.out.println("____________________________________________________________");
            word = input.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }

}
