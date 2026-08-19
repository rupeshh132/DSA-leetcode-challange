import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Map row number to a bitmask representing reserved seats (seats 2 to 9)
        Map<Integer, Integer> rowReservations = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            
            // Seats 1 and 10 do not impact any 4-person group placements
            if (col >= 2 && col <= 9) {
                rowReservations.put(row, rowReservations.getOrDefault(row, 0) | (1 << col));
            }
        }

        // Bitmasks for the three valid 4-person blocks:
        // Left:   seats 2, 3, 4, 5 -> (1<<2) | (1<<3) | (1<<4) | (1<<5) = 4 + 8 + 16 + 32 = 60
        // Middle: seats 4, 5, 6, 7 -> (1<<4) | (1<<5) | (1<<6) | (1<<7) = 16 + 32 + 64 + 128 = 240
        // Right:  seats 6, 7, 8, 9 -> (1<<6) | (1<<7) | (1<<8) | (1<<9) = 64 + 128 + 256 + 512 = 960
        final int LEFT_MASK = 60;
        final int MIDDLE_MASK = 240;
        final int RIGHT_MASK = 960;

        // Completely unreserved rows can always hold 2 groups (Left + Right)
        int totalGroups = (n - rowReservations.size()) * 2;

        for (int mask : rowReservations.values()) {
            boolean leftAvailable = (mask & LEFT_MASK) == 0;
            boolean rightAvailable = (mask & RIGHT_MASK) == 0;
            boolean middleAvailable = (mask & MIDDLE_MASK) == 0;

            if (leftAvailable && rightAvailable) {
                totalGroups += 2;
            } else if (leftAvailable || rightAvailable || middleAvailable) {
                totalGroups += 1;
            }
        }

        return totalGroups;
    }
}