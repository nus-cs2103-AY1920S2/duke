import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {


    public Parser() {

    }

    public void parse(String input) {

        String[] taskDesc = input.split(" ");

        try {

            switch (taskDesc[0]) {



                // BYE will terminate the program with a message
                case "bye":

                    Storage.updateFile(TaskList.taskList);
                    System.out.println("UwU gone so fast? You're a fat bitch anyway.");



                    break;



                // LIST will list out all the tasks and show their done status
                case "list":

                    System.out.println("UwU you got some nice tasks:");

                    for (int i = 0; i < TaskList.getSize(); i++) {
                        int indexNumber = i + 1;

                        if (TaskList.getTask(i).getStatus() == 0) {
                            System.out.println(indexNumber + "." + TaskList.getTask(i).toString());
                        } else {
                            System.out.println(indexNumber + "." + TaskList.getTask(i).toString());
                        }

                    }

                    break;



                // DONE will mark task as done with a ticked checkbox
                case "done": {

                    String indexString = taskDesc[1];
                    int index = Integer.parseInt(indexString);
                    if (index > TaskList.getSize()) {
                        throw new DukeException("There is no task " + index + "!!!");
                    }
                    Task oldTask = TaskList.getTask(index - 1);
                    if (oldTask.getType() == "todo") {

                        ToDo newTask = new ToDo(oldTask.getDescription());
                        newTask.setStatusDone();
                        TaskList.set(index - 1, newTask);
                        Storage.updateFile(TaskList.taskList);
                        System.out.println("I... I've marked this as done... notice me pls: \n" + newTask.toString());
                    } else if (oldTask.getType() == "deadline") {

                        Deadline newTask = new Deadline(oldTask.getDescription(), oldTask.getBy());
                        newTask.setStatusDone();
                        TaskList.set(index - 1, newTask);
                        Storage.updateFile(TaskList.taskList);
                        System.out.println("I... I've marked this as done... notice me pls: \n" + newTask.toString());
                    } else if (oldTask.getType() == "event") {

                        Event newTask = new Event(oldTask.getDescription(), oldTask.getBy());
                        newTask.setStatusDone();
                        TaskList.set(index - 1, newTask);
                        Storage.updateFile(TaskList.taskList);
                        System.out.println("I... I've marked this as done... notice me pls: \n" + newTask.toString());
                    }

                    break;
                }



                // DELETE will delete the task in the ArrayList according to index number
                case "delete": {

                    String indexString = taskDesc[1];
                    int index = Integer.parseInt(indexString);
                    Task oldTask = TaskList.getTask(index - 1);
                    TaskList.deleteTask(index - 1);
                    Storage.updateFile(TaskList.taskList);
                    System.out.println("Noted. I've removed this task: \n" + "  " + oldTask.toString() + "\n" +
                            "Now you have " + TaskList.getSize() + " tasks in the list.");

                    break;
                }



                // TODO will add a new todo task in the list, checkbox will automatically be [✗]
                case "todo":
                    try {
                        String taskDescString = "";
                        for (int i = 1; i < taskDesc.length; i++) {
                            taskDescString += " " + taskDesc[i];
                        }

                        if (taskDescString.equals("")) {

                            throw new DukeException("☹ OWO!!!! Descriptions of TODOs cannot be empty!");
                        } else {
                            ToDo newTask = new ToDo(taskDescString);
                            TaskList.addTask(newTask);
                            String taskMessage = "T | " + newTask.getStatus() + " |" + newTask.getDescription() + "\n";
                            Storage.writeToFile(taskMessage);
                            System.out.println("Senpai I have added this task: \n" + "[T][✗] " + newTask.getDescription() + "\n"
                                    + "Now you have " + TaskList.getSize() + " number of tasks in the list.");

                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;



                // DEADLINE will add a new deadline task in the list, with a date and/or time attached to it
                case "deadline": {

                    String taskDescString = "";
                    String deadlineString = "";
                    int indexOfSlash = 0;

                    // For loop to get the task descriptions until /by is reached
                    for (int i = 1; i < taskDesc.length; i++) {

                        if (taskDesc[i].equals("/by")) {
                            indexOfSlash += i;
                            break;
                        }
                        taskDescString += " " + taskDesc[i];
                    }

                    // For loop to get the due date
                    for (int i = indexOfSlash + 1; i < taskDesc.length; i++) {

                        deadlineString += " " + taskDesc[i];
                    }

                    LocalDate deadline = LocalDate.parse(deadlineString.trim());
                    Deadline newDeadline = new Deadline(taskDescString, deadline);
                    TaskList.addTask(newDeadline);
                    String taskMessage = "D | " + newDeadline.getStatus() + " |" + newDeadline.getDescription()
                            + " | " + deadline + "\n";
                    Storage.writeToFile(taskMessage);
                    System.out.println("Senpai I have added this event: \n" + "[D][✗]" +
                            newDeadline.getDescription() + " (by:" + deadlineString + ")" + "\n"
                            + "Now you have " + TaskList.getSize() + " number of tasks in the list.");

                    break;
                }



                // EVENT will add a new Event task to the list with a date and/or time attached to it
                case "event": {

                    String taskDescString = "";
                    String eventString = "";
                    int indexOfSlash = 0;

                    // For loop to get the task descriptions until /by is reached
                    for (int i = 1; i < taskDesc.length; i++) {

                        if (taskDesc[i].equals("/at")) {
                            indexOfSlash += i;
                            break;
                        }
                        taskDescString += " " + taskDesc[i];
                    }

                    // For loop to get the due date
                    for (int i = indexOfSlash + 1; i < taskDesc.length; i++) {

                        eventString += " " + taskDesc[i];
                    }

                    LocalDate eventTiming = LocalDate.parse(eventString.trim());
                    Event newEvent = new Event(taskDescString, eventTiming);
                    TaskList.addTask(newEvent);
                    String taskMessage = "E | " + newEvent.getStatus() + " |" + newEvent.getDescription()
                            + " | " + eventTiming + "\n";
                    Storage.writeToFile(taskMessage);
                    System.out.println("Senpai I have added this event: \n" + "[E][✗]" +
                            newEvent.getDescription() + " (at:" + eventString + ")" + "\n"
                            + "Now you have " + TaskList.getSize() + " number of tasks in the list.");

                    break;
                }
                default:

                    throw new DukeException("☹ OWO!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        catch (DukeException | IOException ex) {

            System.out.println(ex.getMessage());
        }
    }


}
