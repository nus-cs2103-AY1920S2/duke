import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Duke {
    public static final String FILEPATH = "data\\duke.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = ListStateSaver.loadSavedList(FILEPATH);
        System.out.println("Hello there! I am Duke, your personal assistant. May I know your name?");
        String name = scanner.nextLine();

        boolean isActiveMode = true;

        while (isActiveMode) {
            System.out.println("How may I help you " + name + "?");
            String command = scanner.nextLine();
            String keyWord = command.split(" ")[0].toLowerCase();
            switch (keyWord) {
            case "list":
                for (int currNum = 1; currNum <= taskList.size(); currNum++) {
                    System.out.println("\t" + currNum + ". " + taskList.get(currNum - 1));
                }
                break;

            case "todo":
                try {
                    String taskName = command.split("todo ")[1];
                    if (taskName.equals("")) {
                        throw new DukeException("Invalid Todo Format");
                    }
                    Todo newTodo = new Todo(taskName);
                    System.out.println("\t" + "Awesome! I've added this todo:");
                    System.out.println("\t\t" + newTodo);
                    taskList.add(newTodo);
                    System.out.println("\t" + "Now, you have " + taskList.size() + " tasks in the list.");
                }
                catch (DukeException e) {
                    System.out.println("\t" + e);
                    System.out.println("\t\t" + "Please do not leave todo description as empty.");
                }
                catch (Exception e) {
                    System.out.println("\t\t" + "Please do not leave todo description as empty.");
                }
                break;

            case "deadline":
                try {
                    String[] tokens = command.split("/by ");
                    String dueDate = tokens[1];
                    String deadlineName = tokens[0].split("deadline ")[1].trim();
                    if (dueDate.equals("") || deadlineName.equals("")) {
                        throw new DukeException("Invalid Deadline Format");
                    }
                    Deadline newDeadline = new Deadline(deadlineName, dueDate);
                    System.out.println("\t" + "Awesome! I've added this deadline:");
                    System.out.println("\t\t" + newDeadline);
                    taskList.add(newDeadline);
                    System.out.println("\t" + "Now, you have " + taskList.size() + " tasks in the list.");
                }
                catch (DukeException e){
                    System.out.println("\t" + e);
                    System.out.println("\t\t" + "Please do not leave deadline description as empty.");
                }
                catch (DateTimeParseException e) {
                    System.out.println("\t" + "Please write the date and time in this format:" +
                            "YYYY-MM-DD hh:mm. For example, 1997-05-27 21:02 is the format" +
                            " to represent 9:02pm on 27 May 1997");
                }
                catch (Exception e) {
                    System.out.println("\t\t" + "Please do not leave deadline description as empty.");
                }
                break;

            case "event":
                try {
                    String[] words = command.split("/at ");
                    String timePeriod = words[1];
                    String eventName = words[0].split("event ")[1].trim();
                    if (timePeriod.equals("") || eventName.equals("")) {
                        throw new DukeException("Invalid Event Format");
                    }
                    Event newEvent = new Event(eventName, timePeriod);
                    System.out.println("\t" + "Awesome! I've added this event:");
                    System.out.println("\t\t" + newEvent);
                    taskList.add(newEvent);
                    System.out.println("\t" + "Now, you have " + taskList.size() + " tasks in the list.");
                }
                catch (DukeException e){
                    System.out.println("\t" + e);
                    System.out.println("\t\t" + "Please do not leave deadline description as empty.");
                }
                catch (DateTimeParseException e) {
                    System.out.println("\t" + "Please write the date and time in this format:" +
                            "YYYY-MM-DD hh:mm. For example, 1997-05-27 21:02 is the format" +
                            " to represent 9:02pm on 27 May 1997");
                }
                catch (Exception e) {
                    System.out.println("\t\t" + "Please do not leave event description as empty.");
                }
                break;

            case "whatsup":
                try {
                    String[] words = command.split("/on");
                    LocalDate queryDate = LocalDate.parse(words[1].trim());
                    ArrayList<Task> resultTasks = new ArrayList<>();
                    for (Task task: taskList) {
                        if (task instanceof Deadline &&
                                ((Deadline) task).getDueDate().toLocalDate().equals(queryDate)) {
                            resultTasks.add(task);
                        } else if (task instanceof Event &&
                                ((Event) task).getTimePeriod().toLocalDate().equals(queryDate)) {
                            resultTasks.add(task);
                        }
                    }
                    if (resultTasks.isEmpty()) {
                        System.out.println("You have nothing assigned on that day.");
                    } else {
                        System.out.println("\t" + "The tasks you have on that day are:");
                        for (Task task: resultTasks) {
                            System.out.println("\t\t" + task);
                        }
                    }

                } catch (DateTimeParseException e) {
                    System.out.println("\t" + "Please write the date in this format:" +
                            "YYYY-MM-DD");
                } catch (Exception e) {
                    System.out.println("Please do not leave the description as empty.");
                }
                break;

            case "done":
                try {
                    Task toComplete = taskList.get(Integer.parseInt(command.split(" ")[1]) - 1);
                    toComplete.completeStatus();
                    System.out.println("\t" + "Hooray! You've finally managed to finish this task:");
                    System.out.println("\t\t" + toComplete);
                }
                catch (Exception e) {
                    System.out.println("\t\t" + "Please specify which task you wish to mark as completed.");
                }
                break;

            case "delete":
                try {
                    Task deleted = taskList.remove(Integer.parseInt(command.split(" ")[1]) - 1);
                    System.out.println("\t" + "Got it! I've removed this task:");
                    System.out.println("\t\t" + deleted);
                    System.out.println("\t" + "Now, you have " + taskList.size() + " tasks in the list.");
                }
                catch (Exception e) {
                    System.out.println("\t\t" + "Please specify which task you wish to delete.");
                }
                break;

            case "bye":
                isActiveMode = false;
                ListStateSaver.saveListState(FILEPATH, taskList);
                System.out.println("\t" + "Adios. It was my pleasure assisting you. Keep smiling " + name + ".");
                break;

            default:
                try {
                    throw new DukeException("Invalid Command Format");
                }
                catch (DukeException e) {
                    System.out.println("\t" + e);
                    System.out.println("\t\t" + "I know I am dumb. Please write your command in a way that" +
                            " I can understand.");
                }
                break;
            }
        }
    }
}
