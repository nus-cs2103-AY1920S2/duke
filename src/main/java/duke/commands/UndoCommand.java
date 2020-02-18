package duke.commands;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Events;
import duke.tasks.ToDos;
import duke.ui.Ui;

import java.io.IOException;

public class UndoCommand extends Command {

    public UndoCommand(String command) {
        super(command);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        String lastCommand = Parser.lastCommand;
        Parser.lastCommand = "undo";

        String arr[] = lastCommand.split(" ", 2);
        String firstWord = arr[0];

        if (command.equals("bye")) {
            return "undo not applicable";
        } else if (command.equals("list")) {
            return "undo not applicable";
        } else if (firstWord.equals("done")) {

            if (arr.length > 1) { //check for errors
                //which task done
                String secNum = arr[1];
                int doneTask = Integer.parseInt(secNum) - 1;

                //CALL TASKLIST: set that task to done
                tasks.undoDone(doneTask);
                //CALL STORAGE: write new file
                storage.undoUpdateTask(doneTask, tasks);
                //CALL UI: print output
                return ui.printUndoDone(tasks, doneTask);
            } else {
                throw new DukeException("☹ OOPS!!! Previous command had an error.");
            }

        } else if (firstWord.equals("delete")) {

            if (arr.length > 1) { //check for errors
                //which task to delete
                String delete = Parser.lastDeleted;
                String[] deletedTask = delete.split("@", 4);
                if (deletedTask[0].equals("T")) {
                    ToDos t = new ToDos(deletedTask[2]);
                    if (deletedTask[1].equals("1")) {
                        t.setDone();
                    }
                    tasks.getList().add(t);
                    storage.addTodo(tasks);
                } else if (deletedTask[0].equals("D")) {
                    Deadline d = new Deadline(deletedTask[2], deletedTask[3]);
                    if (deletedTask[1].equals("1")) {
                        d.setDone();
                    }
                    tasks.getList().add(d);
                    storage.addDeadline(tasks);
                } else if (deletedTask[0].equals("E")) {
                    Events e = new Events(deletedTask[2], deletedTask[3]);
                    if (deletedTask[1].equals("1")) {
                        e.setDone();
                    }
                    tasks.getList().add(e);
                    storage.addEvent(tasks);
                }
            } else {
                throw new DukeException("☹ OOPS!!! Previous command had an error.");
            }

            return "undo deleted";
        } else if (firstWord.equals("find")) {
            return "undo not applicable";
        } else if (firstWord.equals("todo")) {
            int lastTask = tasks.getList().size();
            return new DeleteCommand("delete " + lastTask).execute(tasks,ui, storage);
        } else if (firstWord.equals("deadline")) {
            int lastTask = tasks.getList().size();
            return new DeleteCommand("delete " + lastTask).execute(tasks,ui, storage);
        } else if (firstWord.equals("event")) {
            int lastTask = tasks.getList().size();
            return new DeleteCommand("delete " + lastTask).execute(tasks,ui, storage);
        } else if (firstWord.equals("undo")) {
            return "undo not applicable";
        } else {
            throw new DukeException("☹ OOPS!!! Previous command had an error.");
        }
    }
}
