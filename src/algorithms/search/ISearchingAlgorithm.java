package algorithms.search;

public interface ISearchingAlgorithm {
    Solution solve(Iseachable);
    String getName();
    int getNumberOfNodesEvqluated();
}
