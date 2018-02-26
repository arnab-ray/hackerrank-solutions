import java.util.Scanner;

/**
 * Created by arnab.ray on 20/02/18.
 */
public class Floyd {

    static int[] floyd(int n, int[][] edges, int[][] queries) {
        // Complete this function
        int INF = 99999;
        int m = edges.length;
        int q = queries.length;
        int[] result = new int[q];
        int[][] dist = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j)
                    dist[i][j] = 0;
                else
                    dist[i][j] =  INF;
            }
        }

        for(int i = 0; i < m; i++) {
            dist[edges[i][0]-1][edges[i][1]-1] = edges[i][2];
        }


        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(dist[i][j] > dist[i][k] + dist[k][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        for(int i = 0; i < q; i++) {
            result[i] = (dist[queries[i][0]-1][queries[i][1]-1] == INF) ? -1 : dist[queries[i][0]-1][queries[i][1]-1];
        }

        return result;
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
        int q = in.nextInt();
        int[][] queries = new int[q][2];
        for(int queries_i = 0; queries_i < q; queries_i++){
            for(int queries_j = 0; queries_j < 2; queries_j++){
                queries[queries_i][queries_j] = in.nextInt();
            }
        }
        int[] result = floyd(n, edges, queries);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? "\n" : ""));
        }
        System.out.println("");


        in.close();
    }
}
