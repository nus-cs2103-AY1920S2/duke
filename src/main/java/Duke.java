import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you today?");
        System.out.println("Please tell me what to do!");
        System.out.println("\n");
        System.out.println("====================================================================================");
        Scanner sc = new Scanner(System.in);
        String exitCommand = "bye";
        ArrayList<Task> listOfText = new ArrayList<Task>();

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.toLowerCase().equals(exitCommand)) {
                break;
            }

            if (input.toLowerCase().equals("list")) {
                int counter = 1;
                if (listOfText.size() == 0) {
                    System.out.println("To Do List is empty! Congratulations!");
                }
                for (int i = 0; i < listOfText.size(); i++) {
                    System.out.println(counter + ". " + listOfText.get(i));
                    counter++;
                }
                continue;
            }

            String[] splitStr = input.split("\\s+");
            if (splitStr[0].toLowerCase().equals("done")) {
                int index = Integer.parseInt(splitStr[1]) - 1;
                Task currentTask = listOfText.get(index);
                currentTask.markAsDone();
                System.out.println("Nice! I've marked this task as done and dusted:");
                System.out.println(currentTask);
                continue;
            }

            if (splitStr[0].toLowerCase().equals("delete")) {
                int index = Integer.parseInt(splitStr[1]) - 1;
                Task currentTask = listOfText.get(index);
                listOfText.remove(index);
                System.out.println("Bye bye Task! I've removed this task:");
                System.out.println(currentTask);
                System.out.println("Now you have " + listOfText.size() + " tasks in the list.");
                continue;
            }

            if (splitStr[0].toLowerCase().equals("deadline")) {
             try {
                 LocalDate d1 = LocalDate.now().minus(1,ChronoUnit.MONTHS);
                String deadline = "";
                for (int i = 1; i < splitStr.length; i++) {
                    if ((splitStr[i].equals("/by"))) {
                        d1 = LocalDate.parse(splitStr[i+1]);
                        break;
                    } else {
                        deadline += splitStr[i] + " ";
                    }
                }
                deadline = deadline.substring(0, deadline.length() - 1);

                if (d1.isBefore(LocalDate.now())) {
                    throw new DukeException("☹ OOPS!!! You cannot set a date that is in the past! ☹ OOPS!!!");
                } else {
                    Deadline d = new Deadline(deadline, d1);
                    listOfText.add(d);
                    System.out.println("Got you covered! Added this task to the list:");
                    System.out.println(d);
                    System.out.println("Now you have " + listOfText.size() + " tasks in the list.");
                }
                } catch (DukeException e) {
                System.out.println(e);

            } finally {
                continue;
            }

            }

            if (splitStr[0].toLowerCase().equals("event")) {
                try {
                    String at = "";
                    String event = "";
                    for (int i = 1; i < splitStr.length; i++) {
                        if ((splitStr[i].equals("/at"))) {
                            for (int j = i + 1; j < splitStr.length; j++) {
                                at += splitStr[j] + " ";
                            }
                            break;
                        } else {
                            event += splitStr[i] + " ";
                        }
                    }
                    if (at.equals("")) {
                    throw new DukeException("☹ OOPS!!! Where is this event????? use /at to tell me! ☹ OOPS!!!");
                    } else {
                        event = event.substring(0, event.length() - 1);
                        at = at.substring(0, at.length() - 1);

                        Event e = new Event(event, at);
                        listOfText.add(e);
                        System.out.println("Got you covered! Added this task to the list: ");
                        System.out.println(e);
                        System.out.println("Now you have " + listOfText.size() + " tasks in the list.");
                    }
                } catch (DukeException e){
                    System.out.println(e);

                } finally {
                    continue;
                }
            }

            if (splitStr[0].toLowerCase().equals("todo")) {
                try {
                    if (splitStr.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty. ☹ OOPS!!!");
                    } else {
                        String todo = "";
                        for (int i = 1; i < splitStr.length; i++) {
                            todo += splitStr[i] + " ";
                        }
                        todo.substring(0, todo.length() - 1);
                        Todo t = new Todo(todo);
                        listOfText.add(t);
                        System.out.println("Got you covered! Added this task to the list: ");
                        System.out.println(t);
                        System.out.println("Now you have " + listOfText.size() + " tasks in the list.");
                    }
                } catch (DukeException m) {
                    System.out.println(m);

                } finally {
                    continue;
                }
            }

        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-( ☹ OOPS!!!");
    }

        System.out.println("\n");
        System.out.println("====================================================================================");
        System.out.println("Bye bye! Thank you for using me! Hope to see you again soon.");

}
}
