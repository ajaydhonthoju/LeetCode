class Solution {
    public int binaryGap(int n) {
        int distance = 0, max = 0;
        boolean isOne = false;

        while (n > 0) {
            if ((n & 1) == 1) {

                if (isOne) {
                    max = Math.max(max, distance);
                    distance = 0;
                }

                isOne = true;

            }

            if (isOne) {
                distance++;
            }

            n = n >> 1;
        }
        return max;
    }
}