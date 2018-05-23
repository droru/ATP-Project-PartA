package algorithms.search;

public interface ISearchingAlgorithm {
    /**
     * solve the searching problem
     * @param isearch - the searching problem
     * @return the solution for the searching problem
     */
    Solution solve(ISearchable isearch);

    /**
     * getter for the name of the current algorithm
     * @return string that represents the algorithm
     */
    String getName();

    /**
     * getter for number of nodes that the algorithm evaluated in order to find the solution
     * @return int theat represents the number of nodes evaluated
     */
    int getNumberOfNodesEvaluated();
}
