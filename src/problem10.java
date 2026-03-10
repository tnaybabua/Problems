import java.util.*;

public class problem10 {

    // L1 Cache (memory) - LRU using LinkedHashMap
    static LinkedHashMap<String, String> L1 =
            new LinkedHashMap<String, String>(10000, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry<String, String> e) {
                    return size() > 3; // small size for demo
                }
            };

    // L2 Cache (SSD simulated)
    static HashMap<String, String> L2 = new HashMap<>();

    // L3 Database (all videos)
    static HashMap<String, String> L3 = new HashMap<>();

    static int L1Hits = 0;
    static int L2Hits = 0;
    static int L3Hits = 0;

    // get video
    static void getVideo(String videoId) {

        // L1 check
        if (L1.containsKey(videoId)) {
            L1Hits++;
            System.out.println(videoId + " → L1 Cache HIT (0.5ms)");
            return;
        }

        System.out.println(videoId + " → L1 Cache MISS");

        // L2 check
        if (L2.containsKey(videoId)) {
            L2Hits++;
            System.out.println("L2 Cache HIT (5ms)");

            // promote to L1
            L1.put(videoId, L2.get(videoId));
            return;
        }

        System.out.println("L2 Cache MISS");

        // L3 database
        if (L3.containsKey(videoId)) {
            L3Hits++;
            System.out.println("L3 Database HIT (150ms)");

            // add to L2
            L2.put(videoId, L3.get(videoId));
        }
    }

    // statistics
    static void getStatistics() {

        int total = L1Hits + L2Hits + L3Hits;

        System.out.println("\nCache Statistics:");
        System.out.println("L1 Hits: " + L1Hits);
        System.out.println("L2 Hits: " + L2Hits);
        System.out.println("L3 Hits: " + L3Hits);

        if (total > 0) {
            System.out.println("Overall Hit Rate: " + (100.0 * total / total) + "%");
        }
    }

    public static void main(String[] args) {

        // database videos
        L3.put("video_123", "Movie Data");
        L3.put("video_999", "Another Movie");

        getVideo("video_123"); // L3
        getVideo("video_123"); // L2→L1
        getVideo("video_123"); // L1

        getVideo("video_999");

        getStatistics();
    }
}