import java.util.*;

public class problem6 {

    // Token Bucket class
    static class TokenBucket {
        int tokens;
        int maxTokens;
        long lastRefillTime;

        TokenBucket(int maxTokens) {
            this.maxTokens = maxTokens;
            this.tokens = maxTokens;
            this.lastRefillTime = System.currentTimeMillis();
        }

        // refill tokens every hour
        void refill() {
            long now = System.currentTimeMillis();

            if (now - lastRefillTime >= 3600000) { // 1 hour
                tokens = maxTokens;
                lastRefillTime = now;
            }
        }

        // consume a token
        boolean allowRequest() {
            refill();

            if (tokens > 0) {
                tokens--;
                return true;
            }
            return false;
        }
    }

    // clientId -> TokenBucket
    static HashMap<String, TokenBucket> clients = new HashMap<>();

    static int LIMIT = 1000;

    // check rate limit
    static void checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(LIMIT));

        TokenBucket bucket = clients.get(clientId);

        if (bucket.allowRequest()) {
            System.out.println("Allowed (" + bucket.tokens + " requests remaining)");
        } else {
            System.out.println("Denied (0 requests remaining)");
        }
    }

    // show client status
    static void getRateLimitStatus(String clientId) {

        TokenBucket bucket = clients.get(clientId);

        if (bucket == null) {
            System.out.println("Client not found");
            return;
        }

        int used = bucket.maxTokens - bucket.tokens;

        System.out.println("{used: " + used +
                ", limit: " + bucket.maxTokens +
                ", remaining: " + bucket.tokens + "}");
    }

    public static void main(String[] args) {

        checkRateLimit("abc123");
        checkRateLimit("abc123");
        checkRateLimit("abc123");

        getRateLimitStatus("abc123");
    }
}