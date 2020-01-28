package tool;

import command.Deadlines;
import command.Events;
import command.Task;
import command.ToDos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Command {
    private String command;
    private String arguments;

    public Command(String command){
        this.command = command;
    }

    public Command(String command, String arguments){
        this.command = command;
        this.arguments = arguments;
    }

    public boolean execute(TaskList taskList, UI ui) {
        switch (command) {
            case "bye":
                ui.print("Bye. Hope to see you again soon!");
                return true;
            case "list":
                ui.print("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    ui.print(String.valueOf(i + 1) + "." + taskList.get(i));
                }
                return false;
            case "find":
                ui.print("Here are the matching tasks in your list:");
                int count = 0;
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).containsString(arguments)){
                        count++;
                        ui.print(String.valueOf(count) + "." + taskList.get(i));
                    }
                }
                return false;
            case "done":
                try {
                    int num = Integer.parseInt(arguments);
                    if (taskList.size() == 0) {
                        ui.printError("☹ OOPS!!! You have no tasks currently.");
                    } else if (num <= 0) {
                        ui.printError("☹ OOPS!!! Task number cannot be equal to or less than 0");
                    } else if (num > taskList.size()) {
                        ui.printError("☹ OOPS!!! The number of a done command cannot be greater than the number of tasks " +
                                "you have.");
                    } else {
                        taskList.get(num - 1).markAsDone();
                        ui.print("Nice! I've marked this task as done:");
                        ui.print(taskList.get(num - 1).toString());
                    }
                } catch (NumberFormatException ex) {
                    //handle exception here
                    ui.printError("☹ OOPS!!! Enter a valid number after done command");
                }
                return false;
            case "delete":
                try {
                    int num = Integer.parseInt(arguments);
                    if (taskList.size() == 0) {
                        ui.printError("☹ OOPS!!! You have no tasks currently.");
                    } else if (num <= 0) {
                        ui.printError("☹ OOPS!!! Task number cannot be equal to or less than 0");
                    } else if (num > taskList.size()) {
                        ui.printError("☹ OOPS!!! The number of a delete command cannot be greater than the number of tasks " +
                                "you have.");
                    } else {
                        Task removedTask = taskList.remove(num - 1);
                        ui.print("Noted. I've removed this task: ");
                        ui.print(removedTask.toString());
                        ui.print("Now you have " + String.valueOf(taskList.size()) + " tasks in the list.");
                    }
                } catch (NumberFormatException ex) {
                    //handle exception here
                    ui.printError("☹ OOPS!!! Enter a valid number after done command");
                }
                return false;
            case "todo":
                Task newToDo = new ToDos(arguments);
                taskList.add(newToDo);
                ui.print("Got it. I've added this task:");
                ui.print(newToDo.toString());
                ui.print("Now you have " + String.valueOf(taskList.size()) + " tasks in the list.");
                return false;
            case "deadline":
                if (!arguments.contains("/by ")) {
                    ui.printError("☹ OOPS!!! The /by of a deadline cannot be empty.");
                } else if (arguments.split(" ").length < 4) {
                    ui.printError("☹ OOPS!!! Follow the format: deadline [activity] /by [year]-[month]-[day] [hour]:[minute]");
                } else {
                    String description = arguments.split("/")[0].trim();
                    String datetime = arguments.split("/")[1].replaceFirst("by ", "");

                    String[] datetimeSplit = datetime.split(" ");
                    String date = datetimeSplit[0];
                    String time = datetimeSplit[1];

                    LocalDate dateParsed = LocalDate.parse(date);
                    LocalTime timeParsed = LocalTime.parse(time);
                    LocalDateTime datetimeParsed = timeParsed.atDate(dateParsed);

                    String newDateTime = datetimeParsed.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mma"));
                    Task newDeadline = new Deadlines(description, newDateTime);
                    taskList.add(newDeadline);
                    ui.print("Got it. I've added this task:");
                    ui.print(newDeadline.toString());
                    ui.print("Now you have " + String.valueOf(taskList.size()) + " tasks in the list.");
                }
                return false;
            case "event":
                if (!arguments.contains("/at ")) {
                    ui.printError("☹ OOPS!!! The /at of an event cannot be empty.");
                } else if (arguments.split(" ").length < 7) {
                    ui.printError("☹ OOPS!!! Follow the format: event [activity] /at [year]-[month]-[day] [hour]:[minute] to [year]-[month]-[day] [hour]:[minute]");
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

                    String newDateTime1 = datetimeParsed1.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mma"));
                    String newDateTime2 = datetimeParsed2.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mma"));

                    String newDateTime = newDateTime1 + " to " + newDateTime2;

                    Task newTask = new Events(description, newDateTime);
                    taskList.add(newTask);
                    ui.print("Got it. I've added this task:");
                    ui.print(newTask.toString());
                    ui.print("Now you have " + String.valueOf(taskList.size()) + " tasks in the list.");
                }
                return false;
            default:
                ui.printError("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                return false;

        }
    }

}
