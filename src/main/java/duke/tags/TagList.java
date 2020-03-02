package duke.tags;

import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents the current list of tags.
 *
 * @author Firzan Armani
 */
public class TagList {
    private ArrayList<Tag> tagsList;

    /**
     * TagList constructor.
     *
     * @param taskList A TaskList containing all current tasks
     */
    public TagList(TaskList taskList) {
        this.tagsList = new ArrayList<Tag>();
        for (Task task: taskList.getTaskList()) {
            for (Tag tag: task.getTags()) {
                if (!tag.getTagName().equals(" ")) {
                    this.addTag(tag);
                }
            }
        }
    }

    /**
     * TagList constructor.
     */
    public TagList() {
        this.tagsList = new ArrayList<Tag>();
    }

    /**
     * Get the number of tags in the TagList.
     *
     * @return The number of tags
     */
    public int getSize() {
        return this.tagsList.size();
    }

    /**
     * Get an ArrayList object of all the tags in the Taglist.
     *
     * @return An ArrayList of Tag objects
     */
    public ArrayList<Tag> getAllTags() {
        return this.tagsList;
    }

    /**
     * Get the Tag at the specified index.
     *
     * @param i Index specified
     * @return Tag at the index
     */
    public Tag getTagAt(int i) {
        return this.tagsList.get(i);
    }

    /**
     * Add tag to the tagList if it currently doesn't exist.
     *
     * @param tag An input Tag
     * @return Tag on successful add
     */
    public Tag addTag(Tag tag) {
        if (!tagsList.contains(tag)) {
            tagsList.add(tag);
        }
        return tag;
    }

    /**
     * Remove tag from tagList if it exists.
     *
     * @param tag An input Tag
     * @return Tag on successful delete
     */
    public Tag removeTag(Tag tag) {
        if (tagsList.contains(tag)) {
            tagsList.remove(tag);
        }
        return tag;
    }
}