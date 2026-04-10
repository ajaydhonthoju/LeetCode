class Solution {
    public int minimumDistance(int[] nums) {
        int n=nums.length;
        HashMap<Integer,ArrayList<Integer>> map=new HashMap<>();
        for(int i=0;i<n;i++){
            ArrayList<Integer> arr;
            if(map.containsKey(nums[i])){
                arr=map.get(nums[i]);
            }
            else{
                arr=new ArrayList<>();
            }
            arr.add(i);
            map.put(nums[i],arr);
        }
        int min=Integer.MAX_VALUE;
        for(ArrayList<Integer> arr:map.values()){
            int len=arr.size();
            if(len>=3){
                for(int i=0;i<=len-3;i++){
                    int tot=arr.get(i+2)-arr.get(i);
                    min=Math.min(min,2*tot);
                }
            }
        }
        if(min==Integer.MAX_VALUE){
            return -1;
        }
        return min;
    }
}