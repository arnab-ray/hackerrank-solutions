import java.util.*;

/**
 * Created by arnab.ray on 21/02/18.
 */
public class RoadsInHackerland {

    static class Edge implements Comparable<Edge> {
        int u, v, w;
        long count;
        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
            this.count = 0;
        }

        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    private static class Node {
        int id;
        List<Edge> edgeList;

        Node(int id) {
            this.id = id;
            edgeList = new ArrayList<Edge>();
        }
    }

    private static class SetElement {
        int parent, rank;
    }

    private static int findSet(SetElement[] set, int i) {
        if (set[i].parent != i)
            set[i].parent = findSet(set, set[i].parent);
        return set[i].parent;
    }

    private static void unionSet(SetElement[] set, int x, int y) {
        int x_root = findSet(set, x);
        int y_root = findSet(set, y);

        if(set[x_root].rank > set[y_root].rank) {
            set[y_root].parent = x_root;
            set[x_root].rank++;
        }
        else if(set[x_root].rank < set[y_root].rank) {
            set[x_root].parent = y_root;
            set[y_root].rank++;
        }
        else {
            set[y_root].parent = x_root;
            set[x_root].rank++;
        }
    }

    private static Edge[] kruskalUtil(Edge[] edges, int n) {
        Edge[] resultSet = new Edge[n-1];
        SetElement[] set = new SetElement[n];
        int resultSetSize = 0;

        for(int i = 0; i < n; i++) {
            set[i] = new SetElement();
            set[i].parent = i;
            set[i].rank = -1;
        }

        Arrays.sort(edges);

        for (Edge edge : edges) {
            int u = edge.u;
            int v = edge.v;
            if (findSet(set, u) != findSet(set, v)) {
                unionSet(set, u, v);
                resultSet[resultSetSize++] = edge;
            }
        }

        return resultSet;
    }

    private static int dfsUtil(int v, boolean[] visited, Node[] nodes) {
        int total = 1;
        visited[v] = true;

        for(Edge edge : nodes[v].edgeList) {
            int child = (edge.u != v) ? edge.u : edge.v;
            if(!visited[child]) {
                edge.count = dfsUtil(child, visited, nodes);
                total += edge.count;
            }
        }

        return total;
    }

    private static Node[] getAdjList(Edge[] edges, int n) {
        Node[] node = new Node[n];
        for(int i = 0; i < n; i++)
            node[i] = new Node(i);
        for(Edge edge : edges) {
            node[edge.u].edgeList.add(edge);
            node[edge.v].edgeList.add(edge);
        }

        return node;
    }

    private static void getDistance(Edge[] edgeSet, int m, int n) {
        long[] result = new long[2*m];
        //binary output
        long carry = 0, temp;
        long[] buffer = new long[2*m];

        Node[] nodes = getAdjList(edgeSet, n);
        boolean[] visited = new boolean[n];
        dfsUtil(0, visited, nodes);

        for(Edge edge : edgeSet)
            result[edge.w] = edge.count * (n - edge.count);

        for(int i = 0; i < 2*m; i++) {
            temp = result[i];
            int j = 0;
            while(temp != 0) {
                buffer[i + j] += temp%2;
                temp /= 2;
                j++;
            }
            /*System.out.println("------Buffer---------");
            for(int k = 0; k < 2*m; k++)
                System.out.print(buffer[k]);
            System.out.println();*/
        }
        Arrays.fill(result, 0);

        for(int i = 0; i < 2*m; i++) {
            result[i] = (buffer[i] +  carry) % 2;
            carry = (buffer[i] + carry) / 2;
        }

        boolean flag = false;
        for(int i = 2*m - 1; i >= 0; i--) {
            if(result[i] == 1) {
                System.out.print(1);
                flag = true;
            }
            else if(flag && result[i] == 0)
                System.out.print(0);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        Edge[] edgeList = new Edge[m];

        for(int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int d = in.nextInt();

            Edge edge = new Edge(u-1, v-1, d);
            edgeList[i] = edge;
        }

        Edge[] edgeSet = kruskalUtil(edgeList, n);
        getDistance(edgeSet, m, n);
    }
}
