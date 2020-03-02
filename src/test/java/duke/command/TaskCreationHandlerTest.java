package duke.command;

import static duke.util.MagicStrings.ERROR_DEADLINE_MISSING_CONTENT;
import static duke.util.MagicStrings.ERROR_DEADLINE_MISSING_DEADLINE;
import static duke.util.MagicStrings.ERROR_EVENT_MISSING_CONTENT;
import static duke.util.MagicStrings.ERROR_EVENT_MISSING_TIME_FRAME;
import static duke.util.MagicStrings.ERROR_TODO_MISSING_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.exception.DuchessException;
import duke.save.SaveStateStack;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.RecurringDeadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;
import duke.util.Frequency;

/**
 * JUnit test class for {@code TaskCreationHandler}.
 */
public class TaskCreationHandlerTest {
    // Dummy variables
    private final TaskList dummyTaskList = new TaskList();
    private final SaveStateStack dummySaveStateStack = new SaveStateStack();
    private final Ui dummyUi = new Ui();


    // Mock storage - preferred since this will not ever write to directory
    private final Storage dummyStorageMock = mock(Storage.class);

    /**
     * Tests the successful creation of a ToDo.
     */
    @Test
    public void toDo_validInputString_success() {
        String input = "todo Run";
        // Mocking classes
        SaveStateStack saveStateStackMock = mock(SaveStateStack.class);
        Storage storageMock = mock(Storage.class);

        Ui uiMock = mock(Ui.class);
        when(uiMock.printTaskAdded(any(Task.class), eq(1))).thenReturn("Todo created");

        TaskList testTaskList = new TaskList();

        // Testing
        String result = TaskCreationHandler.handleTodoCommand(input, testTaskList, uiMock, storageMock,
                saveStateStackMock);

        assertEquals("Todo created", result);
        assertEquals(1, testTaskList.size());
        Task taskCreated = testTaskList.getTask(0);
        assertEquals(new ToDo("Run").toString(), taskCreated.toString());

        verify(saveStateStackMock, times(1)).saveState(input, testTaskList);
        verify(storageMock, times(1)).save(testTaskList);
        verify(uiMock, times(1)).printTaskAdded(any(Task.class), eq(1));

        verifyNoMoreInteractions(saveStateStackMock);
        verifyNoMoreInteractions(storageMock);
        verifyNoMoreInteractions(uiMock);
    }

    /**
     * Tests the exception thrown from trying to create a ToDo with missing description.
     */
    @Test
    public void toDo_missingDescription_exceptionThrown() {
        String input = "todo";
        try {
            TaskCreationHandler.handleTodoCommand(input, dummyTaskList, dummyUi, dummyStorageMock, dummySaveStateStack);
            fail();
        } catch (DuchessException e) {
            assertEquals(ERROR_TODO_MISSING_CONTENT, e.getMessage());
        }
    }

    /**
     * Tests the successful creation of an Event.
     */
    @Test
    public void event_validInputString_success() {
        String input = "event Run /at Tembusu tonight";
        // Mocking classes
        TaskList testTaskList = new TaskList();

        SaveStateStack saveStateStackMock = mock(SaveStateStack.class);
        Storage storageMock = mock(Storage.class);

        Ui uiMock = mock(Ui.class);
        when(uiMock.printTaskAdded(any(Task.class), eq(1))).thenReturn("Event created");

        // Testing
        String result = TaskCreationHandler.handleEventCommand(input, testTaskList, uiMock, storageMock,
                saveStateStackMock);

        assertEquals("Event created", result);
        assertEquals(1, testTaskList.size());
        Task taskCreated = testTaskList.getTask(0);
        assertEquals(new Event("Run", "Tembusu tonight").toString(), taskCreated.toString());

        verify(saveStateStackMock, times(1)).saveState(input, testTaskList);
        verify(storageMock, times(1)).save(testTaskList);
        verify(uiMock, times(1)).printTaskAdded(any(Task.class), eq(1));

        verifyNoMoreInteractions(saveStateStackMock);
        verifyNoMoreInteractions(storageMock);
        verifyNoMoreInteractions(uiMock);
    }

