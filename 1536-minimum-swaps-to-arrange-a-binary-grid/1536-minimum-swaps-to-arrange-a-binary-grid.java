class Solution {
    public int minSwaps(int[][] grid) {
        int[] endZeros = getZeros(grid);
        int n = endZeros.length;

        int steps = 0; 
        for (int i = 0; i < n; i++) {
            int need = n - i - 1;
            int j = i;
            while (j < n && endZeros[j] < need) {
                j++;
            }

            if (j == n)
                return -1; 

            steps += j - i; 
            while (j > i) {
                int temp = endZeros[j];
                endZeros[j] = endZeros[j - 1];
                endZeros[j - 1] = temp;
                j--;
            }
        }
        return steps;
    }

    int[] getZeros(int[][] arr) {
        int[] endZeros = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int zeros = 0;
            int j = arr.length - 1;
            while (j >= 0 && arr[i][j] == 0) {
                zeros++;
                j--;
            }
            endZeros[i] = zeros;
        }
        return endZeros;
    }
}