
class Solution {
    public int concatenatedBinary(int n) {
        long result = 0;
        long MOD = 1_000_000_007;
        long bitLength = 0;

        for(int i=1; i<=n; i++) {
            if((i & (i-1)) == 0) {
                bitLength++;
            }

            result = ((result << bitLength) + i) % MOD;
        }

        return (int) result;
    }
} 