import java.util.HashMap;
import java.util.Map;

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        // Handle sign
        if ((numerator < 0) ^ (denominator < 0)) {
            sb.append("-");
        }

        // Convert to long to prevent overflow with Integer.MIN_VALUE
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // Append integer part
        sb.append(num / den);
        long remainder = num % den;

        // If cleanly divisible, return early
        if (remainder == 0) {
            return sb.toString();
        }

        sb.append(".");

        // Map remainder to the index in StringBuilder where the quotient digit is placed
        Map<Long, Integer> remainderIndexMap = new HashMap<>();

        while (remainder != 0) {
            if (remainderIndexMap.containsKey(remainder)) {
                int startIndex = remainderIndexMap.get(remainder);
                sb.insert(startIndex, "(");
                sb.append(")");
                break;
            }

            remainderIndexMap.put(remainder, sb.length());

            remainder *= 10;
            sb.append(remainder / den);
            remainder %= den;
        }

        return sb.toString();
    }
}