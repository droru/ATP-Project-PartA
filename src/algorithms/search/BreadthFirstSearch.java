package algorithms.search;
import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {

    @Override
    public String getName() {
        return "Breadth First Search";
    }

    @Override
    public Solution solve(ISearchable isearch) {
        LinkedHashSet<AState> visited = new LinkedHashSet<>();
        LinkedList<AState> queue = new LinkedList<>();
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

    private void backtrackPath(AState state, Solution solution) {
        if(state.getParent() != null)
            backtrackPath(state.getParent(), solution);
        solution.AddState(state);
    }
}
