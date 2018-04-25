package algorithms.search;

import java.util.ArrayList;
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
        stack=new Stack<>();
        AState start=isearch.getStartState();
        AState goal=isearch.getGoalState();
        LinkedHashSet<AState> visited=new LinkedHashSet<>();
        start.setParent(null);
       RunDfs(visited,start,isearch, goal, solution);
       return solution;
    }

    private void RunDfs(LinkedHashSet<AState> visited, AState vertex, ISearchable isearch, AState goal, Solution solution) {
        if(vertex != null) {

            visited.add(vertex);
            stack.push(vertex);
            if (vertex.equals(goal)) {
                backtrackPath(vertex, solution);
                //return solution;
            }
            List<AState> adjStates = isearch.getAllPossibleStates(vertex);
            for (AState state : adjStates) {
                if (!visited.contains(state)) {
                    state.setParent(vertex);
                    //return
                    RunDfs(visited, state, isearch, goal, solution);
                }
            }
                if (!stack.empty()) {
                    stack.pop();
                    //return RunDfs(visited, stack.pop(), isearch, goal);
                }
            }
        //return null;
        }
    private void backtrackPath(AState state, Solution solution) {
         if(state.getParent() != null)
            backtrackPath(state.getParent(), solution);
        solution.AddState(state);
    }
}
