package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected Solution solution;

    public ASearchingAlgorithm() { solution = new Solution();  }

    public abstract String getName();

    public abstract Solution solve(ISearchable isearch);

    public int getNumberOfNodesEvaluated(){return solution.getSolutionLength();}
}
