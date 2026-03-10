import java.util.*;

public class problem8 {

    static int SIZE = 20; // parking spots

    static String[] spots = new String[SIZE];
    static long[] entryTime = new long[SIZE];

    // hash function
    static int hash(String plate) {
        return Math.abs(plate.hashCode()) % SIZE;
    }

    // park vehicle using linear probing
    static void parkVehicle(String plate) {

        int index = hash(plate);
        int probes = 0;

        while (spots[index] != null) {
            index = (index + 1) % SIZE;
            probes++;
        }

        spots[index] = plate;
        entryTime[index] = System.currentTimeMillis();

        System.out.println("Vehicle " + plate +
                " → Assigned spot #" + index +
                " (" + probes + " probes)");
    }

    // exit vehicle
    static void exitVehicle(String plate) {

        for (int i = 0; i < SIZE; i++) {

            if (plate.equals(spots[i])) {

                long duration = (System.currentTimeMillis() - entryTime[i]) / 1000;

                spots[i] = null;

                System.out.println("Vehicle " + plate +
                        " exited from spot #" + i +
                        " Duration: " + duration + " seconds");

                return;
            }
        }

        System.out.println("Vehicle not found");
    }

    // statistics
    static void getStatistics() {

        int occupied = 0;

        for (String s : spots) {
            if (s != null) occupied++;
        }

        double occupancy = (occupied * 100.0) / SIZE;

        System.out.println("Occupancy: " + occupancy + "%");
    }

    public static void main(String[] args) {

        parkVehicle("ABC-1234");
        parkVehicle("ABC-1235");
        parkVehicle("XYZ-9999");

        getStatistics();

        exitVehicle("ABC-1234");

        getStatistics();
    }
}