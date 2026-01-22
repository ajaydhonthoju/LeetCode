import java.util.ArrayList;

class Solution {
    public int minimumPairRemoval(int[] nums) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int p = 0;
        int k = 0;

        
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                p = 1;
                break;
            }
        }
        if (p == 0) {
            return 0;
        }

        boolean pl = true;

        while (pl) {
            list.clear();

            p = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    p = 1;
                    break;
                }
            }
            if (p == 0) break;

            int v = Integer.MAX_VALUE;
            int idx = 0;

            for (int i = 0; i < nums.length - 1; i++) {
                if (v > nums[i] + nums[i + 1]) {
                    v = nums[i] + nums[i + 1];
                    idx = i;
                }
            }

            for (int i = 0; i < nums.length; i++) {
                if (i == idx) {
                    list.add(v);
                    i++; 
                } else {
                    list.add(nums[i]);
                }
            }

           
            nums = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                nums[i] = list.get(i);
            }

            k++; 

            if (nums.length <= 1) {
                pl = false;
            }
        }
        return k;
    }
}