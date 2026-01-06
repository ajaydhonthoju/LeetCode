class Solution {
    public int[] twoSum(int[] a, int t) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            int x = t - a[i];
            if (m.containsKey(x)) return new int[]{m.get(x), i};
            m.put(a[i], i);
        }
        return new int[0];
    }
}