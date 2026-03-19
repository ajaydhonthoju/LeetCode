class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int result = 0;
        int[] colsX = new int[grid[0].length];
        int[] colsY = new int[grid[0].length];
        for (int y = 0; y < grid.length; y++) {
            int curX = 0;
            int curY = 0;
            for (int x = 0; x < grid[y].length; x++) {
                colsX[x] += grid[y][x] == 'X' ? 1 : 0;
                curX += colsX[x];
                colsY[x] += grid[y][x] == 'Y' ? 1 : 0;
                curY += colsY[x];
                if (curX == curY && curX > 0) {
                    result++;
                }
            }
        }
        return result;
    }
}