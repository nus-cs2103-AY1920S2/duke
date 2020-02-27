package duke;

public class ToDo extends Task {

    public ToDo(String instruction){
        super(instruction);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
