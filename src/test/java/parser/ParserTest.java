package parser;

import org.junit.jupiter.api.Test;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void handleTaskCommand_invalidCommand_ExceptionCaught() {
        try {
            new Parser().handleTaskCommand("Hello", new TaskList(new ArrayList<Task>(), true),
                    new Ui(new Scanner(System.in)));
        } catch (Exception e) {
            fail();
        }
    }
}
