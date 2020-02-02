package seedu.java.util;

public class Complete {
    private boolean yesOrNo;

    public Complete(boolean yesOrNo) {
        this.yesOrNo = yesOrNo;
    }

    public boolean isCompleted() {
        return yesOrNo;
    }

    @Override
    public String toString() {
        return yesOrNo ? "[Finished!] " : "[Incomplete] ";
    }
}