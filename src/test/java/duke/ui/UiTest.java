package duke.ui;

import static duke.util.MagicStrings.ERROR_INDEX_OUT_OF_BOUNDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import duke.exception.DuchessException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Pair;

/**
 * JUnit test class for {@code Ui}.
 */
public class UiTest {
    // @@author zhuhanming-reused
    // Reused from https://stackoverflow.com/a/1119559 with minor modifications

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    /**
     * Sets the console stream to a readable byte stream.
     */
    @BeforeAll
    public static void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Clears the stream of any existing data.
     */
    @AfterEach
    public void clearStream() {
        outContent.reset();
    }

    /**
     * Restores the console stream back to the default stream.
     */
    @AfterAll
    public static void restoreStream() {
        System.setOut(originalOut);
    }

    // @@author

    /**
     * Tests the default printing of the Ui.
     */
    @Test
    public void testUiPrint() {
        String result = new Ui().print("Hello", "World", "What's up", "Strangers!");
        assertEquals("Hello\nWorld\nWhat's up\nStrangers!\n", result);
    }

    /**
     * Tests the {@code Ui} methods that return default strings.
     */
    @Test
    public void testUiPrintDefaultStrings() {
        Ui testUi = new Ui();
        assertEquals("Hello! My name is Duchess.\nHow may I help you?\n", testUi.printWelcome());
        assertEquals("Bye, is it? Shoo shoo then.\nDon't need to worry, I'll remember what you told me today.\n",
                testUi.printGoodbye());
        assertEquals("All tasks have been deleted. If you regret this, try undoing.\n", testUi.printAllDeleted());
        assertEquals("Your list of tasks has been sorted.\nType 'list' to see the new order.\n",
                testUi.printTaskListSorted());
    }

    /**
     * Tests the commands that return non-task-related content.
     */
    @Test
    public void returnStrings_nonTaskRelated_success() {
        Ui testUi = new Ui();
        assertEquals("I've helped to fix your mistakes again. Your last command:\nsort\nhas been undone.\n",
                testUi.printUndoMessage("sort"));
        assertEquals("This is a loading error message\n", testUi.printLoadingError("This is a loading error message"));
        assertEquals("Stop causing me trouble...\nERROR!\n", testUi.printError("ERROR!"));
    }

    /**
     * Tests the commands that print single-task-related content.
     */
    @Test
    public void returnStrings_singleTaskRelated_success() {
        // Mocking the classes
        Task taskMock = mock(Task.class);
        when(taskMock.toString()).thenReturn("Task info");
        when(taskMock.isCompleted()).thenReturn(true);

        Ui testUi = new Ui();

        // Testing
        assertEquals("As always, needing someone to keep track of things for you...\nTask info"
                + "\nI've already tracked 3 tasks for you.\n", testUi.printTaskAdded(taskMock, 3));
        assertEquals("Great! One less thing for me to track for you.\nTask info [DELETED]"
                + "\nNow I'm tracking 1 task for you.\n", testUi.printTaskDeleted(taskMock, 1));
        assertEquals("Oh? You actually completed something? Impressive...\nTask info\n",
                testUi.printTaskCompleted(taskMock));
        assertEquals("Behind schedule as always... I've pushed back the deadline for you by 5 days.\nTask info\n",
                testUi.printTaskSnoozed(taskMock, "5 days"));
    }

    /**
     * Tests the {@code printTaskList} method with a valid non-empty task list.
     */
    @Test
    public void returnStrings_printValidNonEmptyTaskList_success() {
        // Mocking the classes
        TaskList taskListMock = mock(TaskList.class);
        Task taskMock = mock(Task.class);
        when(taskMock.toString()).thenReturn("Task info");
        when(taskListMock.size()).thenReturn(2);
        when(taskListMock.getTask(0)).thenReturn(taskMock);
        when(taskListMock.getTask(1)).thenReturn(taskMock);

        Ui testUi = new Ui();

        // Testing
        assertEquals("Sighs... you never remember what you say, don't you.\nYou said these:\n"
                + "1.\tTask info\n2.\tTask info\n", testUi.printTaskList(taskListMock));

        verify(taskListMock, times(1)).size();
        verify(taskListMock, times(1)).getTask(0);
        verify(taskListMock, times(1)).getTask(1);
    }

