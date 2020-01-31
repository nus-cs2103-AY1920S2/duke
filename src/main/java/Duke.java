import entity.Deadline;
import entity.Event;
import entity.Task;
import entity.Todo;
import parser.DateTimeParser;

import java.text.ParseException;

import exception.DirectoryNotFoundException;
import exception.TaskStatusNotValidException;
import handler.FileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;

public class Duke {
    public static void main(String[] args) {
        Path path = Paths.get("src", "main", "java", "memory");
        List<Task> tasks = new ArrayList<>();
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(path, "TaskList.txt");
            tasks = fileHandler.loadTaskFromMemory();
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found!");
        } catch (DirectoryNotFoundException e) {
            System.out.println("Directory not found! No saved tasks found!");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("Here is a list of your saved tasks: ");
        System.out.println("____________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).printTask());
        }
        System.out.println("____________________________");
        System.out.println("What can I do for you?");
        System.out.println("____________________________");
        String command = sc.nextLine();
        DateTimeParser dateTimeParser = new DateTimeParser();

        while (!command.equals("bye")) {
                int breakPoint = command.indexOf('/');
                String[] nextLine = command.split("\\s+");
                if (nextLine[0].equals("list")) {
                    System.out.println("____________________________");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + "." + tasks.get(i).printTask());
                    }
                    System.out.println("____________________________");
                } else if (nextLine[0].equals("done")) {
                    int index = Integer.parseInt(nextLine[1]) - 1;
                    if (!tasks.get(index).isDone()) {
                        try {
                            tasks.get(index).markAsDone(true);
                            fileHandler.saveTasksToMemory(tasks);
                            System.out.println("____________________________");
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(index + 1 + "." + tasks.get(index).printTask());
                            System.out.println("____________________________");
                        } catch (IOException e) {
                            tasks.get(index).markAsDone(false);
                            System.out.println("Error saving changes!");
                        }
                    } else {
                        System.out.println("____________________________");
                        System.out.println("Task is already done!");
                        System.out.println("____________________________");
                    }

                } else if (nextLine[0].equals("delete")) {
                    int index = Integer.parseInt(nextLine[1]) - 1;
                    Task toBeDeleted = tasks.remove(index);
                    try {
                        fileHandler.saveTasksToMemory(tasks);
                        System.out.println("____________________________");
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + toBeDeleted.printTask());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________");
                    } catch (IOException e) {
                        tasks.add(index, toBeDeleted);
                        System.out.println("Error deleting task!");
                    }

                } else if (nextLine[0].equals("check")) {
                    //check all events and deadlines before/on date
                    try {
                        Date date = dateTimeParser.parseDate(nextLine[1]);
                        System.out.println("____________________________");
                        System.out.println("Here is a list of events and deadlines before: " + nextLine[1]);
                        for (int i = 0; i < tasks.size(); i++) {
                            if (tasks.get(i).isDue(date)) {
                                System.out.println(tasks.get(i).printTask());
                            }
                        }
                        System.out.println("____________________________");
                    } catch (ParseException e) {
                        System.out.println("____________________________");
                        System.out.println("Invalid date format! Please use the format yyyy-MM-dd!");
                        System.out.println("____________________________");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("____________________________");
                        System.out.println("No date provided!");
                        System.out.println("____________________________");
                    }
                } else {
                    if (nextLine[0].equals("todo")) {
                        try {
                            tasks.add(new Todo(command.substring(5)));
                            fileHandler.saveTasksToMemory(tasks);
                            System.out.println("____________________________");
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + tasks.get(tasks.size() - 1).printTask());
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                            System.out.println("____________________________");
                        } catch (IOException e) {
                            tasks.remove(tasks.size() - 1);
                            System.out.println("Error saving task!");
                        }
                    } else if (nextLine[0].equals("event")) {
                        if (breakPoint != -1) {
                            try {
                                tasks.add(new Event(command.substring(6, breakPoint - 1), command.substring(breakPoint + 4)));
                                fileHandler.saveTasksToMemory(tasks);
                                System.out.println("____________________________");
                                System.out.println("Got it. I've added this task:");
                                System.out.println("  " + tasks.get(tasks.size() - 1).printTask());
                                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                                System.out.println("____________________________");
                            } catch (IOException e) {
                                tasks.remove(tasks.size() - 1);
                                System.out.println("Error saving task!");
                            }
                        } else {
                            System.out.println("____________________________");
                            System.out.println("☹ OOPS!!! Information about the event is missing");
                            System.out.println("____________________________");
                        }
                    } else if (nextLine[0].equals("deadline")) {
                        if (breakPoint != -1) {
                            try {
                                tasks.add(new Deadline(command.substring(9, breakPoint - 1), command.substring(breakPoint + 4)));
                                fileHandler.saveTasksToMemory(tasks);
                                System.out.println("____________________________");
                                System.out.println("Got it. I've added this task:");
                                System.out.println("  " + tasks.get(tasks.size() - 1).printTask());
                                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                                System.out.println("____________________________");
                            } catch (IOException e) {
                                tasks.remove(tasks.size() - 1);
                                System.out.println("Error saving task!");
                            }
                        } else {
                            System.out.println("____________________________");
                            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                            System.out.println("____________________________");
                        }
                    }
                }
            command = sc.nextLine();
        }
        System.out.println("____________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________");
    }
}