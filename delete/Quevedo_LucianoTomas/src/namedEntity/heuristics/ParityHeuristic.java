package namedEntity.heuristics;

import java.util.Set;

public class ParityHeuristic extends Heuristic{

	protected boolean isEntity(String word){
		return word.length()>1 && (word.length() % 2) == 0;
	}	
}
