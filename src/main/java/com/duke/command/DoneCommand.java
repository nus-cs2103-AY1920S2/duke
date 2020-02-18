package com.duke.command;

import com.duke.tag.TagList;
import com.duke.task.Task;
import com.duke.task.TaskList;
import com.duke.util.DukeException;
import com.duke.util.Storage;
import com.duke.util.Ui;

import java.io.IOException;

/**
 * Represents a command of marks a task as done.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * creates a command that marks the task with given index as done.
     *
     * @param index the index of the task to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, TagList tags) throws DukeException {
        try {
            Task t = tasks.markTask(index);
            storage.save(tasks);
            ui.showDone(t);
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Data Save Failed");
        }
    }

    @Override
    public String executeOnGui(TaskList tasks, Ui ui, Storage storage, TagList tags) throws DukeException {
        try {
            if (index >= tasks.tasks.size()) {
                throw new DukeException("OOPS!!! Invalid index!");
            }
            Task t = tasks.markTask(index);
            storage.save(tasks);
            return ui.getDoneMessage(t);
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Data Save Failed");
        }
    }
}
