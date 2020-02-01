import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.Optional;
import java.util.StringTokenizer;

public class Parser {
    public static HashMap<String, Message> availableMessage = new HashMap<>() {{
        put("done", Message.DONE);
        put("delete", Message.DELETE);
        put("todo", Message.TODO);
        put("deadline", Message.DEADLINE);
        put("event", Message.EVENT);
    }};

    //The specific way to split string (exclusive for deadline and event")
    public String[] stringSplitting(StringTokenizer st) {
        String temp = st.nextToken("/").substring(1);
        String description = temp.substring(0, temp.length() - 1);
        String byOrAt = st.nextToken("/").substring(3);
        return new String[]{description, byOrAt};
    }

    //To check whether the input for description is empty or not
    public void checkDescription(String str, int i) throws EmptyDescriptionException {
        if (str.length() <= i + 1) {
            throw new EmptyDescriptionException("OOPS!!! The description of a task cannot be empty.");
        }
    }

    //get the corresponding Message indicated from the user input String
    public Message getMessage(String str) throws InvalidKeyException {
        return Optional.ofNullable(Parser.availableMessage.get(str))
                .orElseThrow(() -> new InvalidKeyException("OOPS!!! I'm sorry, but I don't know what that means :-("));
    }

    //to check whether the number input is valid
    public boolean inRange(int num, int index) {
        return num > 0 && num <= index;
    }
}
