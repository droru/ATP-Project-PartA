package algorithms.search;
import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {

    @Override
    public String getName() {
        return "Breadth First Search";
    }

    @Override
    public Solution solve(ISearchable isearch) {
        List<AState> visited = new ArrayList<>();
        //LinkedList<AState> queue = new LinkedList<>();
        Queue<AState> queue = getQueue();
        AState start = isearch.getStartState();
            AState goal = isearch.getGoalState();

        queue.add(start);
        visited.add(start);
        start.setDistance(0);

        while (!queue.isEmpty()) {
            AState vertex = queue.remove();
            if(vertex.equals(goal)) {
                backtrackPath(vertex, solution);
                return solution;
            }

            List<AState> adjStates = isearch.getAllPossibleStates(vertex);
            for (AState state : adjStates) {
                if(!visited.contains(state)){
                    state.setParent(vertex);
                    state.setDistance(vertex.getDistance()+1);
                    queue.add(state);
                    visited.add(state);
                }
                else{
                    if(vertex.getDistance()+1 < state.getDistance()) {
                        state.setDistance(vertex.getDistance()+1);
                        state.setParent(vertex);
                    }
                }
            }
        }
        //didn't find a way - retrun null
        return null;
    }

    protected Queue<AState> getQueue()
    {
        return new LinkedList<>();
    }
}
