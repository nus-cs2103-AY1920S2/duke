public class ToDo extends Task {

    protected ToDo(String desciption){
        super(desciption);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
