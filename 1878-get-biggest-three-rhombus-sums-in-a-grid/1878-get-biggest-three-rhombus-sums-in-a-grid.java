class Solution {
    public int calcSum(int grid [] [], int tX, int tY, int bX, int bY, int lX, int lY, int rX, int rY) {
        int sum = 0;
        int i = tX;
        int j = tY;
        while (i < lX && j > lY) {
            sum += grid[i][j];
            i++;
            j--;
        }
        i = lX;
        j = lY;
        while (i < bX && j < bY) {
            sum += grid[i][j];
            i++;
            j++;
        }
        i = bX;
        j = bY;
        while (i > rX && j < rY) {
            sum += grid[i][j];
            i--;
            j++;
        }
        i = rX;
        j = rY;
        while (i > tX && j > tY) {
            sum += grid[i][j];
            i--;
            j--;
        }
        return sum;
    }
    public int[] getBiggestThree(int[][] grid) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                // top
                int tX = i;
                int tY = j;
                // bottom
                int bX = i+2;
                int bY = j;
                // left
                int lX = i+1;
                int lY = j-1;
                // right
                int rX = i+1;
                int rY = j+1;
                while (bX < grid.length && lX < grid.length && lY >= 0 && rX < grid.length && rY < grid[0].length) {
                    pq.add(calcSum(grid, tX, tY, bX, bY, lX, lY, rX, rY));
                    bX += 2;
                    lX++;
                    lY--;
                    rX++;
                    rY++;
                }
                pq.add(grid[i][j]);
            }
        }
        HashSet<Integer> set = new LinkedHashSet<>();
        while (!pq.isEmpty() && set.size() < 3) {
            set.add(pq.poll());
        }
        int answer [] = new int[set.size()];
        int idx = 0;
        for (int ele : set) {
            answer[idx++] = ele;
        }
        return answer;
    }
}