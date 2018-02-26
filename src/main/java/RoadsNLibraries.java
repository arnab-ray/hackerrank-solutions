import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by arnab.ray on 17/02/18.
 */
public class RoadsNLibraries {

    static long dfsUtil(int v, List<Integer> adjacentList[], boolean[] visited, long componentSize) {
        visited[v] = true;
        componentSize++;
        for(Integer node : adjacentList[v]) {
            if (!visited[node])
                componentSize = dfsUtil(node, adjacentList, visited, componentSize);
        }
        return componentSize;
    }

    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        // Complete this function
        if (c_road > c_lib)
            return ((long)n * c_lib);
        else {
            long cost = 0;

            List<Integer> adjacentList[] = new LinkedList[n];
            for(int i = 0; i < n; i++)
                adjacentList[i] = new LinkedList<Integer>();

            boolean[] visited = new boolean[n];

            int numberOfEdges = cities.length;
            for(int i = 0; i < numberOfEdges; i++) {
                adjacentList[cities[i][0]-1].add(cities[i][1]-1);
                adjacentList[cities[i][1]-1].add(cities[i][0]-1);
            }

            for(int i = 0; i < n; i++) {
                if(!visited[i]) {
                    long componentSize = dfsUtil(i, adjacentList, visited, 0);
                    cost += (((long)c_road)*(componentSize - 1)) + c_lib;
                }
            }
            return cost;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int n = in.nextInt();
            int m = in.nextInt();
            int c_lib = in.nextInt();
            int c_road = in.nextInt();
            int[][] cities = new int[m][2];
            for(int cities_i = 0; cities_i < m; cities_i++){
                for(int cities_j = 0; cities_j < 2; cities_j++){
                    cities[cities_i][cities_j] = in.nextInt();
                }
            }
            long result = roadsAndLibraries(n, c_lib, c_road, cities);
            System.out.println(result);
        }
        in.close();
    }
}
