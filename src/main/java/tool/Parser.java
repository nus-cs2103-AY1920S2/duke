package tool;

import command.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Parser {
    private UI ui;
    private TaskList taskList;

    public Parser(UI ui, TaskList taskList){
        this.ui = ui;
        this.taskList = taskList;
    }

    public void parseBye(){
        ui.printLine();
        ui.print("Bye. Hope to see you again soon!");
        ui.printLine();
    }

    public void parseList(){
        ui.printLine();
        ui.print("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++){
            ui.print(String.valueOf(i + 1) + "." + taskList.get(i));
        }
        ui.printLine();
    }

    public void parseDone(String input){
        if (input.split(" ").length == 1){
            ui.printError("☹ OOPS!!! The number of a done command cannot be empty.");
        } else {
            try{
                int num = Integer.parseInt(input.split(" ")[1]);
                if (taskList.size() == 0){
                    ui.printError("☹ OOPS!!! You have no tasks currently.");
                }  else if (num  <= 0){
                    ui.printError("☹ OOPS!!! Task number cannot be equal to or less than 0");
                } else if (num > taskList.size()){
                    ui.printError("☹ OOPS!!! The number of a done command cannot be greater than the number of tasks " +
                            "you have.");
                } else {
                    taskList.get(num - 1).markAsDone();
                    ui.printLine();
                    ui.print("Nice! I've marked this task as done: ");
                    ui.print(taskList.get(num - 1).toString());
                    ui.printLine();
                }
            }catch (NumberFormatException ex) {
                //handle exception here
                ui.printError("☹ OOPS!!! Enter a valid number after done command");
            }
        }
    }

    public void parseDelete(String input){
        if (input.split(" ").length == 1){
            ui.printError("☹ OOPS!!! The number of a delete command cannot be empty.");
        } else {
            try{
                int num = Integer.parseInt(input.split(" ")[1]);
                if (taskList.size() == 0){
                    ui.printError("☹ OOPS!!! You have no tasks currently.");
                }  else if (num  <= 0){
                    ui.printError("☹ OOPS!!! Task number cannot be equal to or less than 0");
                } else if (num > taskList.size()){
                    ui.printError("☹ OOPS!!! The number of a delete command cannot be greater than the number of tasks " +
                            "you have.");
                } else {
                    Task removedTask = taskList.remove(num - 1);
                    ui.printLine();
                    ui.print("Noted. I've removed this task: ");
                    ui.print(removedTask.toString());
                    ui.print("Now you have " + String.valueOf(taskList.size()) + " tasks in the list.");
                    ui.printLine();
                }
            }catch (NumberFormatException ex) {
                //handle exception here
                ui.printError("☹ OOPS!!! Enter a valid number after done command");
            }
        }
    }

    public void parseToDos(String input){
        if (input.split(" ").length == 1){
            ui.printError("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            String description = input.replaceFirst("todo ", "");
            Task newTask = new ToDos(description);
            taskList.add(newTask);
            ui.printLine();
            ui.print("Got it. I've added this task:");
            ui.print(newTask.toString());
            ui.print("Now you have " + String.valueOf(taskList.size()) + " tasks in the list.");
            ui.printLine();
        }
    }

    public void parseDeadlines(String input){
        if (input.split(" ").length == 1){
            ui.printError("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            String description = input.replaceFirst("deadline ", "").split("/")[0].trim();
            if (!input.contains("/by ")) {
                ui.printError("☹ OOPS!!! The /by of a deadline cannot be empty.");
            } else if (input.split(" ").length < 5) {
                ui.printError("☹ OOPS!!! Follow the format: deadline [activity] /by [year]-[month]-[day] [hour]:[minute]");
            } else {
                String datetime = input.replaceFirst("deadline ", "").split("/")[1].replaceFirst("by ", "");

                String[] datetimeSplit = datetime.split(" ");
                String date = datetimeSplit[0];
                String time = datetimeSplit[1];

                LocalDate dateParsed = LocalDate.parse(date);
                LocalTime timeParsed = LocalTime.parse(time);
                LocalDateTime datetimeParsed = timeParsed.atDate(dateParsed);

                String newDateTime = datetimeParsed.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mma"));
                Task newTask = new Deadlines(description, newDateTime);
                taskList.add(newTask);
                ui.printLine();
                ui.print("Got it. I've added this task:");
                ui.print(newTask.toString());
                ui.print("Now you have " + String.valueOf(taskList.size()) + " tasks in the list.");
                ui.printLine();
            }
        }
    }

    public void parseEvents(String input){
        if (input.split(" ").length == 1){
            ui.printError("☹ OOPS!!! The description of an event cannot be empty.");
        } else {
            if (!input.contains("/at ")) {
                ui.printError("☹ OOPS!!! The /at of an event cannot be empty.");
            } else if (input.split(" ").length < 8) {
                ui.printError("☹ OOPS!!! Follow the format: event [activity] /at [year]-[month]-[day] [hour]:[minute] to [year]-[month]-[day] [hour]:[minute]");
            } else {
                String description = input.replaceFirst("event ", "").split("/")[0].trim();
                String datetime = input.replaceFirst("event ", "").split("/")[1].replaceFirst("at ", "");

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
                ui.printLine();
                ui.print("Got it. I've added this task:");
                ui.print(newTask.toString());
                ui.print("Now you have " + String.valueOf(taskList.size()) + " tasks in the list.");
                ui.printLine();
            }
        }
    }


}
