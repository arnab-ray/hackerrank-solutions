import java.util.*;

/**
 * Created by arnab.ray on 25/02/18.
 */
public class CrabGraph {

    private static class Node {
        int id, c;

        Node(int id, int c) {
            this.id = id;
            this.c = c;
        }

        public boolean equals(Object o) {
            if(o == this)
                return true;
            if(!(o instanceof Node))
                return false;
            Node n = (Node) o;
            return n.id == this.id;
        }
    }

    private static boolean bsfUtil(List<Node>[] adjList, int n, int s, int t, int[] parent) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<Integer>();
        visited[s] = true;
        parent[s] = -1;
        queue.add(s);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for(Node node : adjList[u])
                if(!visited[node.id] && node.c > 0) {
                    visited[node.id] = true;
                    parent[node.id] = u;
                    queue.add(node.id);
                }
        }

        return visited[t];
    }

    private static void adjustEdgeWeight(List<Node> nodeList, int v, int value) {
        for(Node node: nodeList)
            if(node.id == v)
                node.c = node.c + value;
    }

    private static int getEdgeWeight(List<Node> nodeList, int v) {
        for(Node node : nodeList)
            if(node.id == v)
                return node.c;
        return 0;
    }

    private static void addEdge(List<Node>[] adjList, int u, int v, int capacity) {
        adjList[u].add(new Node(v, capacity));
        adjList[v].add(new Node(u, 0));
    }

    private static int fordFulkersonUtil(int[][] edges, int n, int m, int t) {
        int source = 0, sink = 2 * n + 1;
        int[] degree = new int[2 * n + 2];
        List<Node>[] adjList = new LinkedList[2 * n + 2];
        for(int i = 0; i < 2 * n + 2; i++)
            adjList[i] = new LinkedList<Node>();


        for(int i = 0; i < m; i++) {
            int u = edges[i][0] - 1;
            int v = edges[i][1] - 1;

            addEdge(adjList, (2 * u) + 1, (2 * v) + 2, 1);
            degree[(2 * u) + 1]++;
            addEdge(adjList, (2 * v) + 1, (2 * u) + 2, 1);
            degree[(2 * v) + 1]++;
        }

        for(int i = 0; i < n; i++) {
            int capacity = Math.min(t, degree[(2 * i) + 1]);
            addEdge(adjList, source, (2 * i) + 1, capacity);
            addEdge(adjList, (2 * i) + 2, sink, 1);
        }

        int[] parent = new int[2 * n + 2];
        int maxFlow = 0;

        while (bsfUtil(adjList, 2 * n + 2, source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for(int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, getEdgeWeight(adjList[u], v));
            }

            for(int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                adjustEdgeWeight(adjList[u], v, -pathFlow);
                adjustEdgeWeight(adjList[v], u, pathFlow);
            }
            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int C = in.nextInt();
        for(int c = 0; c < C; c++) {
            int n = in.nextInt();
            int t = in.nextInt();
            int m = in.nextInt();
            int[][] edges = new int[m][2];
            for(int i = 0; i < m; i++)
                for(int j = 0; j < 2; j++)
                    edges[i][j] = in.nextInt();

            //invoke function here
            int result = fordFulkersonUtil(edges, n, m, t);
            System.out.println(result);
        }
        in.close();
    }
}
