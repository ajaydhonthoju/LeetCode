class Solution {
    public int numSpecial(int[][] mat) {
        int m = mat.length,count=0, n = mat[0].length;
        int[] row = new int[m],col = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    row[i]++;
                    col[j]++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (row[i] == 1) { 
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] == 1 && col[j] == 1) count++;                    
                }
            }
        }
        return count;
    }
}