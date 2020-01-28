import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {
    public void startParsing(ArrayList<Task> tasks, String filepath) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String command = sc.nextLine();
            if(command.trim().toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
//            System.out.println("OK");
            try {
                commandHandler(command, tasks, filepath);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public void commandHandler (String command, ArrayList<Task> store, String filepath) throws DukeException{
        String[] check = command.split(" ");
        switch (check[0].toLowerCase()) {
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < store.size(); i++) {
                    System.out.println("" + (i + 1) + ". " + store.get(i));
                }
                break;
            case "done":
                if(check.length == 1) {
                    throw new DukeException("OOPS! The description of a done cannot be empty.");
                }
                int intOfInterest;
                try {
                    intOfInterest = Integer.parseInt(check[1]) - 1;
                } catch (NumberFormatException e) {
                    throw new DukeException("OOPS! Please use an Integer together with the done command");
                }
                if(intOfInterest >= store.size() || intOfInterest < 0) {
                    throw new DukeException("OOPS! The Integer you used is invalid. Index of Array is out of bounds.");
                }
                System.out.println("Nice! I've marked this task as done:");
                store.get(Integer.parseInt(check[1]) - 1).markAsDone();
                System.out.println(store.get(Integer.parseInt(check[1]) - 1));
                saveTasks(store, filepath);
                break;
            case "todo":
                if(check.length == 1) {
                    throw new DukeException("OOPS! The description of a todo cannot be empty.");
                }
                String tasking = "";
                for(int i = 1; i < check.length; i++) {
                    tasking += check[i];
                    tasking += " ";
                }
                tasking = tasking.trim();
                ToDo todo = new ToDo(tasking);
                store.add(todo);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + todo);
                System.out.println("Now you have " + store.size() + " tasks in the list.");
                saveTasks(store, filepath);
                break;
            case "deadline":
                if(check.length == 1) {
                    throw new DukeException("OOPS! The description of a deadline cannot be empty.");
                }
                String[] newCheck = Arrays.copyOfRange(check, 1, check.length);
                int by = Arrays.asList(newCheck).indexOf("/by");
                if(by == -1) {
                    throw new DukeException("OOPS! Please remember to insert an '/by' in the deadline description.");
                }
                String taskingD = "";
                for(int i = 0; i < by; i++) {
                    taskingD += newCheck[i];
                    taskingD += " ";
                }
                taskingD = taskingD.trim();

                String dL = "";
                for(int i = by + 1; i < newCheck.length; i++) {
                    dL += newCheck[i];
                    dL += " ";
                }
                dL = dL.trim();
                if(!dL.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}")) {
                    throw new DukeException("OOPS! Please enter deadline in the format yyyy-MM-dd HHmm. Thank you.");
                }
                LocalDateTime d1 = LocalDateTime.parse(dL , DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

                Deadline deadline = new Deadline(taskingD, d1);
                store.add(deadline);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + deadline);
                System.out.println("Now you have " + store.size() + " tasks in the list.");
                saveTasks(store, filepath);
                break;
            case "event":
                if(check.length == 1) {
                    throw new DukeException("OOPS! The description of an event cannot be empty.");
                }
                String[] newCheck2 = Arrays.copyOfRange(check, 1, check.length);
                int at = Arrays.asList(newCheck2).indexOf("/at");
                if(at == -1) {
                    throw new DukeException("OOPS! Please remember to insert an '/at' in the event description.");
                }

                String taskingE = "";
                for(int i = 0; i < at; i++) {
                    taskingE += newCheck2[i];
                    taskingE += " ";
                }
                taskingE = taskingE.trim();

                String time = "";
                for(int i = at + 1; i < newCheck2.length; i++) {
                    time += newCheck2[i];
                    time += " ";
                }
                time = time.trim();
                Event event = new Event(taskingE, time);
                store.add(event);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + event);
                System.out.println("Now you have " + store.size() + " tasks in the list.");
                saveTasks(store, filepath);
                break;
            case "delete":
                if(check.length == 1) {
                    throw new DukeException("OOPS! The description of a delete cannot be empty.");
                }
                int intOfInterestD;
                try {
                    intOfInterestD = Integer.parseInt(check[1]) - 1;
                } catch (NumberFormatException e) {
                    throw new DukeException("OOPS! Please use an Integer together with the delete command");
                }
                if(intOfInterestD >= store.size() || intOfInterestD < 0) {
                    throw new DukeException("OOPS! The Integer you used is invalid. Index of Array is out of bounds.");
                }

                Task temp = store.get(intOfInterestD);
                store.remove(intOfInterestD);
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + temp);
                System.out.println("Now you have " + store.size() + " tasks in the list.");
                saveTasks(store, filepath);
                break;
            default:
                throw new DukeException("OOPS! I'm sorry but I don't know what that means :-(");
        }
    }

    public static void saveTasks(ArrayList<Task> store, String filepath){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(filepath));
            //
            String saveAll = "";
            for(Task t : store) {
                String letter;
                if(t instanceof Deadline) {
                    Deadline temp = (Deadline) t;
                    letter = "D";
                    saveAll += letter + " | " + temp.getStatusIcon() + " | " + temp.description + " | " + temp.dL;
                    saveAll += "\n";
                } else if(t instanceof Event) {
                    Event temp = (Event) t;
                    letter = "E";
                    saveAll += letter + " | " + temp.getStatusIcon() + " | " + temp.description + " | " + temp.time;
                    saveAll += "\n";
                } else if(t instanceof ToDo) {
                    ToDo temp = (ToDo) t;
                    letter = "T";
                    saveAll += letter + " | " + temp.getStatusIcon() + " | " + temp.description;
                    saveAll += "\n";
                } else {}
            }
            writer.write(saveAll);
            //
            writer.close();

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

