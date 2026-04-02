class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        int[][][] dp = new int[m][n][3];
        int INF = (int)1e9;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) dp[i][j][k] = -INF;
            }
        }
        dp[0][0][0] = coins[0][0];
        if (coins[0][0] < 0) {
            dp[0][0][1] = 0;
            dp[0][0][2] = 0;
        } else {
            dp[0][0][1] = coins[0][0];
            dp[0][0][2] = coins[0][0];
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                int[] prev = new int[3];
                for (int k = 0; k < 3; k++) {
                    int top = (i > 0) ? dp[i-1][j][k] : -INF;
                    int left = (j > 0) ? dp[i][j-1][k] : -INF;
                    prev[k] = Math.max(top, left);
                }
                dp[i][j][0] = prev[0] + coins[i][j];
                if (coins[i][j] < 0) {
                    dp[i][j][1] = Math.max(prev[0], prev[1] + coins[i][j]);
                    dp[i][j][2] = Math.max(prev[1], prev[2] + coins[i][j]);
                } else {
                    dp[i][j][1] = prev[1] + coins[i][j];
                    dp[i][j][2] = prev[2] + coins[i][j];
                }
            }
        }
        return Math.max(dp[m-1][n-1][0], Math.max(dp[m-1][n-1][1], dp[m-1][n-1][2]));
    }
}