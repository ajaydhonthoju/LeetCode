class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        for(int i=1;i<grid[0].length;i++){
            grid[0][i]+=grid[0][i-1];
        }
        for(int i=1;i<grid.length;i++){
            grid[i][0]+=grid[i-1][0];
        }
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(i==0||j==0)continue;
                else grid[i][j]+=grid[i-1][j]+grid[i][j-1]-grid[i-1][j-1];
            }
        }
        int ans=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]<=k)ans++;
            }
        }
        return ans;
    }
}