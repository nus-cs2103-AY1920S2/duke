package duke.parser;

import duke.task.TaskList;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;
import duke.ui.Ui;
import duke.storage.Storage;

import java.util.Scanner;

import java.util.List;

import java.util.stream.Collectors;

public class Command {
    Scanner terms;

    Command(Scanner sc) {
        this.terms = sc;
    }

    static boolean addTask(String first, Scanner terms, TaskList tasks, Ui ui, Storage storage) {
        Task t = null;
        try {
            switch (first) {
            case "todo":
                t = new ToDoTask(terms.nextLine().trim());
                break;
            case "deadline":
                t = new DeadlineTask(terms.useDelimiter("/").next().trim(), terms.useDelimiter(" ").next().substring(1),
                        terms.nextLine());
                break;
            case "event":
                t = new EventTask(terms.useDelimiter("/").next().trim(), terms.useDelimiter(" ").next().substring(1),
                        terms.nextLine());
                break;
            default:
            }
        } catch (Exception e) {
            ui.respond("☹ OOPS!!! The description of a " + first + " cannot be empty.");
            return true;
        }
        if (t != null) {
            tasks.add(t);
            ui.respond("Got it. I've added this task:", "  " + t.toString(),
                    "Now you have " + tasks.count() + " tasks in the list.");
            storage.save(tasks.getTaskList());
            return true;
        } else {
            return false;
        }
    }

    static void listTask(TaskList tasks, Ui ui) {
        List<Task> lst = tasks.getTaskList();
        ui.start("Here are the tasks in your list:");
        ui.respondLine(lst.stream().map(x -> "" + (lst.indexOf(x) + 1) + "." + x).collect(Collectors.toList()));
        ui.over();
    }

    static void markAsDone(Scanner terms, TaskList tasks, Ui ui) {
        int num = Integer.parseInt(terms.next()) - 1;
        tasks.get(num).setToDone();
        ui.respond(Ui.taskDoneNote, tasks.get(num).toString());
    }

    static void remove(Scanner terms, TaskList tasks, Ui ui) {
        int num = Integer.parseInt(terms.next()) - 1;
        ui.respond("Got it. I've removed this task:", "  " + tasks.remove(num).toString(),
                    "Now you have " + tasks.count() + " tasks in the list.");
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        String first = this.terms.next();

        try {
            if (!addTask(first, this.terms, tasks, ui, storage)) {
                switch (first) {
                case "exit":
                    ui.respond(Ui.bye);
                    System.exit(0);
                    break;
                case "list":
                    listTask(tasks, ui);
                    break;
                case "done":
                    markAsDone(this.terms, tasks, ui);
                    storage.save(tasks.getTaskList());
                    break;
                case "delete":
                    remove(this.terms, tasks, ui);
                    storage.save(tasks.getTaskList());
                    break;
                default:
                    ui.respond(Ui.dunno);
                    return false;
                }
            }
        } catch (Exception e) {
            ui.respond(Ui.dunno);
            return false;
        }
        return true;

    }
}