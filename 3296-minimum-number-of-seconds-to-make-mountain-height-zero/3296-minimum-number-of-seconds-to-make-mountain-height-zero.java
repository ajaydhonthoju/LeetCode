class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
          long left = 0, right = Long.MAX_VALUE; 
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (canReduceHeight(mountainHeight, workerTimes, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canReduceHeight(int mountainHeight, int[] workerTimes, long timeLimit) {
        long totalReducedHeight = 0;

        for (int workerTime : workerTimes) {
            long maxHeight = 0;
            long low = 0, high = (long) 1e6;
            while (low < high) {
                long mid = low + (high - low) / 2;
                if (workerTime * (mid * (mid + 1)) / 2 <= timeLimit) {
                    maxHeight = mid;
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            totalReducedHeight += maxHeight;
            if (totalReducedHeight >= mountainHeight) return true;
        }

        return totalReducedHeight >= mountainHeight;
    }

}