package dukebot.command;

import dukebot.exception.DukeException;
import dukebot.storage.Storage;
import dukebot.tasklist.TaskList;
import dukebot.tasklist.Todo;
import dukebot.ui.LineName;
import dukebot.ui.Ui;
import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

class NewTodoCommandTest {

    @Test
    void execute_inpArrNoTodoDescription_uiSayLineTodoEmpty() {
        TaskList taskListMock = mock(TaskList.class);
        Ui uiMock = mock(Ui.class);
        Storage storageMock = mock(Storage.class);
        NewTodoCommand newTodoCommand = new NewTodoCommand(new String[]{"todo"});

        newTodoCommand.execute(taskListMock, uiMock, storageMock);

        verify(uiMock).sayLine(LineName.TODO_EMPTY);
        verifyNoMoreInteractions(uiMock);
        verifyZeroInteractions(taskListMock);
        verifyZeroInteractions(storageMock);
    }

    @Test
    void execute_TodoHasDescription_TodoAddedToTaskListStorageCalledUiCalled() throws Exception, DukeException {
        TaskList taskListMock = mock(TaskList.class);
        Ui uiMock = mock(Ui.class);
        Storage storageMock = mock(Storage.class);

        Todo testTodo = new Todo("test test2");
        NewTodoCommand newTodoCommand = new NewTodoCommand(new String[]{"todo", "test", "test2"});

        newTodoCommand.execute(taskListMock, uiMock, storageMock);

        PowerMockito.whenNew(Todo.class).withArguments("test test2").thenReturn(testTodo);

        verify(taskListMock).addTask(testTodo);
        verify(storageMock).saveToFile(taskListMock);
        verify(uiMock).newTask(testTodo);

        verifyNoMoreInteractions(uiMock);
        verifyNoMoreInteractions(taskListMock);
        verifyNoMoreInteractions(storageMock);
    }
}