    /**
     * Tests the exception handling when creating an Event without time frame.
     */
    @Test
    public void event_missingTimeFrame_exceptionThrown() {
        String input = "event Run";

        try {
            TaskCreationHandler.handleEventCommand(input, dummyTaskList, dummyUi, dummyStorageMock, dummySaveStateStack);
            fail();
        } catch (DuchessException e) {
            assertEquals(ERROR_EVENT_MISSING_TIME_FRAME, e.getMessage());
        }
    }

    /**
     * Tests the exception handling when creating an Event without description.
     */
    @Test
    public void event_missingDescription_exceptionThrown() {
        // Missing description only
        String input = "event /at Home";
        try {
            TaskCreationHandler.handleEventCommand(input, dummyTaskList, dummyUi, dummyStorageMock, dummySaveStateStack);
            fail();
        } catch (DuchessException e) {
            assertEquals(ERROR_EVENT_MISSING_CONTENT, e.getMessage());
        }

        // Missing both description and time frame
        String inputTwo = "event";
        try {
            TaskCreationHandler.handleEventCommand(inputTwo, dummyTaskList, dummyUi, dummyStorageMock,
                    dummySaveStateStack);
            fail();
        } catch (DuchessException e) {
            assertEquals(ERROR_EVENT_MISSING_CONTENT, e.getMessage());
        }
    }

    /**
     * Tests the successful creation of a non-recurring Deadline.
     */
    @Test
    public void deadline_validNonRecurringInputString_success() {
        String input = "deadline Run /by tonight";
        // Mocking classes
        TaskList testTaskList = new TaskList();

        SaveStateStack saveStateStackMock = mock(SaveStateStack.class);
        Storage storageMock = mock(Storage.class);

        Ui uiMock = mock(Ui.class);
        when(uiMock.printTaskAdded(any(Task.class), eq(1))).thenReturn("Deadline created");

        // Testing
        String result = TaskCreationHandler.handleDeadlineCommand(input, testTaskList, uiMock, storageMock,
                saveStateStackMock);

        assertEquals("Deadline created", result);
        assertEquals(1, testTaskList.size());
        Task taskCreated = testTaskList.getTask(0);
        assertEquals(new Deadline("Run", LocalDate.now().atTime(21, 0)).toString(), taskCreated.toString());

        verify(saveStateStackMock, times(1)).saveState(input, testTaskList);
        verify(storageMock, times(1)).save(testTaskList);
        verify(uiMock, times(1)).printTaskAdded(any(Task.class), eq(1));

        verifyNoMoreInteractions(saveStateStackMock);
        verifyNoMoreInteractions(storageMock);
        verifyNoMoreInteractions(uiMock);
    }

    /**
     * Tests the successful creation of a recurring Deadline without end time for repeat.
     */
    @Test
    public void deadline_validRecurringInputStringWithNoEnd_success() {
        String input = "deadline Run /by tonight /every week";
        // Mocking classes
        TaskList testTaskList = new TaskList();

        SaveStateStack saveStateStackMock = mock(SaveStateStack.class);
        Storage storageMock = mock(Storage.class);

        Ui uiMock = mock(Ui.class);
        when(uiMock.printTaskAdded(any(Task.class), eq(1))).thenReturn("Deadline created");

        // Testing
        String result = TaskCreationHandler.handleDeadlineCommand(input, testTaskList, uiMock, storageMock,
                saveStateStackMock);

        assertEquals("Deadline created", result);
        assertEquals(1, testTaskList.size());
        Task taskCreated = testTaskList.getTask(0);
        assertEquals(new RecurringDeadline("Run", LocalDate.now().atTime(21, 0), Frequency.WEEKLY).toString(),
                taskCreated.toString());

        verify(saveStateStackMock, times(1)).saveState(input, testTaskList);
        verify(storageMock, times(1)).save(testTaskList);
        verify(uiMock, times(1)).printTaskAdded(any(Task.class), eq(1));

        verifyNoMoreInteractions(saveStateStackMock);
        verifyNoMoreInteractions(storageMock);
        verifyNoMoreInteractions(uiMock);
    }

