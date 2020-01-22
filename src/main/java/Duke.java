import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n I am at your service\n");
        String botReplyLine = "----------------------------------------------";


        Scanner sc = new Scanner(System.in);
        ArrayList<Task> arr = new ArrayList<>();


        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            String[] arrString = userInput.split(" ", 2);
            try {
                if (arrString[0].equalsIgnoreCase("bye")) {
                    System.out.println(botReplyLine + "\n Duke: I'll say goodnight now \n" + botReplyLine);
                    break;
                } else if (arrString[0].equalsIgnoreCase("list")) {
                    System.out.println(botReplyLine);
                    if (arr.size() == 0) {
                        System.out.println("You currently do not have anything in your list");
                        System.out.println(botReplyLine);
                    } else {
                        for (int i = 0; i < arr.size(); i++) {
                            System.out.println(i + 1 + ". " + arr.get(i).toString());
                        }
                        System.out.println(botReplyLine);
                    }
                } else if (arrString[0].equalsIgnoreCase("done")) {
                    try {
                        int taskNumber = Integer.parseInt(arrString[1].strip()) - 1;
                        if (taskNumber >= 0 && taskNumber < arr.size()) {
                            arr.get(taskNumber).doneTask();
                            System.out.println(botReplyLine);
                            System.out.println("Consider it done.");
                            System.out.println(arr.get(taskNumber).toString());
                        } else {
                            System.out.println(botReplyLine);
                            System.out.println("Invalid task number");
                        }
                        System.out.println(botReplyLine);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new DukeException(botReplyLine + "\n Missing task number \n" + botReplyLine);
                    }


                } else if (arrString[0].equalsIgnoreCase("delete")) {
                    try {
                        int taskNumber = Integer.parseInt(arrString[1].strip()) - 1;
                        if (taskNumber >= 0 && taskNumber < arr.size()) {
                            System.out.println(botReplyLine);
                            System.out.println("Consider it deleted.");
                            System.out.println(arr.get(taskNumber).toString());
                            arr.remove(taskNumber);
                            System.out.println("You have " + arr.size() + " task(s) in your list.");
                        } else {
                            System.out.println(botReplyLine);
                            System.out.println("Invalid task number");

                        }
                        System.out.println(botReplyLine);

                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new DukeException(botReplyLine + "\n Missing task number \n" + botReplyLine);
                    }


                } else if (arrString[0].equalsIgnoreCase("todo")) {
                    try {
                        Todo todo = new Todo(arrString[1]);
                        arr.add(todo);
                        System.out.println(botReplyLine + "\n Duke: added your command. \n");
                        System.out.println(todo.toString());
                        System.out.println("You have " + arr.size() + " task(s) in your list.");
                        System.out.println(botReplyLine);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new DukeException(botReplyLine + "\n Missing Todo description \n" + botReplyLine);
                    }
                } else if (arrString[0].equalsIgnoreCase("event")) {
                    try {
                        String[] eventString = arrString[1].split("/");
                        Event event = new Event(eventString[0], eventString[1].substring(2).strip());
                        arr.add(event);
                        System.out.println(botReplyLine + "\n Duke: added your command. \n");
                        System.out.println(event.toString());
                        System.out.println("You have " + arr.size() + " task(s) in your list.");
                        System.out.println(botReplyLine);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new DukeException(botReplyLine + "\n Event not complete \n" + botReplyLine);
                    }
                } else if (arrString[0].equalsIgnoreCase("deadline")) {
                    try {
                        String[] deadlineString = arrString[1].split("/");
                        Deadline deadline = new Deadline(deadlineString[0], deadlineString[1].substring(2).strip());
                        arr.add(deadline);
                        System.out.println(botReplyLine + "\n Duke: added your command. \n");
                        System.out.println(deadline.toString());
                        System.out.println("You have " + arr.size() + " task(s) in your list.");
                        System.out.println(botReplyLine);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new DukeException(botReplyLine + "\n Deadline not complete \n" + botReplyLine);
                    }
                } else {
                    throw new DukeException(botReplyLine + " \n Sorry I do not understand. \n" + botReplyLine);
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }


    }
}

