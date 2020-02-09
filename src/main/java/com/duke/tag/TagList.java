package com.duke.tag;

import java.util.ArrayList;
import java.util.List;

public class TagList {
    private List<Tag> tags = new ArrayList<>();

    public List<Tag> getTags() {
        return tags;
    }

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

    public Tag findTag(String tag) {
        for (Tag t:tags) {
            if (t.tagName.equals(tag)) {
                return t;
            }
        }
        return null;
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }
}
