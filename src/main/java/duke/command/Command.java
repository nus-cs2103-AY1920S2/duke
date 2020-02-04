package duke.command;

public abstract class Command implements Executable {

    public boolean isExit() {
        return false;
    }
}