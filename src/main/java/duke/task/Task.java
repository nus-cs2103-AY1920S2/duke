package duke.task;

import java.time.LocalDate;

import duke.main.Constant;

public class Task {
    public final boolean completed;
    public final String name;

    protected Task(final String name) {
        this.name = name;
        this.completed = false;
    }

    protected Task(final String name, final boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public Task complete() {
        return new Task(this.name, true);
    }

    public String storeFormat() {
        return completed + "| |" + name;
    }

    public boolean compareDate(final LocalDate inputDate) {
        return false;
    }

    @Override
    public String toString() {
        if (completed) {
            return Constant.CHECK_DONE + this.name;
        } else {
            return Constant.CHECK_NOTDONE + this.name;
        }
    }
}