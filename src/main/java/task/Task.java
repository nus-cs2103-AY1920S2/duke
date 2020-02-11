package task;

import exception.DukeException;
import exception.UIException;
import parser.Parser;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String type, String isDone, String description) {
        this.type = type;
        this.description = description;
        this.isDone = isDone.equals("Y");
    }

    public Task(String type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    /**
     * @param description Raw input from user
     * @return Task Returns one of the three subclasses of Task, Todo/Event/Deadline
     * @throws DukeException Handles case where task type is not recognized
     */
    public static Task newTask(String description) throws DukeException {
        String type = Parser.getType(description);
        String typeLess = description.substring(type.length()).trim();

        switch (type) {
            case "todo":
                return new Todo(typeLess);
            case "event":
                return new Event(typeLess);
            case "deadline":
                return new Deadline(typeLess);
            case "period":
                return new PeriodTask(typeLess);
            default:
                throw new UIException("Task not recognized");
        }
    }

    /**
     * Reads String from storage and then creates the right Task Object
     *
     * @param entry String as stored in txt file
     * @return Task
     * @throws DukeException
     */
    public static Task newTaskFromMemory(String entry) throws DukeException {
        String[] splitEntry = entry.split("\\|");
        String type = splitEntry[0];
        switch (type) {
            case "[T]":
                return new Todo(splitEntry);
            case "[E]":
                return new Event(splitEntry);
            case "[D]":
                return new Deadline(splitEntry);
            case "[P]":
                return new PeriodTask(splitEntry);
            default:
                throw new UIException("Task not recognized");
        }
    }

    /** @return String "Y" for done, "N" for not done */
    public String getStatusIcon() {
        return (this.isDone ? "Y" : "N"); // Couldn't view the ticks and crosses on my terminal
    }

    /**
     * @param term search term
     * @return Boolean returns whether term is in task description
     */
    public Boolean contains(String term) {
        return this.description.contains(term);
    }

    public void setDone() {
        this.isDone = true;
    }

    /** @return Boolean Getter for isDone */
    public Boolean isDone() {
        return this.isDone;
    }

    /** @return String */
    @Override
    public String toString() {
        return String.format("%s[%s] %s", this.type, this.getStatusIcon(), description);
    }

    /** @return String string used to convert task to txt to store */
    public String toStorable() {
        return String.format("%s|%s|%s", this.type, this.isDone ? "Y" : "N", this.description);
    }
}
