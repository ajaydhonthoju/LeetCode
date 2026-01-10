class Solution {
    int[][] memo;
    int n, m;
    public int minimumDeleteSum(String s1, String s2) {
        n = s1.length();
        m = s2.length();
        memo = new int[n][m];
        for(int i=0; i<n; i++){
            Arrays.fill(memo[i], -1);
        }
        return recurse(s1,s2, 0, 0);
    }

    int recurse(String s1, String s2, int i, int j) {
        if(i==n && j==m) {
            return 0;
        }
        if(i == n){
            int sum = 0;
            for(int k=j; k<m; k++){
                sum+=(int)s2.charAt(k);
            }
            return sum;
        }
        if(j==m){
            int sum = 0;
            for(int k=i; k<n; k++){
                sum+=(int)s1.charAt(k);
            }
            return sum;
        }
        
        if(memo[i][j]!=-1) {
            return memo[i][j];
        }

        if(s1.charAt(i)==s2.charAt(j)){
            return memo[i][j] = recurse(s1, s2, i+1, j+1);
        }
        return memo[i][j] = Math.min((int)s1.charAt(i)+recurse(s1, s2, i+1, j), (int)s2.charAt(j)+recurse(s1,s2, i, j+1));

    }
}