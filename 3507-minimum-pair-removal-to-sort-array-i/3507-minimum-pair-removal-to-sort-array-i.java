import java.util.*;

class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> arr = new ArrayList<>();
        for (int x : nums) arr.add(x);

        int count = 0;
        int n = arr.size();
        int i = 0;

        while (i < n - 1) {
            if (arr.get(i) > arr.get(i + 1)) {
                int minSum = Integer.MAX_VALUE;
                int index = 0;

                for (int j = 0; j < n - 1; j++) {
                    int sum = arr.get(j) + arr.get(j + 1);
                    if (sum < minSum) {
                        minSum = sum;
                        index = j;
                    }
                }

                arr.set(index, minSum);
                arr.remove(index + 1);

                count++;
                n--;
                i = 0; // restart from beginning
            } else {
                i++;
            }
        }

        return count;
    }
}