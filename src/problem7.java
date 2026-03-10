import java.util.*;

public class problem7 {

    // query -> frequency
    static HashMap<String, Integer> queryFreq = new HashMap<>();

    // add or update search query
    static void updateFrequency(String query) {
        queryFreq.put(query, queryFreq.getOrDefault(query, 0) + 1);
    }

    // autocomplete suggestions
    static void search(String prefix) {

        List<Map.Entry<String, Integer>> results = new ArrayList<>();

        // find queries starting with prefix
        for (Map.Entry<String, Integer> entry : queryFreq.entrySet()) {
            if (entry.getKey().startsWith(prefix)) {
                results.add(entry);
            }
        }

        // sort by frequency (highest first)
        results.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("Suggestions for \"" + prefix + "\":");

        int count = 0;
        for (Map.Entry<String, Integer> e : results) {
            System.out.println((count + 1) + ". " + e.getKey() +
                    " (" + e.getValue() + " searches)");
            count++;
            if (count == 10) break;
        }
    }

    public static void main(String[] args) {

        // existing searches
        updateFrequency("java tutorial");
        updateFrequency("javascript");
        updateFrequency("java download");
        updateFrequency("java tutorial");
        updateFrequency("java tutorial");
        updateFrequency("java 21 features");

        // user types "jav"
        search("jav");

        // new searches happening
        updateFrequency("java 21 features");
        updateFrequency("java 21 features");
    }
}