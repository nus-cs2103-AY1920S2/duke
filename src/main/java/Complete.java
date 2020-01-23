public class Complete{
    private boolean YN;

    public Complete(boolean YN){
        this.YN = YN;
    }

    public boolean isCompleted(){
        return YN;
    }

    @Override
    public String toString(){
        return YN ? "[Finished!] " : "[Incomplete. Do lahhhhh] ";
    }
}