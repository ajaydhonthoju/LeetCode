class Solution {
    public String makeLargestSpecial(String s) {
        int sum = 0;
        int start = 0;
        int n = s.length();
        List<String> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            sum += (s.charAt(i) == '1') ? 1 : -1;

            if (sum == 0) {
                String sub = s.substring(start + 1, i);
                String res = "1" + makeLargestSpecial(sub) + "0";
                ans.add(res);
                start = i + 1;
            }
        }

        Collections.sort(ans, Collections.reverseOrder());

        return String.join("", ans);
    }
}