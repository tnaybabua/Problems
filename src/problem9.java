import java.util.*;

public class problem9 {

    static class Transaction {
        int id;
        int amount;
        String merchant;
        int time; // minutes from start of day

        Transaction(int id, int amount, String merchant, int time) {
            this.id = id;
            this.amount = amount;
            this.merchant = merchant;
            this.time = time;
        }
    }

    // Classic Two-Sum
    static void findTwoSum(List<Transaction> list, int target) {

        HashMap<Integer, Transaction> map = new HashMap<>();

        for (Transaction t : list) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {
                Transaction other = map.get(complement);

                System.out.println("Pair Found: (" + other.id + ", " + t.id + ")");
            }

            map.put(t.amount, t);
        }
    }

    // Two-Sum within 1 hour
    static void twoSumTimeWindow(List<Transaction> list, int target) {

        HashMap<Integer, Transaction> map = new HashMap<>();

        for (Transaction t : list) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {
                Transaction other = map.get(complement);

                if (Math.abs(t.time - other.time) <= 60) {
                    System.out.println("Time Window Pair: (" + other.id + ", " + t.id + ")");
                }
            }

            map.put(t.amount, t);
        }
    }

    // Duplicate detection
    static void detectDuplicates(List<Transaction> list) {

        HashMap<String, List<Transaction>> map = new HashMap<>();

        for (Transaction t : list) {

            String key = t.amount + "-" + t.merchant;

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(t);
        }

        for (String key : map.keySet()) {

            List<Transaction> group = map.get(key);

            if (group.size() > 1) {

                System.out.print("Duplicate Transactions: ");

                for (Transaction t : group) {
                    System.out.print(t.id + " ");
                }

                System.out.println();
            }
        }
    }

    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction(1, 500, "StoreA", 600));
        transactions.add(new Transaction(2, 300, "StoreB", 615));
        transactions.add(new Transaction(3, 200, "StoreC", 630));
        transactions.add(new Transaction(4, 500, "StoreA", 640));

        findTwoSum(transactions, 500);

        twoSumTimeWindow(transactions, 500);

        detectDuplicates(transactions);
    }
}