class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int res = Integer.MAX_VALUE;

        int l = 0;
        int r = n-1;

        while(l <= r){
            int mid = l + (r-l)/2;
            if(letters[mid] > target){
                res = letters[mid];
                r = mid-1;
            }
            else{
                l = mid+1;
            }
        }

        return res == Integer.MAX_VALUE ? letters[0] : (char)res;
    }
}