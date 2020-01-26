public class ToDo extends Task implements java.io.Serializable{

    protected ToDo(String desciption){
        super(desciption);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
