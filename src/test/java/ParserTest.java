import org.junit.jupiter.api.Test;
import tasks.TaskList;
import ui.Ui;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void parseTest() {
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(taskList, storage, ui, sc);

        parser.parse("todo buy groceries");
        assertEquals("[T][X] buy groceries", taskList.getTask(0).toString());
    }
}