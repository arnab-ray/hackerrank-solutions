import java.util.*;

/**
 * Created by arnab.ray on 19/02/18.
 */
public class ShortestReach2 {

    private static class Node implements Comparable<Node> {
        int v;
        int w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    private static void djikstraUtil(int n, int s, List<Node>[] adjList) {

        Queue<Node> heap = new PriorityQueue<Node>(n);
        int[] dist = new int[n];
        for(int i = 0; i < n; i++)
            dist[i] = Integer.MAX_VALUE;
        dist[s] = 0;
        Node node = new Node(s, dist[s]);
        heap.add(node);

        while (!heap.isEmpty()) {
            node = heap.poll();
            int u = node.v;

            for(Node aNode : adjList[u]) {
                int v = aNode.v;
                int edgeWeight = aNode.w;
                if(dist[u] != Integer.MAX_VALUE && (dist[u] + edgeWeight) < dist[v]) {
                    dist[v] = dist[u] + edgeWeight;
                    aNode.w = dist[v];
                    heap.add(aNode);
                }
            }
        }

        for(int i = 0; i < n; i++) {
            if (i != s - 1) {
                if(dist[i] == Integer.MAX_VALUE)
                    System.out.print(-1 + " ");
                else
                    System.out.print(dist[i] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            int m = in.nextInt();

            List<Node>[] adjList = new LinkedList[n];
            for(int i = 0; i < n; i++) {
                adjList[i] = new LinkedList<Node>();
            }

            for(int a1 = 0; a1 < m; a1++){
                int x = in.nextInt();
                int y = in.nextInt();
                int r = in.nextInt();

                Node nodeY = new Node(y-1, r);
                Node nodeX = new Node(x-1, r);
                //node.id = y-1;
                //node.w = r;

                adjList[x-1].add(nodeY);
                adjList[y-1].add(nodeX);
            }
            int s = in.nextInt();
            djikstraUtil(n, s-1, adjList);
        }
    }
}
