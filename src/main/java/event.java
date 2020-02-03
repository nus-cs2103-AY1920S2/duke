public class event extends Task {
    protected String when;
    public event(String description, String when){
        super(description);
        this.when = when;
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + when + ")";
    }

    @Override
    public String getWhen(){
        return "(at: " + this.when + ")";

    }


}
