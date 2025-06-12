package namedEntity.heuristics;

public class ParityHeuristic extends Heuristic{
    protected boolean isEntity(String word){
        return word.length()>0 && ((word.length() % 2) == 0);
    }
}