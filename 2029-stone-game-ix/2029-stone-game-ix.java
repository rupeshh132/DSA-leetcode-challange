class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];
        for (int stone : stones) {
            count[stone % 3]++;
        }

        int c0 = count[0];
        int c1 = count[1];
        int c2 = count[2];

        // If c0 is even, Alice needs at least one stone of both remainder 1 and 2
        if (c0 % 2 == 0) {
            return c1 >= 1 && c2 >= 1;
        }

        // If c0 is odd, Alice needs a large enough imbalance between 1s and 2s
        return Math.abs(c1 - c2) > 2;
    }
}