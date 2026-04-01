class Solution 
{
    public int minRemoval(int[] nums, int k) 
    {
        int n=nums.length;
        Arrays.sort(nums);
        int ans=0;
        int l=0;
        int r=0;
       for(r=0;r<n;r++)
        {
            while((long)nums[r] >(long) nums[l]*k)
               l++;
            ans=Math.max(r-l+1,ans);
        }
        return n-ans;
    }
}