    /**
     * Tests the successful creation of a recurring Deadline with end time for repeat.
     */
    @Test
    public void deadline_validRecurringInputStringWithEnd_success() {
        String input = "deadline Run /by tonight /every week /stop tomorrow";
        // Mocking classes
        TaskList testTaskList = new TaskList();

        SaveStateStack saveStateStackMock = mock(SaveStateStack.class);
        Storage storageMock = mock(Storage.class);

        Ui uiMock = mock(Ui.class);
        when(uiMock.printTaskAdded(any(Task.class), eq(1))).thenReturn("Deadline created");

        // Testing
        String result = TaskCreationHandler.handleDeadlineCommand(input, testTaskList, uiMock, storageMock,
                saveStateStackMock);

        assertEquals("Deadline created", result);
        assertEquals(1, testTaskList.size());
        Task taskCreated = testTaskList.getTask(0);
        assertEquals(new RecurringDeadline("Run", LocalDate.now().atTime(21, 0), Frequency.WEEKLY,
                LocalDate.now().plusDays(1).atTime(17, 0)).toString(), taskCreated.toString());

        verify(saveStateStackMock, times(1)).saveState(input, testTaskList);
        verify(storageMock, times(1)).save(testTaskList);
        verify(uiMock, times(1)).printTaskAdded(any(Task.class), eq(1));

        verifyNoMoreInteractions(saveStateStackMock);
        verifyNoMoreInteractions(storageMock);
        verifyNoMoreInteractions(uiMock);
    }

    /**
     * Tests the exception handling when creating a Deadline without description.
     */
    @Test
    public void deadline_missingContent_exceptionThrown() {
        // Missing description only
        String input = "deadline /by tonight";
        try {
            TaskCreationHandler.handleDeadlineCommand(input, dummyTaskList, dummyUi, dummyStorageMock,
                    dummySaveStateStack);
            fail();
        } catch (DuchessException e) {
            assertEquals(ERROR_DEADLINE_MISSING_CONTENT, e.getMessage());
        }

        // Missing both description and deadline
        String inputTwo = "deadline";
        try {
            TaskCreationHandler.handleDeadlineCommand(inputTwo, dummyTaskList, dummyUi, dummyStorageMock,
                    dummySaveStateStack);
            fail();
        } catch (DuchessException e) {
            assertEquals(ERROR_DEADLINE_MISSING_CONTENT, e.getMessage());
        }
    }

    /**
     * Tests the exception handling when creating a Deadline without deadline.
     */
    @Test
    public void deadline_missingDeadline_exceptionThrown() {
        // Missing description only
        String input = "deadline Run!";
        try {
            TaskCreationHandler.handleDeadlineCommand(input, dummyTaskList, dummyUi, dummyStorageMock,
                    dummySaveStateStack);
            fail();
        } catch (DuchessException e) {
            assertEquals(ERROR_DEADLINE_MISSING_DEADLINE, e.getMessage());
        }
    }

    /**
     * Tests the exception handling when creating a Deadline with end date but no repeat frequency.
     */
    @Test
    public void deadline_missingFrequency_nonRecurringDeadlineCreated() {
        String input = "deadline Run /by tonight /stop tomorrow";
        // Mocking classes
        TaskList testTaskList = new TaskList();

        SaveStateStack saveStateStackMock = mock(SaveStateStack.class);
        Storage storageMock = mock(Storage.class);

        Ui uiMock = mock(Ui.class);
        when(uiMock.printTaskAdded(any(Task.class), eq(1))).thenReturn("Deadline created");

        // Testing
        String result = TaskCreationHandler.handleDeadlineCommand(input, testTaskList, uiMock, storageMock,
                saveStateStackMock);

        assertEquals("Deadline created", result);
        assertEquals(1, testTaskList.size());
        Task taskCreated = testTaskList.getTask(0);
        assertEquals(new Deadline("Run", LocalDate.now().atTime(21, 0)).toString(),
                taskCreated.toString());

        verify(saveStateStackMock, times(1)).saveState(input, testTaskList);
        verify(storageMock, times(1)).save(testTaskList);
        verify(uiMock, times(1)).printTaskAdded(any(Task.class), eq(1));

        verifyNoMoreInteractions(saveStateStackMock);
        verifyNoMoreInteractions(storageMock);
        verifyNoMoreInteractions(uiMock);
    }
}
