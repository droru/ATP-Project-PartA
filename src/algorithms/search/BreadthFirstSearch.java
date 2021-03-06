package algorithms.search;
import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {

    @Override
    public String getName() {
        return "Breadth First Search";
    }

    @Override
    public Solution solve(ISearchable isearch) {
        if(isearch == null) return null;

        LinkedHashSet<AState> visited = new LinkedHashSet<>();
        Queue<AState> queue = getQueue();

        AState start = isearch.getStartState();
        AState goal = isearch.getGoalState();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            AState vertex = queue.remove();
            if(vertex.equals(goal)) {
                backtrackPath(vertex, solution);
                return solution;
            }

            List<AState> adjStates = isearch.getAllPossibleStates(vertex);
            numNodesEvaluated++;
            for (AState state : adjStates) {
                if(!visited.contains(state)){
                    state.setParent(vertex);
                    queue.add(state);
                    visited.add(state);
                }


            }
        }
        //didn't find a way - retrun null
        return null;
    }

    /**
     * define on which queue the algorithm is running
     * for Breadth First Search - the type is Queue of AState
     * for Best First Search - the type is Priority Queue of AState
     * @return the queue for the algorithm
     */
    protected Queue<AState> getQueue()
    {
        return new LinkedList<>();
    }
}