    /**
     * Tests the {@code printTaskList} method with a valid empty task list.
     */
    @Test
    public void returnStrings_printValidEmptyTaskList_success() {
        // Mocking the classes
        TaskList taskListMock = mock(TaskList.class);
        when(taskListMock.size()).thenReturn(0);

        Ui testUi = new Ui();

        // Testing
        assertEquals("Is this a trick question? You have not told me anything about 'tasks'.\n",
                testUi.printTaskList(taskListMock));

        verify(taskListMock, times(1)).size();
    }

    /**
     * Tests the {@code printTaskList} method with an invalid task list.
     */
    @Test
    public void returnStrings_printInvalidTaskList_exceptionThrown() {
        // Mocking the classes
        TaskList taskListMock = mock(TaskList.class);
        when(taskListMock.size()).thenReturn(1);
        when(taskListMock.getTask(0)).thenThrow(new DuchessException(ERROR_INDEX_OUT_OF_BOUNDS));

        Ui testUi = new Ui();

        // Testing
        try {
            testUi.printTaskList(taskListMock);
            fail();
        } catch (DuchessException e) {
            assertEquals(ERROR_INDEX_OUT_OF_BOUNDS, e.getMessage());
            verify(taskListMock, times(1)).size();
            verify(taskListMock, times(1)).getTask(0);
        }
    }

    /**
     * Tests the {@code printFilteredTaskList} method with a non-empty filtered task list.
     */
    @Test
    public void returnStrings_printNonEmptyFilteredTaskList_success() {
        // Mocking the classes
        Task taskMock = mock(Task.class);
        when(taskMock.toString()).thenReturn("Task info");

        ArrayList<Pair<Task, Integer>> pairedTaskList = new ArrayList<>();
        pairedTaskList.add(new Pair<>(taskMock, 3));
        pairedTaskList.add(new Pair<>(taskMock, 5));

        Ui testUi = new Ui();

        // Testing
        assertEquals("Not bad, I found the following:\n1.\tTask info\n\t[REF INDEX FOR DELETE/DONE: 4]"
                + "\n2.\tTask info\n\t[REF INDEX FOR DELETE/DONE: 6]\n", testUi.printFilteredTaskList(pairedTaskList));
    }

    /**
     * Tests the {@code printFilteredTaskList} method with a empty filtered task list.
     */
    @Test
    public void returnStrings_printEmptyFilteredTaskList_success() {
        ArrayList<Pair<Task, Integer>> pairedTaskList = new ArrayList<>();
        Ui testUi = new Ui();

        // Testing
        assertEquals("Couldn't find anything that matches what you want.\nI sure hope you're not testing me!\n",
                testUi.printFilteredTaskList(pairedTaskList));
    }

    /**
     * Tests the {@code printLine} method.
     */
    @Test
    public void testUiPrintLineToConsole() {
        Ui testUi = new Ui();
        testUi.printLine();
        assertEquals(new String(new char[65]).replace("\0", "\u2501") + "\n", outContent.toString()); // line
    }

    /**
     * Tests the {@code printConsoleWelcome} method.
     */
    @Test
    public void testUiPrintWelcomeMessageToConsole() {
        Ui testUi = new Ui();
        testUi.printConsoleWelcome();
        assertEquals("Hello from\n" + duke.ui.Ui.logo + "\nMy name is Duchess, as you can see above.\n"
                + "How may I help you?\n", outContent.toString());
    }

    /**
     * Tests the {@code printToConsole} method.
     */
    @Test
    public void testUiPrintToConsole() {
        Ui testUi = new Ui();
        testUi.printToConsole("This is a test message, watch out!");
        assertEquals("This is a test message, watch out!\n", outContent.toString());
    }

    /**
     * Tests the command reading of Ui.
     */
    @Test
    public void testUiReadCommand() {
        final InputStream originalIn = System.in;
        String mockUserInput = "done 20";
        InputStream inContent = new ByteArrayInputStream(mockUserInput.getBytes());
        System.setIn(inContent);
        Ui testUi = new Ui();
        assertEquals(mockUserInput, testUi.readCommand());
        System.setIn(originalIn);
    }
}
