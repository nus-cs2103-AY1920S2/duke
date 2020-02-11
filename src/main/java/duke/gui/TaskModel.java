package duke.gui;

import javafx.beans.property.*;
import duke.entity.TaskList;
import duke.entity.task.Task;

public class TaskModel {

    private final StringProperty taskName;
    private final BooleanProperty isDone;
    private final StringProperty addedInfo;

    public TaskModel() {
        this(null);
    }

    public TaskModel(Task task) {
        this.taskName = new SimpleStringProperty(task.getTaskName());
        this.isDone = new SimpleBooleanProperty(task.isDone());
        this.addedInfo = new SimpleStringProperty(task.getAddedInfo());
    }

    public String getTaskName() {
        return taskName.get();
    }

    public StringProperty taskNameProperty() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName.set(taskName);
    }

    public boolean isIsDone() {
        return isDone.get();
    }

    public BooleanProperty isDoneProperty() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone.set(isDone);
    }

    public String getAddedInfo() {
        return addedInfo.get();
    }

    public StringProperty addedInfoProperty() {
        return addedInfo;
    }

    public void setAddedInfo(String addedInfo) {
        this.addedInfo.set(addedInfo);
    }

    public void setDone() {

    }
}
