import java.util.*;

/**
 * Created by arnab.ray on 23/02/18.
 */
public class MinPenaltyPath {

    private static class Node {
        final int id, w;
        Node(int id, int w) {
            this.id = id;
            this.w = w;
        }
    }

    static List<Node>[] getAdjList(int[][] edges, int n, int m) {
        List<Node>[] adjList = new LinkedList[n];
        for(int i = 0; i < n; i++) {
            adjList[i] = new LinkedList<Node>();
        }

        for(int i = 0; i < m; i++) {
            int u = edges[i][0] - 1;
            int v = edges[i][1] - 1;
            int w = edges[i][2];
            adjList[u].add(new Node(v, w));
            adjList[v].add(new Node(u, w));
        }
        return adjList;
    }


    static int beautifulPath(int[][] edges, int A, int B, int n, int m) {
        // Complete this function
        List<Node>[] adjList = getAdjList(edges, n, m);

        boolean[][] visited = new boolean[n][1024];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < 1024; j++)
                visited[i][j] = false;

        Queue<Integer> queue = new LinkedList<Integer>();
        Queue<Integer> count = new LinkedList<Integer>();

        queue.add(A-1);
        count.add(0);
        visited[A-1][0] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            int k = count.poll();
            for(Node node : adjList[u]) {
                int temp = k | node.w;
                if(!visited[node.id][temp]) {
                    visited[node.id][temp] = true;
                    queue.add(node.id);
                    count.add(temp);
                }
            }
        }

        for(int i = 0; i < 1024; i++)
            if(visited[B-1][i]) {
                return i;
            }

        return -1;
    }

    static int beautifulPathXor(int[][] edges, int A, int B, int n, int m) {
        List<Node>[] adjList = getAdjList(edges, n, m);

        boolean[][] visited = new boolean[n][1024];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < 1024; j++)
                visited[i][j] = false;

        Queue<Integer> queue = new LinkedList<Integer>();
        Queue<Integer> count = new LinkedList<Integer>();

        queue.add(A-1);
        count.add(0);
        visited[A-1][0] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            int k = count.poll();
            for(Node node : adjList[u]) {
                int temp = k ^ node.w;
                if(!visited[node.id][temp]) {
                    visited[node.id][temp] = true;
                    queue.add(node.id);
                    count.add(temp);
                }
            }
        }

        for(int i = 0; i < 1024; i++)
            if(visited[B-1][i]) {
                return i;
            }

        return -1;
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
        int A = in.nextInt();
        int B = in.nextInt();
        System.out.println(beautifulPath(edges, A, B, n, m));
        System.out.println(beautifulPathXor(edges, A, B, n, m));
        in.close();
    }
}
