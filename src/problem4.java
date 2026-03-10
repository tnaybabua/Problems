import java.util.*;

public class problem4 {

    // n-gram -> set of document IDs
    static HashMap<String, Set<String>> index = new HashMap<>();

    static int N = 5; // 5-gram

    // break text into n-grams
    static List<String> getNGrams(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        List<String> grams = new ArrayList<>();

        for (int i = 0; i <= words.length - N; i++) {
            String gram = "";
            for (int j = 0; j < N; j++) {
                gram += words[i + j] + " ";
            }
            grams.add(gram.trim());
        }
        return grams;
    }

    // add document to database
    static void addDocument(String docId, String text) {
        List<String> grams = getNGrams(text);

        for (String g : grams) {
            index.putIfAbsent(g, new HashSet<>());
            index.get(g).add(docId);
        }
    }

    // analyze new document
    static void analyzeDocument(String docId, String text) {

        List<String> grams = getNGrams(text);

        HashMap<String, Integer> matchCount = new HashMap<>();

        for (String g : grams) {
            if (index.containsKey(g)) {

                for (String otherDoc : index.get(g)) {
                    matchCount.put(otherDoc,
                            matchCount.getOrDefault(otherDoc, 0) + 1);
                }
            }
        }

        System.out.println("Extracted " + grams.size() + " n-grams");

        for (String doc : matchCount.keySet()) {

            int matches = matchCount.get(doc);
            double similarity = (matches * 100.0) / grams.size();

            System.out.println("Found " + matches + " matching n-grams with " + doc);
            System.out.println("Similarity: " + similarity + "%");
        }
    }

    public static void main(String[] args) {

        // existing essays
        addDocument("essay_089.txt",
                "machine learning is a field of artificial intelligence that uses data");

        addDocument("essay_092.txt",
                "machine learning is a field of artificial intelligence that uses data and algorithms");

        // new submission
        analyzeDocument("essay_123.txt",
                "machine learning is a field of artificial intelligence that uses data and algorithms to learn");
    }
}