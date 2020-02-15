package com.duke.command;

import com.duke.tag.Tag;
import com.duke.tag.TagList;
import com.duke.task.TaskList;
import com.duke.util.DukeException;
import com.duke.util.Storage;
import com.duke.util.Ui;

import java.util.List;

public class FindTagCommand extends Command {
    public String tagName;

    public FindTagCommand(String tag) {
        this.tagName = tag;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, TagList tags) throws DukeException {
        Tag tag = tags.findTag(tagName);
        ui.showFindTagMessage(tag);
    }

    @Override
    public String executeOnGui(TaskList tasks, Ui ui, Storage storage, TagList tags) throws DukeException {
        Tag tag = tags.findTag(tagName);
        return ui.getFindTagMessage(tag);
    }


}
