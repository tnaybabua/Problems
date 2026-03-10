import java.util.*;

public class problem3 {

    // DNS Entry class
    static class DNSEntry {
        String ip;
        long expiryTime;

        DNSEntry(String ip, int ttlSeconds) {
            this.ip = ip;
            this.expiryTime = System.currentTimeMillis() + (ttlSeconds * 1000);
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }

    // cache
    static HashMap<String, DNSEntry> cache = new HashMap<>();

    static int hits = 0;
    static int misses = 0;

    // simulate upstream DNS
    static String queryUpstream(String domain) {
        return "172.217.14." + new Random().nextInt(255);
    }

    // resolve domain
    static String resolve(String domain) {

        DNSEntry entry = cache.get(domain);

        if (entry != null && !entry.isExpired()) {
            hits++;
            System.out.println(domain + " → Cache HIT → " + entry.ip);
            return entry.ip;
        }

        if (entry != null && entry.isExpired()) {
            System.out.println(domain + " → Cache EXPIRED");
            cache.remove(domain);
        }

        misses++;
        String ip = queryUpstream(domain);

        cache.put(domain, new DNSEntry(ip, 5)); // TTL 5 seconds for demo

        System.out.println(domain + " → Cache MISS → " + ip);

        return ip;
    }

    // cache stats
    static void getCacheStats() {

        int total = hits + misses;
        double hitRate = (total == 0) ? 0 : (hits * 100.0 / total);

        System.out.println("Hit Rate: " + hitRate + "%");
        System.out.println("Hits: " + hits);
        System.out.println("Misses: " + misses);
    }

    public static void main(String[] args) throws Exception {

        resolve("google.com");
        resolve("google.com");

        Thread.sleep(6000); // wait for TTL to expire

        resolve("google.com");

        getCacheStats();
    }
}