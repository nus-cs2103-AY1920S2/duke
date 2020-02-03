package duke.tool;

import duke.command.Deadlines;
import duke.command.Events;
import duke.command.Task;
import duke.command.ToDos;

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
        String outputString = "";
        switch (command) {
            case "bye":
                outputString += ui.print("Bye. Hope to see you again soon!");
                return outputString;
            case "list":
                ui.print("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    outputString += ui.print((i + 1) + "." + taskList.get(i));
                }
                return outputString;
            case "find":
                outputString += ui.print("Here are the matching tasks in your list:");
                int count = 0;
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).containsString(arguments)) {
                        count++;
                        outputString += ui.print(count + "." + taskList.get(i));
                    }
                }
                return outputString;
            case "done":
                try {
                    int num = Integer.parseInt(arguments);
                    if (taskList.size() == 0) {
                        outputString += ui.printError("=( OOPS!!! You have no tasks currently.");
                    } else if (num <= 0) {
                        outputString += ui.printError("=( OOPS!!! Task number cannot be equal to or less than 0");
                    } else if (num > taskList.size()) {
                        outputString += ui.printError(
                                "=( OOPS!!! The number of a done command cannot be greater than the number of tasks "
                                        + "you have.");
                    } else {
                        taskList.get(num - 1).markAsDone();
                        outputString += ui.print("Nice! I've marked this task as done:");
                        outputString += ui.print(taskList.get(num - 1).toString());
                    }
                } catch (NumberFormatException ex) {
                    // handle exception here
                    outputString += ui.printError("=( OOPS!!! Enter a valid number after done command");
                }
                return outputString;
            case "delete":
                try {
                    int num = Integer.parseInt(arguments);
                    if (taskList.size() == 0) {
                        outputString += ui.printError("=( OOPS!!! You have no tasks currently.");
                    } else if (num <= 0) {
                        outputString += ui.printError("=( OOPS!!! Task number cannot be equal to or less than 0");
                    } else if (num > taskList.size()) {
                        outputString += ui.printError(
                                "=( OOPS!!! The number of a delete command cannot be greater than the number of tasks "
                                        + "you have.");
                    } else {
                        Task removedTask = taskList.remove(num - 1);
                        outputString += ui.print("Noted. I've removed this task: ");
                        outputString += ui.print(removedTask.toString());
                        outputString += ui.print("Now you have " + taskList.size() + " tasks in the list.");
                    }
                } catch (NumberFormatException ex) {
                    // handle exception here
                    outputString += ui.printError("=( OOPS!!! Enter a valid number after done command");
                }
                return outputString;
            case "todo":
                Task newToDo = new ToDos(arguments);
                taskList.add(newToDo);
                outputString += ui.print("Got it. I've added this task:");
                outputString += ui.print(newToDo.toString());
                outputString += ui.print("Now you have " + taskList.size() + " tasks in the list.");
                return outputString;
            case "deadline":
                if (!arguments.contains("/by ")) {
                    outputString += ui.printError("=( OOPS!!! The /by of a deadline cannot be empty.");
                } else if (arguments.split(" ").length < 4) {
                    outputString += ui.printError(
                            "=( OOPS!!! Follow the format: deadline [activity] "
                                    + "/by [year]-[month]-[day] [hour]:[minute]");
                } else {
                    String description = arguments.split("/")[0].trim();
                    String datetime = arguments.split("/")[1].replaceFirst("by ", "");

                    String[] datetimeSplit = datetime.split(" ");
                    String date = datetimeSplit[0];
                    String time = datetimeSplit[1];

                    LocalDate dateParsed = LocalDate.parse(date);
                    LocalTime timeParsed = LocalTime.parse(time);
                    LocalDateTime datetimeParsed = timeParsed.atDate(dateParsed);

                    String newDateTime =
                            datetimeParsed.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mma"));
                    Task newDeadline = new Deadlines(description, newDateTime);
                    taskList.add(newDeadline);
                    outputString += ui.print("Got it. I've added this task:");
                    outputString += ui.print(newDeadline.toString());
                    outputString += ui.print("Now you have " + taskList.size() + " tasks in the list.");
                }
                return outputString;
            case "event":
                if (!arguments.contains("/at ")) {
                    outputString += ui.printError("=( OOPS!!! The /at of an event cannot be empty.");
                } else if (arguments.split(" ").length < 7) {
                    outputString += ui.printError(
                            "=( OOPS!!! Follow the format: event [activity] /at [year]-[month]-[day]"
                                    +  " [hour]:[minute] to [year]-[month]-[day] [hour]:[minute]");
                } else {
                    String description = arguments.split("/")[0].trim();
                    String datetime = arguments.split("/")[1].replaceFirst("at ", "");

                    String[] datetimeSplit = datetime.split(" ");
                    String date1 = datetimeSplit[0];
                    String time1 = datetimeSplit[1];
                    String date2 = datetimeSplit[3];
                    String time2 = datetimeSplit[4];

                    LocalDate dateParsed1 = LocalDate.parse(date1);
                    LocalTime timeParsed1 = LocalTime.parse(time1);
                    LocalDateTime datetimeParsed1 = timeParsed1.atDate(dateParsed1);
                    LocalDate dateParsed2 = LocalDate.parse(date2);
                    LocalTime timeParsed2 = LocalTime.parse(time2);
                    LocalDateTime datetimeParsed2 = timeParsed2.atDate(dateParsed2);

                    String newDateTime1 =
                            datetimeParsed1.format(
                                    DateTimeFormatter.ofPattern(
                                            "dd MMM yyyy HH:mma"));
                    String newDateTime2 =
                            datetimeParsed2.format(
                                    DateTimeFormatter.ofPattern(
                                            "dd MMM yyyy HH:mma"));

                    String newDateTime = newDateTime1 + " to " + newDateTime2;

                    Task newTask = new Events(description, newDateTime);
                    taskList.add(newTask);
                    outputString += ui.print("Got it. I've added this task:");
                    outputString += ui.print(newTask.toString());
                    outputString += ui.print("Now you have " + taskList.size() + " tasks in the list.");
                }
                return outputString;
            default:
                outputString += ui.printError("=( OOPS!!! I'm sorry, but I don't know what that means.");
                return outputString;
        }
    }
}
