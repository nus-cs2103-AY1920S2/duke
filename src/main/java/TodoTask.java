public class TodoTask extends Task {

    public TodoTask(String description) throws InvalidDukeFormatException {
        super(description);
        if(description.equals("")){
            throw new InvalidDukeFormatException("The description of a todo cannot be empty.");
        }
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
