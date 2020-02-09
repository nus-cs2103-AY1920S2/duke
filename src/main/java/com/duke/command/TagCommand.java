package com.duke.command;

import com.duke.tag.Tag;
import com.duke.tag.TagList;
import com.duke.task.Task;
import com.duke.task.TaskList;
import com.duke.util.DukeException;
import com.duke.util.Storage;
import com.duke.util.Ui;

import java.io.IOException;

public class TagCommand extends Command {
    String tagName;
    int indexOfTask;

    public TagCommand(String tag, int index) {
        this.tagName = tag;
        this.indexOfTask = index;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, TagList tags) throws DukeException {
        try {
            Tag tag = tags.addTag(tagName);
            tasks.tagTask(indexOfTask, tag);
            Task t = tasks.tasks.get(indexOfTask - 1);
            storage.save(tasks);
            ui.showTagTaskMessage(tag, t);
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Data Save Failed");
        }
    }

    @Override
    public String executeOnGui(TaskList tasks, Ui ui, Storage storage, TagList tags) throws DukeException {
        try {
            Tag tag = tags.addTag(tagName);
            tasks.tagTask(indexOfTask, tag);
            Task t = tasks.tasks.get(indexOfTask - 1);
            storage.save(tasks);
            return ui.getTagTaskMessage(tag, t);
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Data Save Failed");
        }
    }
}
