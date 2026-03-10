import java.util.*;

public class problem2 {

    // productId -> stock
    static HashMap<String, Integer> inventory = new HashMap<>();

    // waiting list (FIFO)
    static LinkedList<Integer> waitingList = new LinkedList<>();

    // check stock
    static void checkStock(String productId) {

        int stock = inventory.getOrDefault(productId, 0);
        System.out.println(productId + " → " + stock + " units available");
    }

    // purchase item
    static synchronized void purchaseItem(String productId, int userId) {

        int stock = inventory.getOrDefault(productId, 0);

        if (stock > 0) {
            stock--;
            inventory.put(productId, stock);

            System.out.println("User " + userId + " → Purchase Success, " + stock + " units remaining");
        }
        else {
            waitingList.add(userId);
            System.out.println("User " + userId + " → Added to waiting list, position #" + waitingList.size());
        }
    }

    public static void main(String[] args) {

        // initial stock
        inventory.put("IPHONE15_256GB", 5);

        checkStock("IPHONE15_256GB");

        purchaseItem("IPHONE15_256GB", 101);
        purchaseItem("IPHONE15_256GB", 102);
        purchaseItem("IPHONE15_256GB", 103);
        purchaseItem("IPHONE15_256GB", 104);
        purchaseItem("IPHONE15_256GB", 105);

        // stock finished
        purchaseItem("IPHONE15_256GB", 106);
        purchaseItem("IPHONE15_256GB", 107);

        checkStock("IPHONE15_256GB");
    }
}