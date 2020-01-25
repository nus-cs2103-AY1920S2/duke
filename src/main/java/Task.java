public interface Task {
    String getName();
    boolean getCompletion();
    Task makeCompleted();

    String writeFormat();
}
