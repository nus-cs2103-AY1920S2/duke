public abstract class Command {

    public void execute(Common common,Ui ui){}

    public boolean isExit(){
        return false;
    }
}
