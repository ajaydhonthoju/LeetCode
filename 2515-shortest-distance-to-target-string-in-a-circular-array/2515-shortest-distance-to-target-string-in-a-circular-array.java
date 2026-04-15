class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;

        int res = Integer.MAX_VALUE;

        int r = startIndex;
        for (int step = 0; step < n; step++) {
            if (words[r].equals(target)) {
                res = Math.min(res, step);
                break;
            }
            r = (r + 1) % n;
        }

        r = startIndex;
        for (int step = 0; step < n; step++) {
            if (words[r].equals(target)) {
                res = Math.min(res, step);
                break;
            }
            r = (r - 1 + n) % n;
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}