package com.duke.tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tags tracked by the current session of duke.
 */
public class TagList {
    private List<Tag> tags = new ArrayList<>();

    /**
     * Gets the list of tags contained in the list.
     * @return A List representation of the tags.
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * Adds a tag into the tag list.
     * @param tag The String name of the tag to be added into the list.
     * @return The Tag object that is added/generated and added.
     */
    public Tag addTag(String tag) {
        for (Tag t:tags) {
            if (t.tagName.equals(tag)) {
                return t;
            }
        }
        Tag output = new Tag(tag);
        tags.add(output);
        return output;
    }

    /**
     * Finds the Tag object that has the name of the input string.
     * @param tag The name of tag queried.
     * @return The Tag object in the list that has the given name. If none exists, null is returned.
     */
    public Tag findTag(String tag) {
        for (Tag t:tags) {
            if (t.tagName.equals(tag)) {
                return t;
            }
        }
        return null;
    }
}
