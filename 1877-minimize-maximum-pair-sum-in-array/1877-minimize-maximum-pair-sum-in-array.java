class Solution {
    public int minPairSum(int[] nums) {
      Arrays.sort(nums);
      int max=0;
      int n=nums.length-1;
      for(int i=0;i<nums.length/2;i++){
        int sum=nums[i]+nums[n-i];
        if(sum>max){
            max=sum;
        }
      }
      return max;
    }
}