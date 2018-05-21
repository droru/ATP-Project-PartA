package algorithms.search;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {

    Stack<AState>stack;

    @Override
    public String getName() {
        return "Depth First Search";
    }

    @Override
    public Solution solve(ISearchable isearch) {
        if(isearch == null) return  null;

        stack=new Stack<>();
        AState start=isearch.getStartState();
        AState goal=isearch.getGoalState();
        LinkedHashSet<AState> visited=new LinkedHashSet<>();
        start.setParent(null);


        stack.push(start);
        visited.add(start);
        RunDfs(visited,isearch, goal);


        return solution;
    }

    /**
     * run with a stack on all the states
     * @param visited - a Linked Hash Set of AState
     * @param isearch - our searching problem
     * @param goal - our goal for the search
     */
    private void RunDfs(LinkedHashSet<AState> visited, ISearchable isearch, AState goal) {
        AState vertex;
        while(!stack.isEmpty()){
            vertex = stack.pop();
            if (vertex.equals(goal)) {
                backtrackPath(vertex, solution);
                break;
            }
            else {
                List<AState> adjStates = isearch.getAllPossibleStates(vertex);
                numNodesEvaluated++;
                for (AState state : adjStates) {
                    if (!visited.contains(state)) {
                        state.setParent(vertex);
                        stack.push(state);
                        visited.add(state);
                    }
                }
            }
        }
    }
}
