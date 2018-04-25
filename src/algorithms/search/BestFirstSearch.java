package algorithms.search;

import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch {

    @Override
    public String getName() {
        return "Best First Search";
    }

    @Override
    protected Queue<AState> getQueue() {
        return new PriorityQueue<>();
    }
}
