public class ToDo extends Task {

    public ToDo(String desciption){
        super(desciption);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
