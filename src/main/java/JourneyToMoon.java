import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by arnab.ray on 18/02/18.
 */
public class JourneyToMoon {

    static int dfsUtil(int v, List<Integer> adjList[], boolean[] visited, int componentSize) {
        visited[v] = true;
        componentSize++;
        for(Integer node : adjList[v]) {
            if(!visited[node])
                componentSize = dfsUtil(node, adjList, visited, componentSize);
        }
        return componentSize;
    }

    static long journeyToMoon(int n, int[][] astronaut) {
        // Complete this function
        long componentSize;
        boolean[] visited = new boolean[n];
        List<Integer> adjList[] = new LinkedList[n];
        for(int i = 0; i < n; i++)
            adjList[i] = new LinkedList<Integer>();

        for (int[] anAstronaut : astronaut) {
            adjList[anAstronaut[0]].add(anAstronaut[1]);
            adjList[anAstronaut[1]].add(anAstronaut[0]);
        }

        long sum  = 0;
        long result = 0;
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                componentSize = dfsUtil(i, adjList, visited, 0);
                result += sum*componentSize;
                sum += componentSize;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int p = in.nextInt();
        int[][] astronaut = new int[p][2];
        for(int astronaut_i = 0; astronaut_i < p; astronaut_i++){
            for(int astronaut_j = 0; astronaut_j < 2; astronaut_j++){
                astronaut[astronaut_i][astronaut_j] = in.nextInt();
            }
        }
        long result = journeyToMoon(n, astronaut);
        System.out.println(result);
        in.close();
    }
}
