class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();

        while (columnNumber > 0) {
            // Adjust for 1-based indexing
            columnNumber--;

            int remainder = columnNumber % 26;
            sb.append((char) ('A' + remainder));

            columnNumber /= 26;
        }

        // Characters were added from least significant to most significant
        return sb.reverse().toString();
    }
}