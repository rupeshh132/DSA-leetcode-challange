class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sumDiff = 0; // S_L - S_R
        int qDiff = 0;   // Q_L - Q_R

        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            int sign = (i < n / 2) ? 1 : -1;

            if (c == '?') {
                qDiff += sign;
            } else {
                sumDiff += sign * (c - '0');
            }
        }

        // If total remaining '?' difference is odd, Alice always wins
        if ((qDiff & 1) != 0) {
            return true;
        }

        // Bob wins only if the expected contributions from ? pairs perfectly offset the sum difference
        return (sumDiff * 2 + qDiff * 9) != 0;
    }
}