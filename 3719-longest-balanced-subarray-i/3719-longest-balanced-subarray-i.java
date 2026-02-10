class Solution {
    public int longestBalanced(int[] nums) {
        LinkedHashSet<Integer> seteven = new LinkedHashSet<>();
        LinkedHashSet<Integer> setodd = new LinkedHashSet<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= nums.length - 1; i++) {
            for (int j = i; j <= nums.length - 1; j++) {
                if (nums[j] % 2 == 0) {
                    seteven.add(nums[j]);
                } else {
                    setodd.add(nums[j]);
                }
                if (seteven.size() == setodd.size()) {
                    max = Math.max(max, j - i + 1);
                }
            }

            seteven.clear();
            setodd.clear();
        }

          if(max==Integer.MIN_VALUE)
        	max=0;
            
        return max;
    }
}