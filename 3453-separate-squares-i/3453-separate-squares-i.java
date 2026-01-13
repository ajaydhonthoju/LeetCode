class Solution {
    public double separateSquares(int[][] sq) {
        int n = sq.length, maxY = Integer.MIN_VALUE;
        double yi, li, sum = 0;
        for(int i=0; i<n; i++) {
            yi = sq[i][1];
            li = sq[i][2];
            maxY = Math.max(maxY, (int) (yi + li));
            sum += li * li;
        }

     
        double l = 0, r = maxY, mid, epsilon = 1e-6, s, ans = 0;
        while(l<=r) {
            mid = (r - l)/2 + l;
            s = calcSum(sq, mid);
            
         
            if(s >= sum/2) {
                ans = mid;
                r = mid - epsilon;
            } else {
                l = mid + epsilon;
            }
        }

        return ans;
    }

    double calcSum(int[][] sq, double mid) {
        double sum = 0;
        int y, l;
        for(int i=0; i<sq.length; i++) {
            y = sq[i][1];
            l = sq[i][2];
            if(y + l <= mid) {
                sum += ((double) l)*((double) l);
            } else {
                if(y < mid) {
                    sum += ((double) l) * ((double) mid - (double) y);
                }
            }
        }
        
        return sum;
    }
}