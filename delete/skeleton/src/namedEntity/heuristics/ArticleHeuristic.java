package namedEntity.heuristics;

import java.util.Set;

public class ArticleHeuristic extends Heuristic {
    
    private static Set<String> keyWords = Set.of(
            "a","an","the",
            //upper case words
            "A","An","The");


    protected boolean isEntity(String word) {
        return word.length() > 1 && keyWords.contains(word);
    }
}
