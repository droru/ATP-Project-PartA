package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    String name;

    public ASearchingAlgorithm() {
        this.name = "";
    }
    public ASearchingAlgorithm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
