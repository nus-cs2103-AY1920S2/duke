package duke.tags;

public class Tag {
    private String tagName;

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return this.tagName;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Tag) obj).tagName == this.tagName;
    }

    @Override
    public String toString() {
        return this.tagName;
    }
}