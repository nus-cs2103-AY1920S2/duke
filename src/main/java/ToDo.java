
import java.io.BufferedReader;
import java.io.IOException;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String getDisplayName() {
        return getName();
    }

    @Override
    public String getType() {
        return "T";
    }

    public String getString() {
        return "{\n"
                + "ToDo\n"
                + getName() + "\n"
                + hasDone() + "\n"
                + "}";
    }

    public static ToDo readBuffer(BufferedReader reader) throws IOException {
        String name = reader.readLine();
        String isDone = reader.readLine();
        return new ToDo(name, Boolean.valueOf(isDone));
    }
}
