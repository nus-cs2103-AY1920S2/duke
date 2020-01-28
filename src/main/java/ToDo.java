public class ToDo extends Task {

    protected ToDo(String desciption){
        super(desciption);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    public String saveData(){
        String temp = this.isDone? "1" : "0";
        //1 is done, 0 is not done

        return "ToDo" + "|" + temp + "|" + this.description;
    }
}
