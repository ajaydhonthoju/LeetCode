class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((a,b) -> compare(a,b));
        for (int i: workerTimes) {
            long[] arr = {i, 0, 0}; // time multipler , nextTime, time taken so far
            pq.offer(arr);
        } 

        for (int i = 0; i < mountainHeight; i++) {
            long[] cur = pq.poll();
            cur[2] += cur[1] + cur[0];
            cur[1] += cur[0];
            pq.offer(cur);
        }
        long ans = 0;
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            ans = Math.max(ans, cur[2]);
        }
        return ans;
    }
    public int compare(long[] a, long[] b ) {
        if (a[2] + a[1] + a[0] > b[2] + b[1] + b[0]) {
            return 1;
        }
        else if (a[2] + a[1] + a[0] < b[2] + b[1] + b[0]) {
            return -1;
        }
        if (a[0] > b[0]) {
            return 1;
        }
        return -1;
    }
    
}