import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Class representation of Parser.
 */
public class Parser {
    /**
     * Parser's startParsing method. which consists of a while-loop
     * that takes in user input as commands
     * @param tasks
     * @param filepath
     */
    public void startParsing(ArrayList<Task> tasks, String filepath) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.trim().toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            try {
                commandHandler2(command, tasks, filepath);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    /**
     * Parser's commandHandler method, which handles every single command
     * from user.
     * @param command
     * @param store
     * @param filepath
     * @throws DukeException
     */
    public String commandHandler2(String command, ArrayList<Task> store, String filepath) throws DukeException {
        String output = "";
        String[] check = command.split(" ");
        switch (check[0].toLowerCase()) {
        case "list":
            output += ("Here are the tasks in your list:\n");
            for (int i = 0; i < store.size(); i++) {
                output += ("" + (i + 1) + ". " + store.get(i) + "\n");
            }
            break;
        case "done":
            assert check.length > 1: "Invalid Expression";
            if (check.length == 1) {
                throw new DukeException("OOPS! The description of a done cannot be empty.");
            }
            int intOfInterest;
            try {
                intOfInterest = Integer.parseInt(check[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS! Please use an Integer together with the done command");
            }
            if (intOfInterest >= store.size() || intOfInterest < 0) {
                throw new DukeException("OOPS! The Integer you used is invalid. Index of Array is out of bounds.");
            }
            output += ("Nice! I've marked this task as done:\n");
            store.get(Integer.parseInt(check[1]) - 1).markAsDone();
            output += (store.get(Integer.parseInt(check[1]) - 1));
            saveTasks(store, filepath);
            break;
        case "todo":
            assert check.length > 1: "Invalid Expression";
            if (check.length == 1) {
                throw new DukeException("OOPS! The description of a todo cannot be empty.");
            }
            String tasking = "";
            for (int i = 1; i < check.length; i++) {
                tasking += check[i];
                tasking += " ";
            }
            tasking = tasking.trim();
            ToDo todo = new ToDo(tasking);
            store.add(todo);
            output += ("Got it. I've added this task:\n");
            output += ("  " + todo + "\n");
            output += ("Now you have " + store.size() + " tasks in the list.");
            saveTasks(store, filepath);
            break;
        case "deadline":
            assert check.length > 1: "Invalid Expression";
            if (check.length == 1) {
                throw new DukeException("OOPS! The description of a deadline cannot be empty.");
            }
            String[] newCheck = Arrays.copyOfRange(check, 1, check.length);
            int by = Arrays.asList(newCheck).indexOf("/by");
            if (by == -1) {
                throw new DukeException("OOPS! Please remember to insert an '/by' in the deadline description.");
            }
            String taskingD = "";
            for (int i = 0; i < by; i++) {
                taskingD += newCheck[i];
                taskingD += " ";
            }
            taskingD = taskingD.trim();
          
            String dL = "";
            for (int i = by + 1; i < newCheck.length; i++) {
                dL += newCheck[i];
                dL += " ";
            }
            dL = dL.trim();
            if (!dL.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}")) {
                throw new DukeException("OOPS! Please enter deadline in the format yyyy-MM-dd HHmm. Thank you.");
            }
            LocalDateTime d1 = LocalDateTime.parse(dL, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            Deadline deadline = new Deadline(taskingD, d1);
            store.add(deadline);
            output += ("Got it. I've added this task:\n");
            output += ("  " + deadline + "\n");
            output += ("Now you have " + store.size() + " tasks in the list.");
            saveTasks(store, filepath);
            break;
        case "event":
            assert check.length > 1: "Invalid Expression";
            if (check.length == 1) {
                throw new DukeException("OOPS! The description of an event cannot be empty.");
            }
            String[] newCheck2 = Arrays.copyOfRange(check, 1, check.length);
            int at = Arrays.asList(newCheck2).indexOf("/at");
            if (at == -1) {
                throw new DukeException("OOPS! Please remember to insert an '/at' in the event description.");
            }


            String taskingE = "";
            for (int i = 0; i < at; i++) {
                taskingE += newCheck2[i];
                taskingE += " ";
            }
            taskingE = taskingE.trim();
            String time = "";
            for (int i = at + 1; i < newCheck2.length; i++) {
                time += newCheck2[i];
                time += " ";
            }
            time = time.trim();
            Event event = new Event(taskingE, time);
            store.add(event);
            output += ("Got it. I've added this task:\n");
            output += ("  " + event + "\n");
            output += ("Now you have " + store.size() + " tasks in the list.");
            saveTasks(store, filepath);
            break;
        case "delete":
            assert check.length > 1: "Invalid Expression";
            if (check.length == 1) {
                throw new DukeException("OOPS! The description of a delete cannot be empty.");
            }
            int intOfInterestD;
            try {
                intOfInterestD = Integer.parseInt(check[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS! Please use an Integer together with the delete command");
            }
            if (intOfInterestD >= store.size() || intOfInterestD < 0) {
                throw new DukeException("OOPS! The Integer you used is invalid. Index of Array is out of bounds.");
            }

            Task temp = store.get(intOfInterestD);
            store.remove(intOfInterestD);
            output += ("Noted. I've removed this task:\n");
            output += ("  " + temp + "\n");
            output += ("Now you have " + store.size() + " tasks in the list.");
            saveTasks(store, filepath);
            break;
        case "find":
            output += ("Here are the matching tasks in your list:\n");
            String toFind = "";
            for (int i = 1; i < check.length; i++) {
                toFind += check[i];
                toFind += " ";
            }
            toFind = toFind.trim();
            int counter = 1;
            for (int i = 0; i < store.size(); i++) {
                Task tempTask = store.get(i);
                if (tempTask.description.contains(toFind) || tempTask.tag.contains(toFind)) {
                    output += ("" + counter + ". " + tempTask + "\n");
                    counter++;
                }
            }
            break;
        case "tag":
            assert check.length > 1: "Invalid Expression";
            if (check.length < 3) {
                throw new DukeException("OOPS! The description of a tag should be in the form as such: tag 2 #Fun");
            }
            int intOfInterestTag;
            try {
                intOfInterestTag = Integer.parseInt(check[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS! Please use tag command in specific format. Eg. Tag 2 #Fun");
            }
            if (intOfInterestTag >= store.size() || intOfInterestTag < 0) {
                throw new DukeException("OOPS! The Integer you used is invalid. Index of Array is out of bounds.");
            }
            output += ("Nice! I've added a tag for this task:\n");
            store.get(Integer.parseInt(check[1]) - 1).setTag(check[2]);
            output += (store.get(Integer.parseInt(check[1]) - 1));
            saveTasks(store, filepath);
            break;
        case "bye":
            output = "Bye! See You Soon!";
            break;
        default:
            throw new DukeException("OOPS! I'm sorry but I don't know what that means :-(");
        }
        return output;
    }


    /**
     * Parser's saveTasks method, which saves any update in list to
     * harddrive, in a txt.file
     * @param store
     * @param filepath
     */
    public static void saveTasks(ArrayList<Task> store, String filepath) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(filepath));
            String saveAll = "";
            for (Task t : store) {
                String letter;
                if (t instanceof Deadline) {
                    Deadline temp = (Deadline) t;
                    letter = "D";
                    saveAll += letter + " | " + temp.toSave() + " | " + temp.tag + " | " + temp.description + " | " + temp.dL;
                    saveAll += "\n";
                } else if (t instanceof Event) {
                    Event temp = (Event) t;
                    letter = "E";
                    saveAll += letter + " | " + temp.toSave() + " | " + temp.tag + " | " + temp.description + " | " + temp.time;
                    saveAll += "\n";
                } else if (t instanceof ToDo) {
                    ToDo temp = (ToDo) t;
                    letter = "T";
                    saveAll += letter + " | " + temp.toSave() + " | " + temp.tag + " | " + temp.description;
                    saveAll += "\n";
                } else {
                    continue;
                }
            }
            writer.write(saveAll);
            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}

