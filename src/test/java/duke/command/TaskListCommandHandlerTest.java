package duke.command;

import static duke.util.MagicStrings.ERROR_COMMAND_MISSING_INDEX;
import static duke.util.MagicStrings.ERROR_COMMAND_TOO_MANY_INDICES;
import static duke.util.MagicStrings.ERROR_INDEX_OUT_OF_BOUNDS;
import static duke.util.MagicStrings.ERROR_INVALID_COMMAND;
import static duke.util.MagicStrings.ERROR_USED_FOR_TESTING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import duke.exception.DuchessException;
import duke.save.SaveStateStack;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * JUnit test for {@code TaskListCommandHandler}.
 */
public class TaskListCommandHandlerTest {
    // Dummy variables
    private final TaskList dummyTaskList = new TaskList();
    private final SaveStateStack dummySaveStateStack = new SaveStateStack();
    private final Ui dummyUi = new Ui();


    // Mock storage - preferred since this will not ever write to directory
    private final Storage dummyStorageMock = mock(Storage.class);

    /**
     * Tests the List command handler for a valid task list.
     */
    @Test
    public void list_validTaskList_success() {
        // Mocking classes
        Ui uiMock = mock(Ui.class);
        when(uiMock.printTaskList(dummyTaskList)).thenReturn("Dummy list");

        String command = "list";

        // Testing
        assertEquals("Dummy list", TaskListCommandHandler.handleListCommand(command, dummyTaskList, uiMock,
                dummyStorageMock, dummySaveStateStack));
        verify(uiMock, times(1)).printTaskList(dummyTaskList);
    }

    /**
     * Tests the exception handling of List command handler for an invalid task list.
     */
    @Test
    public void list_invalidTaskList_exceptionThrown() {
        // Mocking classes
        Ui uiMock = mock(Ui.class);
        when(uiMock.printTaskList(dummyTaskList)).thenThrow(new DuchessException(ERROR_USED_FOR_TESTING));

        String command = "list";

        // Testing
        try {
            TaskListCommandHandler.handleListCommand(command, dummyTaskList, uiMock,
                    dummyStorageMock, dummySaveStateStack);
            fail();
        } catch (DuchessException e) {
            assertEquals(ERROR_USED_FOR_TESTING, e.getMessage());
            verify(uiMock, times(1)).printTaskList(dummyTaskList);
            verifyNoMoreInteractions(uiMock);
        }
    }

    /**
     * Tests the handling of the done command for valid arguments.
     */
    @Test
    public void done_validArguments_success() {
        // Mocking classes
        final String command = "done 4";
        final SaveStateStack saveStateStackMock = mock(SaveStateStack.class);
        Task dummyTask = new Task("This is a dummy task");
        final Storage storageMock = mock(Storage.class);

        TaskList taskListMock = mock(TaskList.class);
        when(taskListMock.size()).thenReturn(10);
        when(taskListMock.completeTask(3)).thenReturn(dummyTask);

        Ui uiMock = mock(Ui.class);
        when(uiMock.printTaskCompleted(dummyTask)).thenReturn("Task completed");

        // Testing
        assertEquals("Task completed", TaskListCommandHandler.handleDoneCommand(command, taskListMock, uiMock,
                storageMock, saveStateStackMock));
        verify(saveStateStackMock, times(1)).saveState(command, taskListMock);
        verify(storageMock, times(1)).save(taskListMock);
        verify(taskListMock, times(1)).size();
        verify(taskListMock, times(1)).completeTask(3);
        verify(uiMock, times(1)).printTaskCompleted(dummyTask);

        verifyNoMoreInteractions(saveStateStackMock);
        verifyNoMoreInteractions(storageMock);
        verifyNoMoreInteractions(taskListMock);
        verifyNoMoreInteractions(uiMock);
    }

    /**
     * Tests the handling of the done command for commands missing indices.
     */
    @Test
    public void done_missingIndex_exceptionThrown() {
        String command = "done";

        // Testing
        try {
            TaskListCommandHandler.handleDoneCommand(command, dummyTaskList, dummyUi, dummyStorageMock,
                    dummySaveStateStack);
            fail();
        } catch (DuchessException e) {
            assertEquals(ERROR_COMMAND_MISSING_INDEX, e.getMessage());
        }
    }

    /**
     * Tests the handling of the done command for commands with too many indices.
     */
    @Test
    public void done_tooManyIndices_exceptionThrown() {
        String command = "done 1 2 3 4";

        // Testing
        try {
            TaskListCommandHandler.handleDoneCommand(command, dummyTaskList, dummyUi, dummyStorageMock,
                    dummySaveStateStack);
            fail();
        } catch (DuchessException e) {
            assertEquals(ERROR_COMMAND_TOO_MANY_INDICES, e.getMessage());
        }
    }

    /**
     * Tests the handling of the done command for invalid done command.
     */
    @Test
    public void done_invalidCommand_exceptionThrown() {
        String command = "done done";

        // Testing
        try {
            TaskListCommandHandler.handleDoneCommand(command, dummyTaskList, dummyUi, dummyStorageMock,
                    dummySaveStateStack);
            fail();
        } catch (DuchessException e) {
            assertEquals(ERROR_INVALID_COMMAND, e.getMessage());
        }
    }

    /**
     * Tests the handling of the done command for index out of bounds.
     */
    @Test
    public void done_taskAlreadyCompleted_exceptionThrown() {
        // Mocking classes
        TaskList taskListMock = mock(TaskList.class);
        when(taskListMock.size()).thenReturn(3);

        String command = "done 5";

        // Testing
        try {
            TaskListCommandHandler.handleDoneCommand(command, taskListMock, dummyUi, dummyStorageMock,
                    dummySaveStateStack);
            fail();
        } catch (DuchessException e) {
            assertEquals(ERROR_INDEX_OUT_OF_BOUNDS, e.getMessage());
            verify(taskListMock, times(1)).size();
            verifyNoMoreInteractions(taskListMock);
        }
    }

    /**
     * Tests the handling of the done command for index out of bounds.
     */
    @Test
    public void done_indexOutOfBounds_exceptionThrown() {
        // Mocking classes
        TaskList taskListMock = mock(TaskList.class);
        when(taskListMock.size()).thenReturn(3);

        String command = "done 5";

        // Testing
        try {
            TaskListCommandHandler.handleDoneCommand(command, taskListMock, dummyUi, dummyStorageMock,
                    dummySaveStateStack);
            fail();
        } catch (DuchessException e) {
            assertEquals(ERROR_INDEX_OUT_OF_BOUNDS, e.getMessage());
            verify(taskListMock, times(1)).size();
            verifyNoMoreInteractions(taskListMock);
        }
    }
}
