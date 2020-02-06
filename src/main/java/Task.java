public interface Task {
    String getName();

    boolean getCompletion();

    void makeCompleted();

    String writeFormat();
}
