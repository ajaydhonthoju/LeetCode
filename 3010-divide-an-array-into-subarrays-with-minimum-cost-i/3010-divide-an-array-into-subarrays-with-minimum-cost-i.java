class Solution {
    public int minimumCost(int[] nums) {
        int i, j, k;
        i = nums[0];
        int[] nums1 = removeFirstElement(nums);
        
        Arrays.sort(nums1);
        j = nums1[0];
        k = nums1[1];

        return i+j+k;
    }

    public int[] removeFirstElement(int[] nums) {

        if (nums.length < 2) {
            return new int[0];
        }

        int[] nums1 = new int[nums.length - 1];

        for (int a = 1; a < nums.length; a++) {
            nums1[a - 1] = nums[a];
        }

        return nums1;
    }

}