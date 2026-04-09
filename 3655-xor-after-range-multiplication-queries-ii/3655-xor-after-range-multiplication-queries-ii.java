import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    static final int MOD = 1_000_000_007;

    public int pow(long x, long n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = (res * x) % MOD;
            }
            x = (x * x) % MOD;
            n >>= 1;
        }
        return (int) res;
    }

    @SuppressWarnings("unchecked")
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int t = (int) Math.sqrt(n) + 1;
        
        List<int[]>[] groupedQueries = new ArrayList[t];
        for (int i = 1; i < t; i++) {
            groupedQueries[i] = new ArrayList<>();
        }
        
        for (int[] q : queries) {
            int k = q[2];
            if (k < t) {
                groupedQueries[k].add(q);
            } else {
                int l = q[0], r = q[1], v = q[3];
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int) (((long) nums[i] * v) % MOD);
                }
            }
        }
        
        long[] dif = new long[n + t];
        for (int k = 1; k < t; k++) {
            if (groupedQueries[k].isEmpty()) {
                continue;
            }
            
            Arrays.fill(dif, 1);
            
            for (int[] q : groupedQueries[k]) {
                int l = q[0], r = q[1], v = q[3];
                dif[l] = (dif[l] * v) % MOD;
                
                int nextBound = l + ((r - l) / k + 1) * k;
                dif[nextBound] = (dif[nextBound] * pow(v, MOD - 2)) % MOD;
            }
            
            for (int i = k; i < n; i++) {
                dif[i] = (dif[i] * dif[i - k]) % MOD;
            }
            
            for (int i = 0; i < n; i++) {
                if (dif[i] != 1) {
                    nums[i] = (int) (((long) nums[i] * dif[i]) % MOD);
                }
            }
        }
        
        int res = 0;
        for (int x : nums) {
            res ^= x;
        }
        
        return res;
    }
}