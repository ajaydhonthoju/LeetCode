class Solution {
    private static int maxOneDiffs(int[] arr) {
        int count = 0;
        int maxCount = 0;
        for (int i = 0; i < arr.length-1; i++) {
            // increment count if difference is 1
            if (arr[i+1] - arr[i] == 1) {
                count++;
            } else {
            // in case difference is more than 1, re-initialize count to 0
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        return Math.max(maxCount, count);
    }

    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        Arrays.sort(hBars);
        Arrays.sort(vBars);
        int side = Math.min(maxOneDiffs(hBars), maxOneDiffs(vBars)) + 2;
        return side * side;
    }
}