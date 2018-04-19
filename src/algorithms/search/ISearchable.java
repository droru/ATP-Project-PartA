package algorithms.search;
import java.util.List;

public interface ISearchable {
    List<Astate> getAllPossibleStates(Astate state);
}
