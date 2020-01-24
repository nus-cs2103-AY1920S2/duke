public class NoDescriptionException extends DukeException {

    @Override
    public String toString() { 
        return "*************************************************************\n"
                + "☹ OI. Provide the description of the task! >:("
                + "\n*************************************************************";

    }
}