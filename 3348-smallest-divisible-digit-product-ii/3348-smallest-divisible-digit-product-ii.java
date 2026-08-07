import java.util.*;

class Solution {
    private static class Factors {
        long c2 = 0, c3 = 0, c5 = 0, c7 = 0;

        Factors() {}

        Factors(long c2, long c3, long c5, long c7) {
            this.c2 = c2;
            this.c3 = c3;
            this.c5 = c5;
            this.c7 = c7;
        }

        boolean isSatisfied() {
            return c2 <= 0 && c3 <= 0 && c5 <= 0 && c7 <= 0;
        }

        Factors countRemaining(Factors prefixFactors) {
            return new Factors(
                Math.max(0, c2 - prefixFactors.c2),
                Math.max(0, c3 - prefixFactors.c3),
                Math.max(0, c5 - prefixFactors.c5),
                Math.max(0, c7 - prefixFactors.c7)
            );
        }
    }

    private Factors getDigitFactors(char d) {
        int val = d - '0';
        Factors f = new Factors();
        if (val == 0) return f;
        while (val % 2 == 0) { f.c2++; val /= 2; }
        while (val % 3 == 0) { f.c3++; val /= 3; }
        if (val % 5 == 0) f.c5++;
        if (val % 7 == 0) f.c7++;
        return f;
    }

    // Returns the minimum number of digits needed to fulfill required factor counts
    private int minDigitsNeeded(Factors req) {
        int count7 = (int) req.c7;
        int count5 = (int) req.c5;
        int count9 = (int) (req.c3 / 2);
        int rem3 = (int) (req.c3 % 2);
        int count8 = (int) (req.c2 / 3);
        int rem2 = (int) (req.c2 % 3);

        int extra = 0;
        if (rem3 == 1 && rem2 == 1) {
            // Combine 2 and 3 into digit 6
            extra = 1;
        } else if (rem3 == 1 && rem2 == 2) {
            // Combine 3 and 2*2 into digits 6 and 2
            extra = 2;
        } else if (rem3 == 1 && rem2 == 0) {
            extra = 1; // digit 3
        } else if (rem3 == 0 && rem2 > 0) {
            extra = 1; // digit 4 (rem2=2) or digit 2 (rem2=1)
        }

        return count7 + count5 + count9 + count8 + extra;
    }

    // Constructs the smallest sorted string of length `len` that satisfies `req`
    private String constructSuffix(Factors req, int len) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < req.c7; i++) sb.append('7');
        for (int i = 0; i < req.c5; i++) sb.append('5');

        long c3 = req.c3;
        long c2 = req.c2;

        while (c3 >= 2) { sb.append('9'); c3 -= 2; }
        while (c2 >= 3) { sb.append('8'); c2 -= 3; }

        if (c3 == 1 && c2 == 1) { sb.append('6'); }
        else if (c3 == 1 && c2 == 2) { sb.append("26"); }
        else if (c3 == 1) { sb.append('3'); }
        else if (c2 == 2) { sb.append('4'); }
        else if (c2 == 1) { sb.append('2'); }

        while (sb.length() < len) {
            sb.append('1');
        }

        char[] chars = sb.toString().toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public String smallestNumber(String num, long t) {
        // Step 1: Prime factorize t
        Factors req = new Factors();
        long tempT = t;
        while (tempT % 2 == 0) { req.c2++; tempT /= 2; }
        while (tempT % 3 == 0) { req.c3++; tempT /= 3; }
        while (tempT % 5 == 0) { req.c5++; tempT /= 5; }
        while (tempT % 7 == 0) { req.c7++; tempT /= 7; }

        // t has a prime factor > 7, so it's impossible to form with single non-zero digits
        if (tempT > 1) return "-1";

        int n = num.length();

        // Step 2: Compute prefix factors for num
        Factors[] prefFactors = new Factors[n + 1];
        prefFactors[0] = new Factors();
        int firstZero = n;

        for (int i = 0; i < n; i++) {
            if (num.charAt(i) == '0') {
                firstZero = i;
                break;
            }
            Factors f = getDigitFactors(num.charAt(i));
            prefFactors[i + 1] = new Factors(
                prefFactors[i].c2 + f.c2,
                prefFactors[i].c3 + f.c3,
                prefFactors[i].c5 + f.c5,
                prefFactors[i].c7 + f.c7
            );
        }

        // Step 3: Try matching prefix of length i from min(n, firstZero) down to 0
        for (int i = Math.min(n, firstZero); i >= 0; i--) {
            Factors prefF = prefFactors[i];
            Factors remainingReq = req.countRemaining(prefF);
            int availLen = n - i;

            if (i == n) {
                if (remainingReq.isSatisfied()) {
                    return num;
                }
                continue;
            }

            // Try changing digit at index i to d > num[i]
            char startDigit = (char) (num.charAt(i) + 1);
            for (char d = startDigit; d <= '9'; d++) {
                Factors digitF = getDigitFactors(d);
                Factors currentPrefF = new Factors(
                    prefF.c2 + digitF.c2,
                    prefF.c3 + digitF.c3,
                    prefF.c5 + digitF.c5,
                    prefF.c7 + digitF.c7
                );

                Factors currRemainingReq = req.countRemaining(currentPrefF);
                int neededLen = minDigitsNeeded(currRemainingReq);

                if (neededLen <= availLen - 1) {
                    String prefix = num.substring(0, i) + d;
                    String suffix = constructSuffix(currRemainingReq, availLen - 1);
                    return prefix + suffix;
                }
            }
        }

        // Step 4: If no solution of length n exists, create solution of length n + 1 (or min length needed)
        int minLen = Math.max(n + 1, minDigitsNeeded(req));
        return constructSuffix(req, minLen);
    }
}