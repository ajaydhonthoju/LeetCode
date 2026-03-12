class Solution {

    static class DisjointSet {
        int[] root;
        int[] rank;
        int connectedComponents;

        public DisjointSet(int n) {
            root = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
                rank[i] = 1;
            }
            connectedComponents = n;
        }

        public int find(int x) {
            if (root[x] != x) {
                root[x] = find(root[x]);
            }
            return root[x];
        }

        public boolean union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);

            if (rootU == rootV) {
                return false;
            } else {
                if (rank[rootU] > rank[rootV]) {
                    root[rootV] = rootU;
                } else if (rank[rootV] > rank[rootU]) {
                    root[rootU] = rootV;
                } else {
                    root[rootV] = rootU;
                    rank[rootU]++;
                }
                connectedComponents--;
                return true;
            }
        }
    }

    public int maxStability(int n, int[][] edges, int k) {
        int ans = Integer.MAX_VALUE;
        DisjointSet ds = new DisjointSet(n);

        PriorityQueue<int[]> remEdges = new PriorityQueue<>((e1, e2) -> Integer.compare(e2[2], e1[2])); 
        
        int mustEdgeCount = 0;

        for (int[] edgeData : edges) {
            int u = edgeData[0];
            int v = edgeData[1];
            int s = edgeData[2];
            int m = edgeData[3];

            if (m == 1) {
                ans = Math.min(ans, s);
                boolean unionised = ds.union(u, v);
                if (unionised) {
                    mustEdgeCount++;
                } else {
                    return -1;
                }
            } else {
                remEdges.offer(new int[]{u, v, s});
            }
        }

        if (mustEdgeCount == n - 1) {
            return ans;
        }

        int toAdd = ds.connectedComponents - 1;

        PriorityQueue<Integer> addedOptionalEdgeStrengths = new PriorityQueue<>();

        while (toAdd >= 1) {
            if (!remEdges.isEmpty()) {
                int[] currentEdgeArr = remEdges.poll();
                int u = currentEdgeArr[0];
                int v = currentEdgeArr[1];
                int s = currentEdgeArr[2];

                if (ds.union(u, v)) {
                    addedOptionalEdgeStrengths.offer(s);
                    toAdd--;
                }
            } else {
                return -1;
            }
        }

        for (int i = 0; i < k; i++) {
            if (!addedOptionalEdgeStrengths.isEmpty()) {
                int weakestStrength = addedOptionalEdgeStrengths.poll();
                ans = Math.min(ans, weakestStrength * 2);
            } else {
                break;
            }
        }
        
        if (!addedOptionalEdgeStrengths.isEmpty()) {
             ans = Math.min(ans, addedOptionalEdgeStrengths.peek());
        }
        
        return ans;
    }
}