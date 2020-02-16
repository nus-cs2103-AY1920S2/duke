/**
 * Duke object that serves as an association between the Ui, Storage and TaskList
 */

package umaikaze.duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke{
    Storage st;
    TaskList tl;

    public Duke() throws IOException, DukeException {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir") + "\n");
        st = new Storage("./data/data.txt");
        tl = new TaskList(st.loadFile());
    }

    public String getResponse(String line) throws DukeException, DateTimeParseException, IOException {
        String response;
        System.out.println("Input received: " + line);
        String[] words = line.split(" ");
        switch (words[0]) {
        case "list":
            response = tl.toString();
            break;
        case "done":
            response = tl.markDone(Integer.parseInt(words[1]) - 1);
            break;
        case "delete":
            response = tl.delete(Integer.parseInt(words[1]) - 1);
            break;
        case "find":
            response = tl.getFindString(words);
            break;
        default:
            response = tl.addTask(words);
            break;
        }
        if (!(words[0].equals("list"))) {
            tl.saveFile(st);
        }
        return response;
    }
}
