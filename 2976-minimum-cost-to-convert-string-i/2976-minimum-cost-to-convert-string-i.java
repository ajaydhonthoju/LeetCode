class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int[][] dis = new int[26][26];
        
        for(int[] d : dis) Arrays.fill(d,Integer.MAX_VALUE);
        
        for(int i = 0; i < 26; i++) dis[i][i] = 0;

        for(int i = 0; i < original.length; i++){
            int node1 = original[i] - 'a';
            int node2 = changed[i] - 'a';
            if(cost[i] < dis[node1][node2]){
                dis[node1][node2] = cost[i];
            }
        }

        floydWarshall(dis);

        long ans = 0;

        for(int i = 0; i < source.length(); i++){
            int node1 = source.charAt(i) - 'a';
            int node2 = target.charAt(i) - 'a';

            if(dis[node1][node2] == Integer.MAX_VALUE) return -1;

            ans += dis[node1][node2];
        }
        return ans;
    }

    public void floydWarshall(int[][] dis){
        for(int k = 0; k < 26; k++){
            for(int i = 0; i < 26; i++){
                for(int j = 0; j < 26; j++){
                    if(dis[i][k] == Integer.MAX_VALUE || dis[k][j] == Integer.MAX_VALUE){
                        continue;
                    }
                    if(dis[i][j] == Integer.MAX_VALUE){
                        dis[i][j] = dis[i][k] + dis[k][j];
                    }else{
                        dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                    }
                }
            }
        }
    }
}