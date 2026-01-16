class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        int lenH = hFences.length;
        int lenV = vFences.length;
        int MOD = (int) 1e9 + 7;
        Arrays.sort(hFences);
        Arrays.sort(vFences);
        Set<Integer> dup = new HashSet<>();

        for(int i = -1;i < lenH;i++){
            int prev = 1;
            if(i != -1) prev = hFences[i];
            for(int j = i+1;j < lenH;j++){
                dup.add(hFences[j]-prev);
            }
            dup.add(m-prev);
        }

        long max = -1;
        for(int i = -1;i < lenV;i++){
            int prev = 1;
            if(i != -1) prev = vFences[i];
            for(int j = i+1;j < lenV;j++){
                int val = vFences[j]-prev;
                if(dup.contains(val)) max = Math.max(max,val);
            }
            if(dup.contains(n-prev)) max = Math.max(max, n-prev);
        }

        return max == -1 ? -1 : (int)((max * max) % MOD);
    }
}