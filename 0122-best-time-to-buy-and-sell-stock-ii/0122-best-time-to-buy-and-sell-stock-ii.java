class Solution {
    public int maxProfit(int[] prices) {
        int totalProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            // Collect profit whenever the price increases from the previous day
            if (prices[i] > prices[i - 1]) {
                totalProfit += prices[i] - prices[i - 1];
            }
        }

        return totalProfit;
    }
}