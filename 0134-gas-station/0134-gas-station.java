class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0;
        int currentTank = 0;
        int startIndex = 0;

        for (int i = 0; i < gas.length; i++) {
            int netGas = gas[i] - cost[i];
            totalTank += netGas;
            currentTank += netGas;

            // If tank drops below 0, cannot reach station i + 1 from startIndex
            if (currentTank < 0) {
                // Reset start station to the next station
                startIndex = i + 1;
                // Reset current tank
                currentTank = 0;
            }
        }

        // If total gas is less than total cost, completing the circuit is impossible
        return totalTank >= 0 ? startIndex : -1;
    }
}