import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {


    public Parser() {

    }

    /**
     * Parses the String user input from the Ui
     * Then checks the kind of Task: todo, event, deadline
     * and also the commands: list, bye, delete, done, find
     *
     * @param input string user input from Ui
     * @throws DukeException  If description is empty, or if the input is none of the commands
     * @throws IOException If file cannot be written to storage
     */

    public void parse(String input) {

        String[] taskDescription = input.split(" ");

        try {

            switch (taskDescription[0]) {



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

                    String indexString = taskDescription[1];
                    int index = Integer.parseInt(indexString);
                    if (index > TaskList.getSize()) {
                        throw new DukeException("There is no task " + index + "!!!");
                    }
                    Task oldTask = TaskList.getTask(index - 1);

                    switch (oldTask.getType()) {
                        case "todo": {

                            ToDo newTask = new ToDo(oldTask.getDescription());
                            newTask.setStatusDone();
                            TaskList.set(index - 1, newTask);
                            Storage.updateFile(TaskList.taskList);
                            System.out.println("I... I've marked this as done... notice me pls: \n" + newTask.toString());
                            break;
                        }
                        case "deadline": {

                            Deadline newTask = new Deadline(oldTask.getDescription(), oldTask.getBy());
                            newTask.setStatusDone();
                            TaskList.set(index - 1, newTask);
                            Storage.updateFile(TaskList.taskList);
                            System.out.println("I... I've marked this as done... notice me pls: \n" + newTask.toString());
                            break;
                        }
                        case "event": {

                            Event newTask = new Event(oldTask.getDescription(), oldTask.getBy());
                            newTask.setStatusDone();
                            TaskList.set(index - 1, newTask);
                            Storage.updateFile(TaskList.taskList);
                            System.out.println("I... I've marked this as done... notice me pls: \n" + newTask.toString());
                            break;
                        }
                    }

                    break;
                }



                // DELETE will delete the task in the ArrayList according to index number
                case "delete": {

                    String indexString = taskDescription[1];
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
                        String taskDescriptionString = "";
                        for (int i = 1; i < taskDescription.length; i++) {
                            taskDescriptionString += " " + taskDescription[i];
                        }

                        if (taskDescriptionString.equals("")) {

                            throw new DukeException("☹ OWO!!!! Descriptions of TODOs cannot be empty!");
                        } else {
                            ToDo newTask = new ToDo(taskDescriptionString);
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

                    try {
                        String taskDescriptionString = "";
                        String deadlineString = "";
                        int indexOfSlash = 0;

                        // For loop to get the task descriptions until /by is reached
                        for (int i = 1; i < taskDescription.length; i++) {

                            if (taskDescription[i].equals("/by")) {
                                indexOfSlash += i;
                                break;
                            }
                            taskDescriptionString += " " + taskDescription[i];
                        }

                        if (taskDescriptionString.equals("")) {
                            throw new DukeException("☹ OWO!!!! Descriptions of Deadlines cannot be empty!");

                        } else {

                            // For loop to get the due date
                            for (int i = indexOfSlash + 1; i < taskDescription.length; i++) {

                                deadlineString += " " + taskDescription[i];
                            }

                            LocalDate deadline = LocalDate.parse(deadlineString.trim());
                            Deadline newDeadline = new Deadline(taskDescriptionString, deadline);
                            TaskList.addTask(newDeadline);
                            String taskMessage = "D | " + newDeadline.getStatus() + " |" + newDeadline.getDescription()
                                    + " | " + deadline + "\n";
                            Storage.writeToFile(taskMessage);
                            System.out.println("Senpai I have added this event: \n" + "[D][✗]" +
                                    newDeadline.getDescription() + " (by:" + deadlineString + ")" + "\n"
                                    + "Now you have " + TaskList.getSize() + " number of tasks in the list.");
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }



                // EVENT will add a new Event task to the list with a date and/or time attached to it
                case "event": {

                    try {

                        String taskDescriptionString = "";
                        String eventString = "";
                        int indexOfSlash = 0;

                        // For loop to get the task descriptions until /by is reached
                        for (int i = 1; i < taskDescription.length; i++) {

                            if (taskDescription[i].equals("/at")) {
                                indexOfSlash += i;
                                break;
                            }
                            taskDescriptionString += " " + taskDescription[i];
                        }

                        if (taskDescriptionString.equals("")) {
                            throw new DukeException("☹ OWO!!!! Descriptions of Events cannot be empty!");

                        } else {
                            // For loop to get the due date
                            for (int i = indexOfSlash + 1; i < taskDescription.length; i++) {

                                eventString += " " + taskDescription[i];
                            }

                            LocalDate eventTiming = LocalDate.parse(eventString.trim());
                            Event newEvent = new Event(taskDescriptionString, eventTiming);
                            TaskList.addTask(newEvent);
                            String taskMessage = "E | " + newEvent.getStatus() + " |" + newEvent.getDescription()
                                    + " | " + eventTiming + "\n";
                            Storage.writeToFile(taskMessage);
                            System.out.println("Senpai I have added this event: \n" + "[E][✗]" +
                                    newEvent.getDescription() + " (at:" + eventString + ")" + "\n"
                                    + "Now you have " + TaskList.getSize() + " number of tasks in the list.");

                        }

                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }

                case "find": {

                    ArrayList<Task> searchResults = new ArrayList<>();
                    int incrementer = 1;

                    for (Task task : TaskList.taskList) {

                        if (task.getDescription().contains(taskDescription[1])) {
                            searchResults.add(task);
                        }
                    }

                    System.out.println("Here are the matching tasks in your list:");

                    for (Task printingTask : searchResults) {
                        System.out.println(incrementer + "." + printingTask);
                        incrementer++;
                    }

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
