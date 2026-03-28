class Solution {
    public String findTheString(int[][] lcp) {
        
        int n = lcp.length;
        char arr[] = new char[n];
        arr[0] = 'a';

        for(int i = 1; i < n; i++){
            char test = 'a';
            boolean found = false;

            for(int j = 0; j < i; j++){
                test = (char)Math.max(test, arr[j]);

                if(lcp[i][j] != 0){
                    found = true;
                    arr[i] = arr[j];
                    break;
                }
            }

            if(found)
                continue;

            arr[i] = (char)(++test);

            if(test > 'z')
                return "";
        }


        int dp[][] = new int[n+1][n+1];
        
        for(int i = n-1; i >= 0; i--){
            for(int j = n-1; j >= 0; j--){
                int val = 0;

                if(arr[i] == arr[j]){
                    val = 1 + dp[i+1][j+1];
                }
                dp[i][j] = val;
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                
                if(dp[i][j] != lcp[i][j])
                    return "";
            }
        }

        StringBuilder result = new StringBuilder();
        for(char ch : arr)
            result.append(ch);

        return result.toString();
    }
}