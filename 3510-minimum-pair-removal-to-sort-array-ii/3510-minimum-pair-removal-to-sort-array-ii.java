class Solution {
    static class Node {
        long value;
        Node prev, next;
        boolean removed;
        int idx;
        Node(long value, int idx) {
            this.value = value;
            this.idx = idx;
        }
    }

    static class MinSumPair {
        long sum;
        Node left, right;
        int leftIdx;

        MinSumPair(Node left, Node right) {
            this.left = left;
            this.right = right;
            this.sum = left.value + right.value;
            this.leftIdx = left.idx;
        }
    }

    private static boolean isReal(Node x) {
        return x != null && x.idx != -1;
    }

    private static int invEdge(Node a, Node b) {
        if (!isReal(a) || b == null) {
            return 0;
        }
        return a.value > b.value ? 1 : 0;
    }

    public static int minimumPairRemoval(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        Node dummy = new Node(0, -1);
        Node cur = dummy;

        for (int i = 0; i < nums.length; i++) {
            Node node = new Node(nums[i], i);
            cur.next = node;
            node.prev = cur;
            cur = node;
        }

        PriorityQueue<MinSumPair> pq = new PriorityQueue<>(
                (a, b) -> {
                    int c = Long.compare(a.sum, b.sum);
                    if (c != 0) {
                        return c;
                    }
                    return Integer.compare(a.leftIdx, b.leftIdx);
                }
        );

       
        int inv = 0;
        Node run = dummy.next;
        while (run != null && run.next != null) {
            inv += invEdge(run, run.next);
            pq.offer(new MinSumPair(run, run.next));
            run = run.next;
        }

        if (inv == 0) {
            return 0;
        }

        int ops = 0;

        while (inv > 0) {
            MinSumPair p = pq.poll();

            if (p == null) {
                break;
            }

            Node left = p.left;
            Node right = p.right;

            if (left.removed || right.removed) {
                continue;
            }
            if (left.next != right) {
                continue; 
            }

            ops++;

            Node prev = left.prev;    
            Node next = right.next;   


            inv -= invEdge(prev, left);
            inv -= invEdge(left, right);
            inv -= invEdge(right, next);

      
            Node merged = new Node(left.value + right.value, left.idx);

  
            prev.next = merged;
            merged.prev = prev;

            merged.next = next;
            if (next != null) {
                next.prev = merged;
            }

 
            left.removed = true;
            right.removed = true;

            inv += invEdge(prev, merged);
            inv += invEdge(merged, next);

            if (isReal(prev)) {
                pq.offer(new MinSumPair(prev, merged));
            }
            if (next != null) {
                pq.offer(new MinSumPair(merged, next));
            }
        }

        return ops;
    }
}