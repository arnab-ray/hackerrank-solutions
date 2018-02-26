import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by arnab.ray on 25/02/18.
 */
public class FordFulkerson {

    private static class Node {
        int id, c;

        Node(int id, int c) {
            this.id = id;
            this.c = c;
        }
    }

    private static boolean bfsUtil(List<Node>[] adjList, int n, int s, int t, int[] parent) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        while(!queue.isEmpty()) {
            int u = queue.poll();
            for(Node node : adjList[u]) {
                if(!visited[node.id] && node.c > 0) {
                    parent[node.id] = u;
                    visited[node.id] = true;
                    queue.add(node.id);
                }
            }
        }

        return visited[t];
    }

    private static int getEdgeWeight(List<Node> nodeList, int v) {
        for(Node node : nodeList)
            if(node.id == v)
                return node.c;
        return 0;
    }

    private static void adjustEdgeWeight(List<Node> nodeList, int v, int value) {
        for(Node node : nodeList)
            if(node.id == v)
                node.c = node.c + value;
    }

    private static int fordFulkerSon(List<Node>[] adjList, int n, int s, int t) {
        List<Node>[] resAdjList = new LinkedList[n];
        for(int i = 0; i < n; i++) {
            resAdjList[i] = new LinkedList<Node>();
            for(Node node : adjList[i])
                resAdjList[i].add(node);
        }

        int[] parent = new int[n];
        int maxFlow = 0;

        while(bfsUtil(resAdjList, n, s, t, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for(int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, getEdgeWeight(adjList[u], v));
            }

            for(int v = t; v != s; v = parent[v]) {
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
        int T = in.nextInt();
        for(int t = 0; t < T; t++) {
            int n = in.nextInt();

            List<Node>[] adjList = new LinkedList[n];
            for(int i = 0; i < n; i++)
                adjList[i] = new LinkedList<Node>();

            int m = in.nextInt();
            for(int i = 0; i < m; i++) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                int c = in.nextInt();
                adjList[u].add(new Node(v, c));
            }

            int source = in.nextInt() - 1;
            int sink = in.nextInt() - 1;
            //invoke function here
            int result = fordFulkerSon(adjList, n, source, sink);
            System.out.println(result);
        }
        in.close();
    }
}
