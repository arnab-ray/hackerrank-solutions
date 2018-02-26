import java.util.*;

/**
 * Created by arnab.ray on 24/02/18.
 */
public class JackGoesToRapture {

    private static class Node implements Comparable<Node> {
        int id, w;

        Node(int id, int w) {
            this.id = id;
            this.w = w;
        }

        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    private static long djikstraUtil(List<Node>[] adjList, int n, int m) {
        Queue<Node> heap = new PriorityQueue<Node>(n);
        long[] totalCost = new long[n];
        for(int i = 0; i < n; i++)
            totalCost[i] = Long.MAX_VALUE;

        totalCost[0] = 0;
        heap.add(new Node(0, 0));

        while (!heap.isEmpty()) {
            Node node = heap.poll();
            int u = node.id;
            for(Node aNode : adjList[u]) {
                int v = aNode.id;
                int edgeWeight = aNode.w;
                long diff = edgeWeight - totalCost[u];
                diff = (diff >= 0) ? diff : 0;
                if(totalCost[u] != Long.MAX_VALUE &&  totalCost[u] + diff < totalCost[v]) {
                    totalCost[v] = totalCost[u] + diff;
                    heap.add(aNode);
                }
            }
        }

        return totalCost[n-1];
    }

    static long jackGoesToRapture(int N, int[][] edges) {
        // Complete this function
        List<Node>[] adjList = new LinkedList[N];
        for(int i = 0; i < N; i++)
            adjList[i] = new LinkedList<Node>();

        int m = edges.length;
        for(int i = 0; i < m; i ++) {
            adjList[edges[i][0] - 1].add(new Node(edges[i][1] - 1, edges[i][2]));
            adjList[edges[i][1] - 1].add(new Node(edges[i][0] - 1, edges[i][2]));
        }

        return djikstraUtil(adjList, N, m);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int E = in.nextInt();
        int[][] edges = new int[E][3];
        for(int edges_i = 0; edges_i < E; edges_i++){
            for(int edges_j = 0; edges_j < 3; edges_j++){
                edges[edges_i][edges_j] = in.nextInt();
            }
        }
        long result = jackGoesToRapture(N, edges);
        if(result == Long.MAX_VALUE)
            System.out.println("NO PATH EXISTS");
        else
            System.out.println(result);
        in.close();
    }
}
