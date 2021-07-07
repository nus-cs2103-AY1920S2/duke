package duke.tags;

/**
 * Represents a Tag.
 *
 * @author Fizan Armani
 */
public class Tag {
    private String tagName;

    /**
     * Tag constructor.
     *
     * @param tagName Name of the tag to create
     */
    public Tag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Get the name of the tag.
     *
     * @return The name of the tag
     */
    public String getTagName() {
        return this.tagName;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Tag) obj).getTagName().equals(this.getTagName());
    }

    @Override
    public String toString() {
        return this.tagName;
    }
}