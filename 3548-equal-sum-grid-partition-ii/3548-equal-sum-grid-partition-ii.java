class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        long[] vert = new long[n];
        HashMap<Integer,Integer>[] vertMap = new HashMap[n];
        for(int i = 0; i < n; i++){
            vertMap[i] = new HashMap<Integer,Integer>();
            long sum = 0;
            for(int j = 0; j < m; j++){
                if(!vertMap[i].containsKey(grid[i][j])){
                    vertMap[i].put(grid[i][j],0);
                }
                vertMap[i].put(grid[i][j],vertMap[i].get(grid[i][j])+1);
                sum+=grid[i][j];
            }
            vert[i] = sum;
        }
        long[] hor = new long[m];
        HashMap<Integer,Integer>[] horMap = new HashMap[m];
        HashMap<Integer,Integer> totalMap = new HashMap<Integer,Integer>();
        HashMap<Integer, Integer> copy = new HashMap<>();
        long total = 0;
        for(int j = 0; j < m; j++){
            horMap[j] = new HashMap<Integer,Integer>();
            long sum = 0;
            for(int i = 0; i < n; i++){
                if(!horMap[j].containsKey(grid[i][j])){
                    horMap[j].put(grid[i][j],0);
                }
                if(!totalMap.containsKey(grid[i][j])){
                    totalMap.put(grid[i][j],0);
                }
                horMap[j].put(grid[i][j],horMap[j].get(grid[i][j])+1);
                totalMap.put(grid[i][j],totalMap.get(grid[i][j])+1);
                sum+=grid[i][j];
            }
            hor[j] = sum;
            total+=sum;
        }
        copy.putAll(totalMap);
        if(helper(vert,n,total,vertMap,totalMap,grid)){
            System.out.println("veritical");
            return true;
        }
        if(helper(hor,m,total,horMap,copy,rotate(grid))){
            System.out.println("horizontal");
            return true;
        }
        return false;
    }
    public boolean helper(long[] arr, int n, long total,
                          HashMap<Integer,Integer>[] map,HashMap<Integer,Integer> totalMap,int[][] grid){
        long cur = 0;
        HashMap<Integer,Integer> left = new HashMap<Integer,Integer>();
        for(int i = 0; i < n; i++){
            long num = arr[i];
            cur+=num;
            total-=num;
            // System.out.println(cur+" "+total+" ");
            for (Map.Entry<Integer, Integer> entry : map[i].entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();
                if(!left.containsKey(key)){
                    left.put(key,0);
                }
                left.put(key,left.get(key)+value);
                totalMap.put(key,totalMap.get(key)-value);
            }
            if(cur==total){
                return true;
            }
            else if(cur<total){
                long val = total-cur;
                if(val<=Math.pow(10,5) && totalMap.containsKey((int)val) && totalMap.get((int)val)>0){
                    if(grid[0].length==1){
                        if(!(grid[0].length==1 && i<=n-3 && i>=0 && grid[i][0]!=val && grid[n-1][0]!=val)){
                            return true;
                        }
                    }
                    else {if(!(i==n-2 && totalMap.get((int)(val))==1 && grid[n-1][0]!=(int)val && (grid[n-1][grid[0].length-1]!=(int)val))){

                        // System.out.println(i+" "+val+" "+n+" "+totalMap.get((int)(val))+" "+grid[0][grid[0].length-1]+" "+grid[grid.length-1][grid[0].length-1]);
                        return true;
                    }}
                    
                }
            }
            else{
                long val = cur-total;
                if(val<=Math.pow(10,5) && left.containsKey((int)val) && left.get((int)val)>0){
                                        if(grid[0].length==1)
                    {if(!(grid[0].length==1 && i>=2 && i<n && grid[i][0]!=val && grid[0][0]!=val)){
                        return true;
                    }}

                    else {if(!(i==0 && left.get((int)(val))==1 && grid[0][0]!=(int)val && (grid[n-1][grid[0].length-1]!=(int)val))){
                        return true;
                    }}
                }
            }
        }
        return false;
    }
    public int[][] rotate(int[][] grid){
        int n = grid.length;
        int m = grid[0].length;
        int[][] rot = new int[m][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                rot[j][i] = grid[i][j];
            }
        }
        return rot;
    }
}