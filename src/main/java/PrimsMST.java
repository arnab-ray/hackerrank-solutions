import java.util.*;

/**
 * Created by arnab.ray on 23/02/18.
 */
public class PrimsMST {

    private static class Node implements Comparable<Node> {
        int id, w;

        Node(int id, int w) {
            this.id = id;
            this.w = w;
        }

        public boolean equals(Object o) {
            if(o == this)
                return true;
            if(!(o instanceof Node))
                return false;
            Node n = (Node) o;
            return n.id == this.id;
        }

        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    static long primsMSTUtil(Queue<Node>[] adjList, int n, int m, int start) {
        long totalWeight = 0;
        int[] weightVal = new int[n];
        Queue<Node> heap = new PriorityQueue<Node>(n);

        for(int i = 0; i < n; i++) {
            if(i == start) {
                heap.add(new Node(start, 0));
                weightVal[i] = 0;
            }
            else {
                heap.add(new Node(i, Integer.MAX_VALUE));
                weightVal[i] = Integer.MAX_VALUE;
            }
        }

        while(!heap.isEmpty()) {
            Node u = heap.poll();
            totalWeight += u.w;
            for(Node v : adjList[u.id]) {
                if(heap.contains(v) && weightVal[v.id] > v.w) {
                    heap.remove(v);
                    weightVal[v.id] = v.w;
                    heap.add(new Node(v.id, v.w));
                }
            }
        }

        return totalWeight;
    }

    static long prims(int n, int[][] inputEdges, int start) {
        // Complete this function
        int m = inputEdges.length;
        Queue<Node>[] adjList = new PriorityQueue[n];
        for(int i = 0; i < n; i++) {
            adjList[i] = new PriorityQueue<Node>();
        }

        for(int i = 0; i < m; i++) {
            int u = inputEdges[i][0] - 1;
            int v = inputEdges[i][1] - 1;
            int w = inputEdges[i][2];
            adjList[u].add(new Node(v,w));
            adjList[v].add(new Node(u,w));
        }

        return primsMSTUtil(adjList, n, m, start - 1);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] edges = new int[m][3];
        for(int edges_i = 0; edges_i < m; edges_i++){
            for(int edges_j = 0; edges_j < 3; edges_j++){
                edges[edges_i][edges_j] = in.nextInt();
            }
        }
        int start = in.nextInt();
        long result = prims(n, edges, start);
        System.out.println(result);
        in.close();
    }

}
