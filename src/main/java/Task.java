import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class Task {
    boolean isDone = false;
    HashSet<String> wordsInDescription = new HashSet<>();
    List<Tag> tags = new ArrayList<>();

    String tick = "[" + (char) 10003 + "]";
    String cross = "[" + (char) 10060 + "]";


    abstract public String toString();

    public void setDone() {
        isDone = true;
    }

    abstract public String formatToStore();

    public Tag addTag(String tagContent){
        Tag myTag = new Tag(tagContent);
        this.tags.add(myTag);
        return myTag;
    }

    public List<Tag> getTags(){
        return this.tags;
    }

    boolean contains(String keyword) {
        return wordsInDescription.contains(keyword);
    }

}
