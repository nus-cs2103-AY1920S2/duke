package duke.tool;

import duke.command.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains the full command string of the user.
 */
public class Command {
    private String command;
    private String arguments;

    public Command(String command) {
        this.command = command;
    }

    public Command(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    /**
     * Executes commands such as: bye, list, done, find, delete, todo, deadline, event.
     *
     * @param taskList ArrayList of String storing the user tasks
     * @param ui       UI deals with interactions with the user
     * @return Boolean describing if a bye command was executed
     */
    public String execute(TaskList taskList, UI ui) {
        StringBuilder outputString = new StringBuilder();
        assert command != null : "Command should not be null";
        assert taskList != null : "taskList should not be null";
        assert ui != null : "ui should not be null";

        switch (command) {
            case "bye":
                outputString.append(ui.print("Bye. Hope to see you again soon!"));
                return outputString.toString();
            case "list":
                outputString.append(ui.print("Here are the tasks in your list:"));
                for (int i = 0; i < taskList.size(); i++) {
                    outputString.append(ui.print((i + 1) + "." + taskList.get(i)));
                }
                return outputString.toString();
            case "sort":
                if (!arguments.equals("description") && !arguments.equals("datetime")){
                    // handle exception here
                    outputString.append(ui.printError("=( OOPS!!! Enter 'sort description' or 'sort datetime'."));
                } else if (arguments.equals("description")) {
                    taskList.sort(new TaskDescriptionComparator());
                    outputString.append(ui.print("Successfully sorted your tasks list according to description. Here are the tasks in your list:"));
                    for (int i = 0; i < taskList.size(); i++) {
                        outputString.append(ui.print((i + 1) + "." + taskList.get(i)));
                    }
                } else {
                    taskList.sort(new TaskDatetimeComparator());
                    outputString.append(ui.print("Successfully sorted your tasks list according to datetime. Here are the tasks in your list:"));
                    for (int i = 0; i < taskList.size(); i++) {
                        outputString.append(ui.print((i + 1) + "." + taskList.get(i)));
                    }
                }
                return outputString.toString();
            case "find":
                outputString.append(ui.print("Here are the matching tasks in your list:"));
                int count = 0;
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).containsString(arguments)) {
                        count++;
                        outputString.append(ui.print(count + "." + taskList.get(i)));
                    }
                }
                return outputString.toString();
            case "done":
                try {
                    int num = Integer.parseInt(arguments);
                    if (taskList.size() == 0) {
                        outputString.append(ui.printError("=( OOPS!!! You have no tasks currently."));
                    } else if (num <= 0) {
                        outputString.append(ui.printError("=( OOPS!!! Task number cannot be equal to or less than 0"));
                    } else if (num > taskList.size()) {
                        outputString.append(ui.printError(
                                "=( OOPS!!! The number of a done command cannot be greater than the number of tasks "
                                        + "you have."));
                    } else {
                        taskList.get(num - 1).markAsDone();
                        outputString.append(ui.print("Nice! I've marked this task as done:"));
                        outputString.append(ui.print(taskList.get(num - 1).toString()));
                    }
                } catch (NumberFormatException ex) {
                    // handle exception here
                    outputString.append(ui.printError("=( OOPS!!! Enter a valid number after done command"));
                }
                return outputString.toString();
            case "delete":
                try {
                    int num = Integer.parseInt(arguments);
                    if (taskList.size() == 0) {
                        outputString.append(ui.printError("=( OOPS!!! You have no tasks currently."));
                    } else if (num <= 0) {
                        outputString.append(ui.printError("=( OOPS!!! Task number cannot be equal to or less than 0"));
                    } else if (num > taskList.size()) {
                        outputString.append(ui.printError(
                                "=( OOPS!!! The number of a delete command cannot be greater than the number of tasks "
                                        + "you have."));
                    } else {
                        Task removedTask = taskList.remove(num - 1);
                        outputString.append(ui.print("Noted. I've removed this task: "));
                        outputString.append(ui.print(removedTask.toString()));
                        outputString.append(ui.print("Now you have " + taskList.size() + " tasks in the list."));
                    }
                } catch (NumberFormatException ex) {
                    // handle exception here
                    outputString.append(ui.printError("=( OOPS!!! Enter a valid number after done command"));
                }
                return outputString.toString();
            case "help":
                outputString.append(ui.print("List of available commands: todo, event, deadline, list, sort, find, done, delete, bye\n\n" +
                        "Detailed user guide is at: https://shengxue97.github.io/duke/"));
                return outputString.toString();
            case "todo":
                if (arguments.equals("todo")){
                    outputString.append(ui.printError("=( OOPS!!! A ToDo cannot be empty."));
                } else {
                    Task newToDo = new ToDos(arguments);
                    taskList.add(newToDo);
                    outputString.append(ui.print("Got it. I've added this task:"));
                    outputString.append(ui.print(newToDo.toString()));
                    outputString.append(ui.print("Now you have " + taskList.size() + " tasks in the list."));
                }
                return outputString.toString();
            case "deadline":
                try {
                    String description = arguments.split("/")[0].trim();
                    String datetime = arguments.split("/")[1].replaceFirst("by ", "");

                    String[] datetimeSplit = datetime.split(" ");
                    String date = datetimeSplit[0];
                    String time = datetimeSplit[1];

                    LocalDate dateParsed = LocalDate.parse(date);
                    LocalTime timeParsed = LocalTime.parse(time);
                    LocalDateTime datetimeParsed = timeParsed.atDate(dateParsed);

                    String newDateTime =
                            datetimeParsed.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + "HRS";
                    Task newDeadline = new Deadlines(description, newDateTime);
                    taskList.add(newDeadline);
                    outputString.append(ui.print("Got it. I've added this task:"));
                    outputString.append(ui.print(newDeadline.toString()));
                    outputString.append(ui.print("Now you have " + taskList.size() + " tasks in the list."));
                } catch (Exception e){
                    outputString.append(ui.printError(
                            "=( OOPS!!! Follow the format: deadline [activity] "
                                    + "/by [year]-[month]-[day] [hour]:[minute]"));
                }
                return outputString.toString();
            case "event":
                try {
                    String description = arguments.split("/")[0].trim();
                    String datetime = arguments.split("/")[1].replaceFirst("at ", "");

                    String[] datetimeSplit = datetime.split(" ");

                    String dateFrom = datetimeSplit[0];
                    String timeFrom = datetimeSplit[1];

                    String dateTo = datetimeSplit[3];
                    String timeTo = datetimeSplit[4];

                    LocalDate dateFromParsed = LocalDate.parse(dateFrom);
                    LocalTime timeFromParsed = LocalTime.parse(timeFrom);
                    LocalDateTime datetimeFromParsed = timeFromParsed.atDate(dateFromParsed);

                    LocalDate dateToParsed = LocalDate.parse(dateTo);
                    LocalTime timeToParsed = LocalTime.parse(timeTo);
                    LocalDateTime datetimeToParsed = timeToParsed.atDate(dateToParsed);

                    String dateTimeFrom =
                            datetimeFromParsed.format(
                                    DateTimeFormatter.ofPattern(
                                            "dd MMM yyyy HH:mm"));
                    String dateTimeTo =
                            datetimeToParsed.format(
                                    DateTimeFormatter.ofPattern(
                                            "dd MMM yyyy HH:mm"));

                    String newDateTime = dateTimeFrom + "HRS to " + dateTimeTo + "HRS";

                    Task newTask = new Events(description, newDateTime);
                    taskList.add(newTask);
                    outputString.append(ui.print("Got it. I've added this task:"));
                    outputString.append(ui.print(newTask.toString()));
                    outputString.append(ui.print("Now you have " + taskList.size() + " tasks in the list."));
                } catch (Exception e){
                    outputString.append(ui.printError(
                            "=( OOPS!!! Follow the format: event [activity] /at [year]-[month]-[day]"
                                    + " [hour]:[minute] to [year]-[month]-[day] [hour]:[minute]"));
                }
                return outputString.toString();
            default:
                outputString.append(ui.printError("=( OOPS!!! I'm sorry, but I don't know what that means."));
                return outputString.toString();
        }
    }
}
