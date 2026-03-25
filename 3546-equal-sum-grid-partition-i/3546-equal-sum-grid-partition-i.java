class Solution {
    public boolean canPartitionGrid(int[][] grid) {
      
        int m = grid.length;
        int n = grid[0].length;

        long[] colsum = new long[n];
        long[] rowsum = new long[m];

        long rownet = 0;
        long colnet = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowsum[i] = (rowsum[i] + grid[i][j]);
            }
            rownet += rowsum[i];
        }
        for (int col = 0; col < n; col++) {
            for (int row = 0; row < m; row++) {
                colsum[col] = (colsum[col] + grid[row][col]);
            }
            colnet += colsum[col];
        }

        long prev = 0;

        for(int i = 0; i < m - 1; i++){
            prev += rowsum[i];
            if(rownet - prev == prev)
                return true;
        }
        prev = 0;

        for(int i = 0; i < n - 1; i++){
            prev += colsum[i];
            if(colnet - prev == prev)
                return true;
        }

        return false;

    }
}