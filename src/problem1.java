import java.util.*;

public class problem1 {

    // existing usernames
    static HashMap<String, Integer> users = new HashMap<>();

    // username attempt count
    static HashMap<String, Integer> attempts = new HashMap<>();

    // check availability
    static boolean checkAvailability(String username) {

        // count attempts
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);

        if (users.containsKey(username)) {
            return false;
        }
        return true;
    }

    // suggest similar usernames
    static void suggestAlternatives(String username) {

        System.out.println("Suggestions:");
        System.out.println(username + "1");
        System.out.println(username + "2");
        System.out.println(username.replace("_", "."));
    }

    // most attempted username
    static void getMostAttempted() {

        String maxUser = "";
        int max = 0;

        for (String key : attempts.keySet()) {
            if (attempts.get(key) > max) {
                max = attempts.get(key);
                maxUser = key;
            }
        }

        System.out.println("Most attempted: " + maxUser + " (" + max + " attempts)");
    }

    public static void main(String[] args) {

        // existing users
        users.put("john", 101);
        users.put("admin", 1);
        users.put("guest", 2);

        System.out.println("john available? " + checkAvailability("john_doe"));
        System.out.println("jane_smith available? " + checkAvailability("jane_smith"));

        suggestAlternatives("john");

        getMostAttempted();
    }
}