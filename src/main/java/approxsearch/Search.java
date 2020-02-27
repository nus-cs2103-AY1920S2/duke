package approxsearch;

import duke.TaskList;

public class Search {
    private static final double MIN_RATIO = 0.7;
    private TaskList tasks;

    public Search(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Search for a match in tasks using the similarity ratio.
     * @param searchTerm term to search for.
     * @return All the tasks that matches the search term approximately.
     */
    public TaskList search(String searchTerm) {
        TaskList results = new TaskList();

        tasks.getTasksStream()
                .sorted((t1, t2) -> Double.compare(
                        SimilarityRatio.getHighestSimilarityRatio(searchTerm, t2.toString()),
                        SimilarityRatio.getHighestSimilarityRatio(searchTerm, t1.toString())
                ))
                .filter(t -> SimilarityRatio.getHighestSimilarityRatio(searchTerm, t.toString())
                        > MIN_RATIO
                )
                .forEach(t -> results.add(t));

        return results;
    }
}
