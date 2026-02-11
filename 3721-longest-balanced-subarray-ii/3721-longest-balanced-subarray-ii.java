import java.util.*;

class LazyTag {
    int add = 0;

    void merge(LazyTag other) {
        this.add += other.add;
    }

    boolean isEmpty() {
        return add == 0;
    }

    void clear() {
        add = 0;
    }
}

class SegmentTreeNode {
    int min, max;
    LazyTag lazy = new LazyTag();
}

class SegmentTree {
    private final int n;
    private final SegmentTreeNode[] tree;

    SegmentTree(int[] prefix) {
        this.n = prefix.length;
        tree = new SegmentTreeNode[4 * n];
        for (int i = 0; i < tree.length; i++) tree[i] = new SegmentTreeNode();
        build(prefix, 1, n, 1);
    }

    void addRange(int l, int r, int val) {
        if (l <= r) rangeAdd(l, r, val, 1, n, 1);
    }

    int findLastZero(int start) {
        if (start > n) return -1;
        return query(start, n, 0, 1, n, 1);
    }

    private void build(int[] a, int l, int r, int idx) {
        if (l == r) {
            tree[idx].min = tree[idx].max = a[l - 1];
            return;
        }
        int m = (l + r) >> 1;
        build(a, l, m, idx << 1);
        build(a, m + 1, r, idx << 1 | 1);
        pull(idx);
    }

    private void apply(int idx, int val) {
        tree[idx].min += val;
        tree[idx].max += val;
        tree[idx].lazy.add += val;
    }

    private void push(int idx) {
        if (!tree[idx].lazy.isEmpty()) {
            apply(idx << 1, tree[idx].lazy.add);
            apply(idx << 1 | 1, tree[idx].lazy.add);
            tree[idx].lazy.clear();
        }
    }

    private void pull(int idx) {
        tree[idx].min = Math.min(tree[idx << 1].min, tree[idx << 1 | 1].min);
        tree[idx].max = Math.max(tree[idx << 1].max, tree[idx << 1 | 1].max);
    }

    private void rangeAdd(int ql, int qr, int val, int l, int r, int idx) {
        if (ql <= l && r <= qr) {
            apply(idx, val);
            return;
        }
        push(idx);
        int m = (l + r) >> 1;
        if (ql <= m) rangeAdd(ql, qr, val, l, m, idx << 1);
        if (qr > m) rangeAdd(ql, qr, val, m + 1, r, idx << 1 | 1);
        pull(idx);
    }

    private int query(int ql, int qr, int target, int l, int r, int idx) {
        if (tree[idx].min > target || tree[idx].max < target) return -1;
        if (l == r) return l;
        push(idx);
        int m = (l + r) >> 1;
        if (qr > m) {
            int res = query(ql, qr, target, m + 1, r, idx << 1 | 1);
            if (res != -1) return res;
        }
        if (ql <= m) return query(ql, qr, target, l, m, idx << 1);
        return -1;
    }
}

class Solution {

    private int parity(int x) {
        return (x & 1) == 0 ? 1 : -1;
    }

    public int longestBalanced(int[] nums) {
        int n = nums.length;
        Map<Integer, Queue<Integer>> pos = new HashMap<>();

        int[] prefix = new int[n];
        prefix[0] = parity(nums[0]);
        pos.computeIfAbsent(nums[0], k -> new LinkedList<>()).add(1);

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1];
            Queue<Integer> q = pos.computeIfAbsent(nums[i], k -> new LinkedList<>());
            if (q.isEmpty()) prefix[i] += parity(nums[i]);
            q.add(i + 1);
        }

        SegmentTree st = new SegmentTree(prefix);
        int ans = 0;

        for (int l = 0; l < n; l++) {
            int r = st.findLastZero(l + ans);
            if (r != -1) ans = Math.max(ans, r - l);

            Queue<Integer> q = pos.get(nums[l]);
            q.poll();
            int next = q.isEmpty() ? n + 1 : q.peek();
            st.addRange(l + 1, next - 1, -parity(nums[l]));
        }

        return ans;
    }
}