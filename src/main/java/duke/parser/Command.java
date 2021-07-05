package duke.parser;

import duke.Duke;

import duke.DukeHistory;
import duke.task.*;
import duke.ui.UiText;


import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;

public class Command {
    Scanner terms;
    protected boolean isExecuted = false;
    protected final static String CMD_EXECUTED = "command ady executed";

    Command(Scanner sc) {
        this.terms = sc;
    }

    /**
     * execute the command with the given elements
     *
     * @param main = DukeMain object
     * @return task successfully executed?
     */
    public Duke execute(Duke main) throws CommandExecutionExeption {
        assert !this.isExecuted : Command.CMD_EXECUTED;
        this.isExecuted = true;
        main.ui.respond(UiText.dunno);
        return main;
    }
}

class AddTaskCommand extends Command {
    private String firstPhrase;
    private Task task = null;

    AddTaskCommand(String firstPhrase, Scanner sc) {
        super(sc);
        this.firstPhrase = firstPhrase;
    }

    @Override
    public Duke execute(Duke main) throws CommandExecutionExeption{
        Duke next = main.getCopy();
        TaskList tasks = next.tasks;
        UiText ui = next.ui;
        assert !this.isExecuted : Command.CMD_EXECUTED;
        this.isExecuted = true;
        try {
            switch (this.firstPhrase) {
                case "todo":
                    this.task = new ToDoTask(this.terms.nextLine().trim());
                    break;
                case "deadline":
                    this.task = new DeadlineTask(this.terms.useDelimiter("/").next().trim(), this.terms.useDelimiter(" ").next().substring(1),
                            this.terms.nextLine());
                    break;
                case "event":
                    this.task = new EventTask(this.terms.useDelimiter("/").next().trim(), this.terms.useDelimiter(" ").next().substring(1),
                            this.terms.nextLine());
                    break;
                default:
            }
        } catch (Exception e) {
            ui.respond("☹ OOPS!!! The description of a " + firstPhrase + " cannot be empty.");
            throw new CommandExecutionExeption();
        }
        if (this.task != null) {
            tasks.add(this.task);
            ui.respond("Got it. I've added this task:", "  " + this.task.toString(),
                    "Now you have " + tasks.count() + " tasks in the list.");
            DukeHistory.progress(main);
            return next;
        } else {
            ui.respond(UiText.dunno);
            throw new CommandExecutionExeption();
        }
    }
}

class ListCommand extends Command {
    ListCommand(Scanner sc) {
        super(sc);
    }

    @Override
    public Duke execute(Duke main) throws CommandExecutionExeption {
        TaskList tasks = main.tasks;
        UiText ui = main.ui;
        assert !this.isExecuted : Command.CMD_EXECUTED;
        this.isExecuted = true;
        try {
            List<Task> lst;
            if(this.terms.hasNextLine() && this.terms.nextLine().trim().equals("not done")) {
                lst = tasks.filter(Task::isDone);
            } else {
                lst = tasks.getTaskList();
            }
            ui.startRespond("Here are the tasks in your list:");
            ui.respondLine(lst.stream().map(x -> "" + (lst.indexOf(x) + 1) + "." + x).collect(Collectors.toList()));
            ui.over();
            return main;
        } catch (Exception e) {
            ui.respond(UiText.dunno);
            throw new CommandExecutionExeption();
        }
    }
}

class StatCommand extends Command {
    StatCommand(Scanner sc) {
        super(sc);
    }

    @Override
    public Duke execute(Duke main) {
        TaskList tasks = main.tasks;
        UiText ui = main.ui;
        ui.startRespond("total tasks due: ");
        ui.respondLine(Integer.toString((int) tasks.stream().filter(x -> !x.isDone()).count()));
        ui.over();
        return main;
    }
}

class FindCommand extends Command {
    FindCommand(Scanner sc) {
        super(sc);
    }

    @Override
    public Duke execute(Duke main) throws CommandExecutionExeption {
        TaskList tasks = main.tasks;
        UiText ui = main.ui;
        assert !this.isExecuted : Command.CMD_EXECUTED;
        this.isExecuted = true;
        try {
            List<Task> lst = tasks.find(this.terms.next());
            ui.startRespond("Here are the matching tasks in your list:");
            ui.respondLine(lst.stream().map(x -> "" + (lst.indexOf(x) + 1) + "." + x).collect(Collectors.toList()));
            ui.over();
            return main;
        } catch (Exception e) {
            ui.respond(UiText.dunno);
            throw new CommandExecutionExeption();
        }
    }
}

class DeleteCommand extends Command {
    private Task task = null;
    private int num = 0;

    DeleteCommand(Scanner sc) {
        super(sc);
    }

    @Override
    public Duke execute(Duke main) throws CommandExecutionExeption {
        Duke next = main.getCopy();
        TaskList tasks = next.tasks;
        UiText ui = next.ui;
        assert !this.isExecuted : Command.CMD_EXECUTED;
        this.isExecuted = true;
        try {
            this.num = Integer.parseInt(this.terms.next()) - 1;
            this.task = tasks.remove(this.num);
            ui.respond("Got it. I've removed this task:", "  " + this.task.toString(),
                    "Now you have " + tasks.count() + " tasks in the list.");
            DukeHistory.progress(main);
            return next;
        } catch (Exception e) {
            ui.respond(UiText.dunno);
            throw new CommandExecutionExeption();
        }
    }
}

class MarkAsDoneCommand extends Command {
    private Task task;

    MarkAsDoneCommand(Scanner sc) {
        super(sc);
    }

    @Override
    public Duke execute(Duke main) throws CommandExecutionExeption {
        Duke next = main.getCopy();
        TaskList tasks = next.tasks;
        UiText ui = next.ui;
        assert !this.isExecuted : Command.CMD_EXECUTED;
        this.isExecuted = true;
        try {
            int num = Integer.parseInt(this.terms.next()) - 1;
            this.task = tasks.get(num);
            this.task.setToDone();
            ui.respond(UiText.taskDoneNote, tasks.get(num).toString());
            return next;
        } catch (Exception e) {
            ui.respond(UiText.dunno);
            throw new CommandExecutionExeption();
        }
    }
}

class ExitCommand extends Command {
    ExitCommand(Scanner sc) {
        super(sc);
    }

    @Override
    public Duke execute(Duke main) throws CommandExecutionExeption {
        UiText ui = main.ui;
        try {
            ui.respond(UiText.bye);
            System.exit(0);
            return main;
        } catch (Exception e) {
            ui.respond(UiText.dunno);
            throw new CommandExecutionExeption();
        }
    }
}

class ReverseCommand extends Command {
    ReverseCommand(Scanner sc) {
        super(sc);
    }

    @Override
    public Duke execute(Duke main) throws CommandExecutionExeption {
        UiText ui = main.ui;
        try {
            if(DukeHistory.revert()) {
                ui.respond(UiText.revertSuccess);
            } else {
                ui.respond(UiText.revertError);
            }

            return DukeHistory.getCurrent();
        } catch (Exception e) {
            ui.respond(UiText.dunno);
            throw new CommandExecutionExeption();
        }
    }
}