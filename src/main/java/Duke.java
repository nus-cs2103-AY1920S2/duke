import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        String line = "    ____________________________________________________________" + "\n";
        String fiveSpaces = "      ";
        System.out.println(line + fiveSpaces + "Hello! I'm Duke\n" + fiveSpaces + "Whatcha wanna do?\n" + line);
        ArrayList<Task> tasks = new ArrayList<>();
        boolean continueDoLoop = true;
        do {
            try {
                while (scanner.hasNextLine()) {
                    Scanner commandScanner = new Scanner(scanner.nextLine());
                    String word = commandScanner.next();
                    if (word.equals("bye")) {
                        System.out.println(line + fiveSpaces + "See ya later alligator!\n" + line);
                        continueDoLoop = false;
                        break;
                    } else {
                        String taskName = "";
                        switch (word) {
                            case "list":
                                System.out.print(line);
                                System.out.println("     Here are the tasks in your list:");
                                /*for (Task task : tasks) {
                                    System.out.println("      " + Integer.toString(task.thisTaskNum) + "." + task);
                                }*/
                                for(int i = 0; i < tasks.size(); i++){
                                    System.out.println("      " + Integer.toString(i + 1) + "." + tasks.get(i));
                                }
                                System.out.println(line);
                                break;

                            case "done":
                                int doneTaskNum = Integer.parseInt(commandScanner.next()) - 1;
                                tasks.get(doneTaskNum).isDone = true;
                                System.out.print(line);
                                System.out.println("     Nice! I've marked this task as done:");
                                System.out.println("       " + tasks.get(doneTaskNum).toString());
                                System.out.println(line);
                                break;

                            case "delete":
                                int deleteTaskNum = Integer.parseInt(commandScanner.next()) - 1;
                                System.out.print(line);
                                System.out.println("     Noted. I've removed this task:");
                                System.out.println("       " + tasks.get(deleteTaskNum).toString());
                                tasks.remove(deleteTaskNum);
                                System.out.println("     Now you have " + Integer.toString(tasks.size()) + " tasks in the list.");
                                System.out.println(line);
                                break;

                            case "todo":
                                if (!commandScanner.hasNext()) {
                                    throw new DukeException(word);
                                } else {
                                    while (commandScanner.hasNext()) {
                                        taskName += commandScanner.next() + " ";
                                    }
                                    taskName = taskName.substring(0, taskName.length() - 1);
                                    Task todoTask = new Todo(taskName);
                                    tasks.add(todoTask);
                                    System.out.print(line);
                                    System.out.println("     Got it. I've added this task:");
                                    System.out.println("       " + todoTask.toString());
                                    System.out.println("     Now you have " + Integer.toString(tasks.size()) + " tasks in the list.");
                                    System.out.println(line);
                                    break;
                                }

                            case "deadline":
                                while (commandScanner.hasNext()) {
                                    taskName += commandScanner.next() + " ";
                                }
                                taskName = taskName.substring(0, taskName.length() - 1);
                                String deadlineTask[] = taskName.split("/by ");
                                Task newDeadLineTask = new Deadline(deadlineTask[0], deadlineTask[1]);
                                tasks.add(newDeadLineTask);
                                System.out.print(line);
                                System.out.println("     Got it. I've added this task:");
                                System.out.println("       " + newDeadLineTask.toString());
                                System.out.println("     Now you have " + Integer.toString(tasks.size()) + " tasks in the list.");
                                System.out.println(line);
                                break;

                            case "event":
                                while (commandScanner.hasNext()) {
                                    taskName += commandScanner.next() + " ";
                                }
                                taskName = taskName.substring(0, taskName.length() - 1);
                                String eventTask[] = taskName.split("/at ");
                                Task newEventTask = new Event(eventTask[0], eventTask[1]);
                                tasks.add(newEventTask);
                                System.out.print(line);
                                System.out.println("     Got it. I've added this task:");
                                System.out.println("       " + newEventTask.toString());
                                System.out.println("     Now you have " + Integer.toString(tasks.size()) + " tasks in the list.");
                                System.out.println(line);
                                break;

                            default:
                                throw new DukeException("");
                        }
                    }
                }
            } catch (DukeException ex) {
                System.out.print(line);
                System.out.println(ex);
                System.out.println(line);
            }
        } while(continueDoLoop);
    }
}