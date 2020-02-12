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
    private String firstPhrase;

    AddTaskCommand(String firstPhrase, Scanner sc) {
        super(sc);
        this.firstPhrase = firstPhrase;
    }

    @Override
    public boolean execute(TaskList tasks, UiText ui, Storage storage) {
        Task task = null;
        try {
            switch (this.firstPhrase) {
                case "todo":
                    task = new ToDoTask(this.terms.nextLine().trim());
                    break;
                case "deadline":
                    task = new DeadlineTask(this.terms.useDelimiter("/").next().trim(), this.terms.useDelimiter(" ").next().substring(1),
                            this.terms.nextLine());
                    break;
                case "event":
                    task = new EventTask(this.terms.useDelimiter("/").next().trim(), this.terms.useDelimiter(" ").next().substring(1),
                            this.terms.nextLine());
                    break;
                default:
            }
        } catch (Exception e) {
            ui.respond("☹ OOPS!!! The description of a " + firstPhrase + " cannot be empty.");
            return true;
        }
        if (task != null) {
            tasks.add(task);
            ui.respond("Got it. I've added this task:", "  " + task.toString(),
                    "Now you have " + tasks.count() + " tasks in the list.");
            storage.save(tasks.getTaskList());
            return true;
        } else {
            ui.respond(UiText.dunno);
            return false;
        }
    }
}

class ListCommand extends Command {
    ListCommand(Scanner sc) {
        super(sc);
    }

    @Override
    public boolean execute(TaskList tasks, UiText ui, Storage storage) {
        try {
            List<Task> lst = tasks.getTaskList();
            ui.start("Here are the tasks in your list:");
            ui.respondLine(lst.stream().map(x -> "" + (lst.indexOf(x) + 1) + "." + x).collect(Collectors.toList()));
            ui.over();
            return true;
        } catch (Exception e) {
            ui.respond(UiText.dunno);
            return false;
        }
    }
}

class FindCommand extends Command {
    FindCommand(Scanner sc) {
        super(sc);
    }

    @Override
    public boolean execute(TaskList tasks, UiText ui, Storage storage) {
        try {
            List<Task> lst = tasks.find(this.terms.next());
            ui.start("Here are the matching tasks in your list:");
            ui.respondLine(lst.stream().map(x -> "" + (lst.indexOf(x) + 1) + "." + x).collect(Collectors.toList()));
            ui.over();
            return true;
        } catch (Exception e) {
            ui.respond(UiText.dunno);
            return false;
        }
    }
}

class DeleteCommand extends Command {
    DeleteCommand(Scanner sc) {
        super(sc);
    }

    @Override
    public boolean execute(TaskList tasks, UiText ui, Storage storage) {
        try {
            int num = Integer.parseInt(this.terms.next()) - 1;
            ui.respond("Got it. I've removed this task:", "  " + tasks.remove(num).toString(),
                    "Now you have " + tasks.count() + " tasks in the list.");
            storage.save(tasks.getTaskList());
            return true;
        } catch (Exception e) {
            ui.respond(UiText.dunno);
            return false;
        }
    }
}

class MarkAsDoneCommand extends Command {
    MarkAsDoneCommand(Scanner sc) {
        super(sc);
    }

    @Override
    public boolean execute(TaskList tasks, UiText ui, Storage storage) {
        try {
            int num = Integer.parseInt(this.terms.next()) - 1;
            tasks.get(num).setToDone();
            ui.respond(UiText.taskDoneNote, tasks.get(num).toString());
            storage.save(tasks.getTaskList());
            return true;
        } catch (Exception e) {
            ui.respond(UiText.dunno);
            return false;
        }
    }
}

class ExitCommand extends Command {
    ExitCommand(Scanner sc) {
        super(sc);
    }

    @Override
    public boolean execute(TaskList tasks, UiText ui, Storage storage) {
        try {
            ui.respond(UiText.bye);
            System.exit(0);
            return true;
        } catch (Exception e) {
            ui.respond(UiText.dunno);
            return false;
        }
    }
}