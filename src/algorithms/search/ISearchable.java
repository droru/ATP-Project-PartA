package algorithms.search;

import java.util.List;

public interface ISearchable  {
    /**
     * getter for a list with the possible and valid neighbors in the search problem for the given state
     * @param state - the state to evaluate
     * @return a list of AState which contains the possible states of the given state
     */
    List<AState> getAllPossibleStates(AState state);

    /**
     * getter for the start state of the search problem
     * @return the start state
     */
    AState getStartState();

    /**
     * getter for the goal state of the search problem
     * @return the goal state
     */
    AState getGoalState();
}
