package duke.parser;

import duke.task.TaskList;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;
import duke.storage.Storage;
import duke.ui.UiText;

import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;

public class Command {
    Scanner terms;

    Command(Scanner sc) {
        this.terms = sc;
    }

    /**
     * execute the command with the given elements
     *
     * @param tasks   = tasklist
     * @param ui      = ui to respond to user
     * @param storage = to store data to local file
     * @return task successfully executed?
     */
    public boolean execute(TaskList tasks, UiText ui, Storage storage) {
        ui.respond(UiText.dunno);
        return false;
    }
}

class AddTaskCommand extends Command {
    private String first;

    public AddTaskCommand(String first, Scanner sc) {
        super(sc);
        this.first = first;
    }

    @Override
    public boolean execute(TaskList tasks, UiText ui, Storage storage) {
        Task t = null;
        try {
            switch (this.first) {
                case "todo":
                    t = new ToDoTask(this.terms.nextLine().trim());
                    break;
                case "deadline":
                    t = new DeadlineTask(this.terms.useDelimiter("/").next().trim(), this.terms.useDelimiter(" ").next().substring(1),
                            this.terms.nextLine());
                    break;
                case "event":
                    t = new EventTask(this.terms.useDelimiter("/").next().trim(), this.terms.useDelimiter(" ").next().substring(1),
                            this.terms.nextLine());
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
}

class ListCommand extends Command {
    public ListCommand(Scanner sc) {
        super(sc);
    }

    @Override
    public boolean execute(TaskList tasks, UiText ui, Storage storage) {
        List<Task> lst = tasks.getTaskList();
        ui.start("Here are the tasks in your list:");
        ui.respondLine(lst.stream().map(x -> "" + (lst.indexOf(x) + 1) + "." + x).collect(Collectors.toList()));
        ui.over();
        return true;
    }
}

class FindCommand extends Command {
    public FindCommand(Scanner sc) {
        super(sc);
    }

    @Override
    public boolean execute(TaskList tasks, UiText ui, Storage storage) {
        List<Task> lst = tasks.find(this.terms.next());
        ui.start("Here are the matching tasks in your list:");
        ui.respondLine(lst.stream().map(x -> "" + (lst.indexOf(x) + 1) + "." + x).collect(Collectors.toList()));
        ui.over();
        return true;
    }
}

class DeleteCommand extends Command {
    public DeleteCommand(Scanner sc) {
        super(sc);
    }

    @Override
    public boolean execute(TaskList tasks, UiText ui, Storage storage) {
        int num = Integer.parseInt(this.terms.next()) - 1;
        ui.respond("Got it. I've removed this task:", "  " + tasks.remove(num).toString(),
                "Now you have " + tasks.count() + " tasks in the list.");
        storage.save(tasks.getTaskList());
        return true;
    }
}

class MarkAsDoneCommand extends Command {
    public MarkAsDoneCommand(Scanner sc) {
        super(sc);
    }

    @Override
    public boolean execute(TaskList tasks, UiText ui, Storage storage) {
        int num = Integer.parseInt(this.terms.next()) - 1;
        tasks.get(num).setToDone();
        ui.respond(UiText.taskDoneNote, tasks.get(num).toString());
        storage.save(tasks.getTaskList());
        return true;
    }
}

class ExitCommand extends Command {
    public ExitCommand(Scanner sc) {
        super(sc);
    }

    @Override
    public boolean execute(TaskList tasks, UiText ui, Storage storage) {
        ui.respond(UiText.bye);
        System.exit(0);
        return true;
    }
}