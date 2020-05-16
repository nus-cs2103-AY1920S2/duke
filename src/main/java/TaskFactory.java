
import java.io.BufferedReader;
import java.io.IOException;

public class TaskFactory {
    /**
     * Read the header and decide which kind of task is it
     * @param reader
     * @return A task
     * @throws IOException if task type is wrong
     */

    public Task readTask(BufferedReader reader) throws IOException {
        String header = reader.readLine();
        assert (header.equals("{"));

        String taskType = reader.readLine();

        Task result;
        switch (taskType) {
        case "ToDo": {
            result = ToDo.readBuffer(reader);
            break;
        }
        case "Deadline": {
            result = Deadline.readBuffer(reader);
            break;
        }
        case "Event": {
            result = Event.readBuffer(reader);
            break;
        }
        default: {
            throw new IOException("Wrong task type");
        }
        }
        String wrapStr = reader.readLine();
        assert (wrapStr.equals("}"));
        return result;
    }
}
