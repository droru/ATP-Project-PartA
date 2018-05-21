package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected Solution solution;
    protected int numNodesEvaluated = 0;

    /**
     * empty constructor
     */
    public ASearchingAlgorithm() { solution = new Solution();  }

    /**
     * getter for the name of the current algorithm
     * @return string that represents the algorithm
     */
    public abstract String getName();

    /**
     * solve the searching problem
     * @param isearch - the searching problem
     * @return the solution for the searching problem
     */
    public abstract Solution solve(ISearchable isearch);

    /**
     * getter for number of nodes that the algorithm evaluated in order to find the solution
     * @return int theat represents the number of nodes evaluated
     */
    public int getNumberOfNodesEvaluated(){return numNodesEvaluated;}

    /**
     * go recursively through the states using the parent field
     * and add each state to the solution
     * @param state - the state of the current iteration of the recursive
     * @param solution - the solution for the current iteration of the recursive
     */
    public void backtrackPath(AState state, Solution solution) {
        if(state != null && state.getParent() != null)
            backtrackPath(state.getParent(), solution);
        solution.AddState(state);
    }
}
