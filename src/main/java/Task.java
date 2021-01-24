import java.util.ArrayList;
import java.util.List;

public abstract class Task {
    private List<String> tags = new ArrayList<>();

    abstract String getName();

    abstract void makeCompleted();

    abstract String writeFormat();

    void addTag(String tag) {
        tags.add(tag);
    }

    void clearTag() {
        tags.clear();
    }

    List<String> getTags() {
        return new ArrayList<>(tags);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (tags.size() > 0) {
            sb.append("Tags:");
            tags.forEach(x -> sb.append(" #").append(x));
        }
        return sb.toString();
    }
}
