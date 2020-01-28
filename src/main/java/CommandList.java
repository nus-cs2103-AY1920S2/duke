import java.util.HashMap;
import java.util.Map;

public enum CommandList {
    DEADLINE("D"),
    DELETE(""),
    DONE(""),
    EVENT("E"),
    LIST(""),
    TODO("T"),
    BYE("");

    private String abbreviation;
    private static final Map<String, CommandList> lookup = new HashMap<>();

    CommandList(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    static {
        for (CommandList cl : CommandList.values()) {
            lookup.put(cl.getAbbreviation(), cl);
        }
    }

    public static CommandList get(String abbreviation) {
        return lookup.get(abbreviation);
    }
}
