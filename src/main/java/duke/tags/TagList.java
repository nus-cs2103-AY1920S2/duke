package duke.tags;

import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class TagList {
    private ArrayList<Tag> tagsList;

    public TagList(TaskList taskList) {
        this.tagsList = new ArrayList<Tag>();
        for (Task task: taskList.getTaskList()) {
            for (Tag tag: task.getTags()) {
                tagsList.add(tag);
            }
        }
    }

    public TagList() {
        this.tagsList = new ArrayList<Tag>();
    }

    public Tag addTag(Tag tag) {
        if (!tagsList.contains(tag)) {
            tagsList.add(tag);
        }
        return tag;
    }

    public Tag removeTag(Tag tag) {
        if (tagsList.contains(tag)) {
            tagsList.remove(tag);
        }
        return tag;
    }
}