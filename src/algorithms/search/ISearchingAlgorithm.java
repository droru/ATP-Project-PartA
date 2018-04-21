package algorithms.search;

public interface ISearchingAlgorithm {
    Solution solve(ISearchable isearch);
    String getName();
    int getNumberOfNodesEvaluated();
}
