package algorithms.search;
import javafx.util.Pair;

import java.util.List;

public interface ISearchable  {
    List<AState> getAllPossibleStates(AState state);
    AState getStartState();
    AState getGoalState();
}
