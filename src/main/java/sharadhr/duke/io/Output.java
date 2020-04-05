package sharadhr.duke.io;

import sharadhr.duke.task.Task;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * A class to neatly format and write any output from the organiser chat bot,
 * Duke, to the standard output.
 */
public class Output
{
    private static final String LOGO = "\n ____        _        \n" 
                                     + "|  _ \\ _   _| | _____ \n"
                                     + "| | | | | | | |/ / _ \\\n" 
                                     + "| |_| | |_| |   <  __/\n" 
                                     + "|____/ \\__,_|_|\\_\\___|\n";
    BufferedWriter writer;
    
    public Output()
    {
        this.writer = new BufferedWriter(new OutputStreamWriter(System.out));
    }
    
    public Output(OutputStream os)
    {
        try
        {
            this.writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        }
        catch (UnsupportedEncodingException e)
        {
            this.sayError(e);
        }
    }

    /////////////////////////////////////////////////////////////////////////
    // Instance
    /////////////////////////////////////////////////////////////////////////

    static void ioException() {
        System.err.println("I/O Error occurred.");
    }

    void appendWithNewline(String string) {
        try {
            this.writer.append(string);
            this.writer.newLine();
        }
        catch (Exception e) {
            ioException();
        }
    }

    void appendWithNewline(String... strings) {
        Arrays.asList(strings).forEach(this::appendWithNewline);
    }

    /**
     * Appends {@code message} to this {@link sharadhr.duke.io.Output} object
     *
     * @param message the {@link String} to append to this object
     */
    public void add(String message) {
        try {
            this.writer.append(message);
        }
        catch (Exception e) {
            ioException();
        }
    }

    /**
     * Flushes the contents of this writer's buffer to the standard output.
     */
    public void say() {
        try {
            this.writer.flush();
        }
        catch (Exception e) {
            ioException();
        }
    }

    /**
     * Writes a {@code String}) to the standard output.
     *
     * @param message The message to be written
     */
    public void say(String message) {
        try {
            this.appendWithNewline(message);
            this.writer.flush();
        }
        catch (IOException e) {
            ioException();
        }
    }

    /**
     * Writes multiple messages to the standard output. These messages are given as
     * a comma-separated list of {@link String}s.
     *
     * @param messages The list of messages to be written to standard output
     */
    public void say(String... messages) {
        try {
            this.appendWithNewline(messages);
            writer.flush();
        }
        catch (IOException e) {
            ioException();
        }
    }

    public void sayError(Exception e) {
        this.say("**********************ERROR**********************", e.toString(),
                 "**********************ERROR**********************");
    }

    /**
     * Greets the user.
     */
    public void sayHello()
    {
        this.say("Hello, this is" + LOGO + "How can I help?");
    }
    
    public String helloString() {
        return "Hello, this is" + LOGO + "How can I help?";
    }

    /**
     * Bids the user farewell; should be called when program is exiting.
     */
    public void sayGoodBye() {
        this.say("Goodbye, then; see you soon!");
    }

    /**
     * Given a task, tells the user that the task was added to the task list,
     * formatted appropriately.
     *
     * @param task The task that was added to the task list
     */
    public void sayTaskAdded(Task task) {
        this.say("This task was added: ", task.toString());
    }

    /**
     * Given a task, tells the user that the task was marked complete, formatted
     * appropriately.
     *
     * @param task The task that was marked complete
     */
    public void sayTaskMarkedComplete(Task task) {
        this.say("This task has been marked done:", task.toString());
    }

    public void sayTaskDeleted(Task task) {
        this.say("This task has been deleted:", task.toString());
    }

    /**
     * Closes this writer.
     */
    public void close() {
        try {
            writer.close();
        }
        catch (Exception e) {
            ioException();
        }
    }
}