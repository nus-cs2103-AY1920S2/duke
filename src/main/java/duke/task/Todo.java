package duke.task;

import duke.main.Constant;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean completed) {
        super(name, completed);
    }

    @Override
    public Todo complete() {
        return new Todo(this.name, true);
    }

    @Override
    public String storeFormat() {
        return "T| |" + completed + "| |" + name;
    }

    @Override
    public String toString() {
        if (completed) {
            return "[T]" + Constant.CHECK_DONE + this.name;
        } else {
            return "[T]" + Constant.CHECK_NOTDONE + this.name;
        }
    }
}