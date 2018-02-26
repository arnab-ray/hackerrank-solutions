import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by arnab.ray on 23/02/18.
 */
public class KruskalMST {

    private static class Edge implements Comparable<Edge> {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    private static class SetElement {
        int root, rank;
        SetElement(int root) {
            this.root = root;
            this.rank = -1;
        }
    }

    static int findSet(SetElement[] set, int n) {
        if(set[n].root != n)
            set[n].root = findSet(set, set[n].root);
        return set[n].root;
    }

    static void unionSet(SetElement[] set, int u, int v) {
        int root_u = findSet(set, u);
        int root_v = findSet(set, v);

        if(set[root_u].rank >= set[root_v].rank) {
            set[root_v].root = root_u;
            set[root_u].rank++;
        }
        else {
            set[root_u].root = root_v;
            set[root_v].rank++;
        }
    }


    static long mst(int n, int[][] inputEdges) {
        // Complete this function
        long distance = 0;
        int edgeCount = 0;

        SetElement[] set = new SetElement[n];
        for(int i = 0; i < n; i++)
            set[i] = new SetElement(i);

        int m = inputEdges.length;

        Queue<Edge> edges = new PriorityQueue<Edge>();
        for(int i = 0; i < m; i++)
            edges.add(new Edge(inputEdges[i][0]-1, inputEdges[i][1]-1, inputEdges[i][2]));

        while(edgeCount < n - 1) {
            Edge edge = edges.poll();
            if(findSet(set, edge.u) != findSet(set, edge.v)) {
                unionSet(set, edge.u, edge.v);
                distance += edge.w;
                edgeCount++;
            }
        }

        return distance;
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
        long result = mst(n, edges);
        System.out.println(result);
        in.close();
    }
}
