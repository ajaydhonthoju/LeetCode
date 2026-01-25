class Solution
{
    public int minimumDifference(int[] nums, int k)
    {
        Arrays.sort(nums);
        
        int min = Integer.MAX_VALUE;
        
        for(int i=k, l=nums.length; i<=l; i++)
            min = Math.min(min, nums[i-1]-nums[i-k]);
        
        return min;
    }
}