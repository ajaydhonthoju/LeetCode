class Solution {
    static class Robot {
        int pos;
        int dist;
        Robot(int pos, int dist) {
            this.pos = pos;
            this.dist = dist;
        }
    }
    public int lowerBound(int[] walls, int target) {
        int start = 0, end = walls.length;
        while(start < end) {
            int mid = start + (end - start) / 2;
            if(walls[mid] < target) start = mid + 1;
            else end = mid;
        }
        return start;
    }
    public int upperBound(int[] walls, int target) {
        int start = 0, end = walls.length;
        while(start < end) {
            int mid = start + (end - start) / 2;
            if(walls[mid] <= target) start = mid + 1;
            else end = mid;
        }
        return start - 1;
    }
    public int countWallsInInterval(int[] walls, int start, int end) {
        int left = lowerBound(walls, start);
        int right= upperBound(walls, end);
        if(left < 0) left = 0;
        if(right >= walls.length) right = walls.length - 1;
        if(left > right) return 0;
        return right - left + 1;
    }
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int m = walls.length;
        if(m == 0) return 0;
        if(n == 0) return 0;
        List<Robot> robotList = new ArrayList<>();
        for(int i=0; i<n; i++) {
            robotList.add(new Robot(robots[i], distance[i]));
        }
        Collections.sort(robotList, (a,b) -> a.pos - b.pos);
        int[] sortedRobots = new int[n];
        int[] sortedDistance = new int[n];
        for(int i=0; i<n; i++) {
            sortedRobots[i] = robotList.get(i).pos;
            sortedDistance[i] = robotList.get(i).dist;
        }
        int[] sortedWalls = walls.clone();
        Arrays.sort(sortedWalls);
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = Integer.MIN_VALUE;
        for(int i=1; i<n; i++) left[i] = sortedRobots[i-1];
        right[n-1] = Integer.MAX_VALUE;
        for(int i=0; i<n-1; i++) right[i] = sortedRobots[i+1];
        long[] A = new long[n];
        long[] B = new long[n];
        for(int i=0; i<n; i++) {
            int r = sortedRobots[i];
            int d = sortedDistance[i];
            int leftStart = Math.max(r-d, left[i]+1);
            int leftEnd = r;
            A[i] = countWallsInInterval(sortedWalls, leftStart, leftEnd);
            int rightStart = r;
            int rightEnd = Math.min(r+d, right[i]-1);
            B[i] = countWallsInInterval(sortedWalls, rightStart, rightEnd);
        }
        long[] I = new long[n-1];
        for(int i=0; i<n-1; i++) {
            int r1 = sortedRobots[i];
            int d1 = sortedDistance[i];
            int rightEnd1 = Math.min(r1+d1, right[i]-1);
            int r2 = sortedRobots[i+1];
            int d2 = sortedDistance[i+1];
            int leftStart2 = Math.max(r2-d2, left[i+1] + 1);
            int start = Math.max(r1, leftStart2);
            int end = Math.min(rightEnd1, r2);
            if(start <= end) I[i] = countWallsInInterval(sortedWalls, start, end);
            else I[i] = 0;
        }
        long[][] dp = new long[n][2];
        dp[0][0] = B[0];
        dp[0][1] = A[0];
        for(int i=1; i<n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]) + B[i];
            long option1 = dp[i-1][0] + A[i] - I[i-1];
            long option2 = dp[i-1][1] + A[i];
            dp[i][1] = Math.max(option1, option2);
        }
        return (int)Math.max(dp[n-1][0], dp[n-1][1]);
    }
}