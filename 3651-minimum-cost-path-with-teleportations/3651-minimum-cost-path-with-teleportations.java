class Solution {
    public int minCost(int[][] mat, int k) {
        int row=mat.length;
        int col=mat[0].length;

        int cost[][]=new int[row][col];
        int valmat[][]=new int[row*col][4];
        int premin[]=new int[row*col];

        fillCost(mat,cost,row,col);

        while(k-->0){
            fillVal(mat,cost,valmat,row,col);
            fillMin(valmat,premin,row,col);
            updateCost(mat,cost,valmat,premin,row,col);
            bfsUpdate2(mat,cost,row,col);
        }

        return cost[0][0];
    }
    public void bfsUpdate2(int mat[][],int cost[][],int row,int col){
        for(int j=col-2;j>=0;j--){
            cost[row-1][j]=Math.min(cost[row-1][j],cost[row-1][j+1]+mat[row-1][j+1]);
        }
        for(int i=row-2;i>=0;i--){
            cost[i][col-1]=Math.min(cost[i][col-1],cost[i+1][col-1]+mat[i+1][col-1]);
        }

        for(int i=row-2;i>=0;i--){
            for(int j=col-2;j>=0;j--){
                cost[i][j]=Math.min(cost[i][j],Math.min(mat[i][j+1]+cost[i][j+1],mat[i+1][j]+cost[i+1][j]));
            }
        }

    }
    public int bfsUpdate(int mat[][],int cost[][],int row,int col){
        Queue<int[]> queue=new LinkedList<>();
        queue.add(new int[]{0,0,0});
        int ans=cost[0][0];

        int a[];
        while(!queue.isEmpty()){
            a=queue.remove();
            ans=Math.min(ans,a[2]+cost[a[0]][a[1]]);
            if(a[0]+1<row){
                queue.add(new int[]{a[0]+1,a[1],a[2]+mat[a[0]+1][a[1]]});
                ans=Math.min(ans,a[2]+mat[a[0]+1][a[1]]+cost[a[0]+1][a[1]]);
            }
            if(a[1]+1<col){
                queue.add(new int[]{a[0],a[1]+1,a[2]+mat[a[0]][a[1]+1]});
                ans=Math.min(ans,a[2]+mat[a[0]][a[1]+1]+cost[a[0]][a[1]+1]);
            }
        }


        return ans;
    }
    public void updateCost(int mat[][],int cost[][],int valmat[][],int premin[]
    ,int row,int col){
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                cost[i][j]=Math.min(cost[i][j],calc(mat[i][j],valmat,premin));
            }
        }  
    }
    public int calc(int targ,int valmat[][],int premin[]){
        int left=0;
        

        int right=valmat.length-1;
        int mid;
        int ans=left;
        while(left<right){
            mid=left+(right-left)/2;
            if(valmat[mid][0]<=targ){
                ans=mid;
                left=mid+1;
            }
            else{
                right=mid;
            }
        }
        if(valmat[left][0]<=targ)
        ans=left;

        return premin[ans];
    }
    public void fillMin(int valmat[][],int premin[],int row,int col){
        premin[0]=valmat[0][3];

        for(int i=1;i<valmat.length;i++){
            premin[i]=Math.min(premin[i-1],valmat[i][3]);
        }
    }
    public void fillVal(int mat[][],int cost[][],int valmat[][],int row,int col){
        int idx=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                valmat[idx][0]=mat[i][j];
                valmat[idx][1]=i;
                valmat[idx][2]=j;
                valmat[idx++][3]=cost[i][j];
            }
        }
        Arrays.sort(valmat,(a,b)->a[0]-b[0]);
    }
    public void fillCost(int mat[][],int cost[][],int row,int col){
        cost[row-1][col-1]=0;
        for(int j=col-2;j>=0;j--){
            cost[row-1][j]=cost[row-1][j+1]+mat[row-1][j+1];
        }
        for(int i=row-2;i>=0;i--){
            cost[i][col-1]=cost[i+1][col-1]+mat[i+1][col-1];
        }

        for(int i=row-2;i>=0;i--){
            for(int j=col-2;j>=0;j--){
                cost[i][j]=Math.min(mat[i][j+1]+cost[i][j+1],mat[i+1][j]+cost[i+1][j]);
            }
        }
    }
}