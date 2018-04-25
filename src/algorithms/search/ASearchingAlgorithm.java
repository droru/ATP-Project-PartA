package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected Solution solution;

    public ASearchingAlgorithm() { solution = new Solution();  }

    public abstract String getName();

    public abstract Solution solve(ISearchable isearch);

    public int getNumberOfNodesEvaluated(){return solution.getSolutionLength();}

    public void backtrackPath(AState state, Solution solution) {
        if(state.getParent() != null)
            backtrackPath(state.getParent(), solution);
        solution.AddState(state);
    }
}
