class Solution {
    int len;
    int[][] pos;
    int[][][] dp;
    public int minimumDistance(String word) {
        len = word.length();
        if(len < 3) return 0;

        pos = new int[26][2];
        int x = 0;
        int y = 0;
        for(int i = 0;i < 26;i++){
            pos[i][0] = x;
            pos[i][1] = y;
            y++;
            if(y == 6){
                y = 0;
                x++;
            }
        }
        
        dp = new int[len][27][27];
        for(int[][] ele1: dp){
            for(int[] ele: ele1) Arrays.fill(ele, -1);
        }

        return calMinDistance(word.toCharArray(), 0, 26, 26);
    }

    public int calMinDistance(char[] arr, int i, int fstFin, int secFin) {
        if(i >= len) return 0;
        if(dp[i][fstFin][secFin] != -1) return dp[i][fstFin][secFin];

        int cur = arr[i]-'A';
        int fstFinger = (fstFin == 26 ? 0 : dstFinder(fstFin, cur)) + calMinDistance(arr, i+1, cur, secFin);
        int secFinger = (secFin == 26 ? 0 : dstFinder(secFin, cur)) + calMinDistance(arr, i+1, fstFin, cur);

        return dp[i][fstFin][secFin] = Math.min(fstFinger, secFinger);
    }

    public int dstFinder(int fst, int sec){
        return Math.abs(pos[fst][0] - pos[sec][0]) + Math.abs(pos[fst][1] - pos[sec][1]);
    }
}