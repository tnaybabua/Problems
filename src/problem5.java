import java.util.*;

public class problem5 {

    // page -> total visits
    static HashMap<String, Integer> pageViews = new HashMap<>();

    // page -> unique users
    static HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();

    // source -> visit count
    static HashMap<String, Integer> trafficSources = new HashMap<>();

    // process page view event
    static void processEvent(String url, String userId, String source) {

        // count page visits
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        // track unique visitors
        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        // count traffic source
        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }

    // display dashboard
    static void getDashboard() {

        System.out.println("Top Pages:");

        // sort pages by views
        List<Map.Entry<String, Integer>> list = new ArrayList<>(pageViews.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());

        int rank = 1;
        for (Map.Entry<String, Integer> entry : list) {

            String page = entry.getKey();
            int views = entry.getValue();
            int unique = uniqueVisitors.get(page).size();

            System.out.println(rank + ". " + page + " - " + views + " views (" + unique + " unique)");
            rank++;

            if (rank > 10) break;
        }

        System.out.println("\nTraffic Sources:");

        int total = 0;
        for (int count : trafficSources.values()) total += count;

        for (String source : trafficSources.keySet()) {
            int count = trafficSources.get(source);
            double percent = (count * 100.0) / total;

            System.out.println(source + ": " + percent + "%");
        }
    }

    public static void main(String[] args) {

        processEvent("/article/breaking-news", "user_123", "google");
        processEvent("/article/breaking-news", "user_456", "facebook");
        processEvent("/sports/championship", "user_789", "direct");
        processEvent("/article/breaking-news", "user_123", "google");
        processEvent("/sports/championship", "user_999", "google");

        getDashboard();
    }
}