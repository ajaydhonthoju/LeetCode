class Solution {
    public boolean hasAlternatingBits(int n) {
        List<Integer> bits = new ArrayList<>();
        while (n != 0) {
            bits.add(n & 1);
            n >>= 1;
        }
        for (int i = 0; i < bits.size() - 1; i++) {
            if (bits.get(i).equals(bits.get(i + 1)))
                return false;
        }
        return true;
    }
}