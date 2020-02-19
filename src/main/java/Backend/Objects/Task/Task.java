package Backend.Objects.Task;

import Backend.Constants.TaskChars;
import Backend.Parsers.DateParser;

public abstract class Task {

    public String content;
    int index;
    public boolean done = false;
    public DateParser date;
    private char taskChar;

    public Task( String content, DateParser date, char taskChar ) {
        this.content = content;
        this.date = date;
        this.taskChar = taskChar;
    }

    protected Task( String content, char taskChar ){
        this.content = content;
        this.taskChar = taskChar;
    }

    public void indexTask( int index ){
        this.index = index;
    }

    @Override
    public String toString() {

        char done = this.done? TaskChars.DONE_CHAR : TaskChars.UNDONE_CHAR;

        return index + ". [" + taskChar + "]" + "[" + done + "] " + this.content;
    }

    /**
     * marks task as done
     */
    public void markAsDone(){
        this.done = true;
    }

}
