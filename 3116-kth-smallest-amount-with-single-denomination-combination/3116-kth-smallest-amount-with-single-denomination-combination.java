class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int minCoin = coins[0];
        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }

        long low = 1;
        long high = (long) minCoin * k;
        long answer = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (countMultiples(coins, mid) >= k) {
                answer = mid;
                high = mid - 1; // Try to find a smaller valid amount
            } else {
                low = mid + 1;
            }
        }

        return answer;
    }

    private long countMultiples(int[] coins, long m) {
        int n = coins.length;
        long count = 0;
        int totalSubsets = 1 << n;

        // Iterate through all non-empty subsets
        for (int mask = 1; mask < totalSubsets; mask++) {
            long currentLcm = 1;
            int bitCount = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bitCount++;
                    currentLcm = lcm(currentLcm, coins[i]);
                    if (currentLcm > m) {
                        break; // LCM exceeds m, contributes 0
                    }
                }
            }

            if (currentLcm <= m) {
                if (bitCount % 2 == 1) {
                    count += m / currentLcm;
                } else {
                    count -= m / currentLcm;
                }
            }
        }

        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return (a / gcd(a, b)) * b;
    }
}