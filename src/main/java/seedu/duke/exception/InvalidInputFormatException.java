package seedu.duke.exception;

public class InvalidInputFormatException extends DukeException {
    @Override
    public String toString() {
        return "Invalid input format!\n"
                + "Format:\n"
                + "- To list all tasks: list\n"
                + "- To add new deadline: deadline [description] /by [due date in yyyy-mm-dd]\n"
                + "- To add new event: event [description] /at [date in yyyy-mm-dd]\n"
                + "- To add new todo: todo [description]\n"
                + "- To mark task as done: done [index]\n"
                + "- To delete a task: delete [index]\n"
                + "- To find a task: find [keyword]\n"
                + "- To exit: bye";
    }
}
