package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    /***
     * Process user input and call classes to handle them accordingly
     */
    public void parse() {
        Scanner input = new Scanner(System.in);
        String word = input.nextLine();
        Storage storage = new Storage();
        Ui ui = new Ui();
        while (!word.equalsIgnoreCase("bye")) {
            ui.printLine();
            if (word.equalsIgnoreCase("list")) {
                TaskList.printList();
            } else {
                String[] words = word.split(" ");

                if (words[0].equalsIgnoreCase("done") && words[1].matches("\\d+")) {
                    int doneTarget = Integer.parseInt(words[1]);
                    if (doneTarget > 0 && doneTarget <= Duke.commandList.size()) {
                        TaskList.doneTask(Duke.commandList.get(doneTarget - 1));
                        storage.writeList();

                    } else {
                        ui.printErrorNotFound();
                    }
                } else if (words[0].equalsIgnoreCase("delete") && words[1].matches("\\d+")) {
                    int deleteTarget = Integer.parseInt(words[1]);
                    if (deleteTarget > 0 && deleteTarget <= Duke.commandList.size()) {
                        TaskList.deleteTask(Duke.commandList.get(deleteTarget - 1));
                        storage.writeList();
                    } else {
                        ui.printErrorNotFound();
                    }

                } else if (words[0].equalsIgnoreCase("todo")){
                    if (word.contains("todo ") && !word.substring(5).isEmpty()) {
                        String substr = word.substring(5);
                        ToDo task = new ToDo(substr);
                        TaskList.addTask(task);
                        storage.writeList();
                    } else {
                        ui.printErrorNoTaskName();
                    }
                } else if (words[0].equalsIgnoreCase("deadline")){
                    String substr = word.substring(9);
                    if (substr.contains(" /")) {
                        String[] deadlineSplit = substr.split(" /");
                        try {
                            LocalDate date = LocalDate.parse(deadlineSplit[1]);
                            Deadline task = new Deadline(deadlineSplit[0], date);
                            TaskList.addTask(task);
                        } catch (DateTimeParseException e) {
                            ui.printErrorWrongDateFormat();
                        }
                        storage.writeList();
                    } else {
                        ui.printErrorNoTime();
                    }

                } else if (words[0].equalsIgnoreCase("event")){
                    String substr = word.substring(6);
                    if (substr.contains(" /")) {
                        String[] eventSplit = substr.split(" /");

                        try {
                            LocalDate date = LocalDate.parse(eventSplit[1]);
                            Event task = new Event(eventSplit[0], date);
                            TaskList.addTask(task);
                        } catch (DateTimeParseException e) {
                            ui.printErrorWrongDateFormat();
                        }


                        storage.writeList();
                    } else {
                        ui.printErrorNoTime();
                    }
                } else if (words[0].equalsIgnoreCase("find")) {
                    ArrayList<Task> taskFound = new ArrayList<>();
                    for (Task task : Duke.commandList) {
                        if (task.name.contains(words[1])) {
                            taskFound.add(task);
                        }
                    }
                    System.out.println("Here are the matching tasks in your list:");
                    for (int i = 0; i < taskFound.size(); i++) {
                        int a = i + 1;
                        System.out.print(a + ". " + taskFound.get(i));
                    }




                } else{
                    ui.printErrorUnderstanding();
                }

            }
            ui.printLine();
            word = input.nextLine();
        }
    }
}
