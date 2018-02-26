import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by arnab.ray on 18/02/18.
 */
public class EvenTree {

    private static int dfs(int v, List<Integer> adjList[], boolean[] visited, int[] componentArray) {
        visited[v] = true;
        int componentSize = 1;
        for(Integer node : adjList[v]) {
            if(!visited[node])
                componentSize += dfs(node, adjList, visited, componentArray);
        }
        componentArray[v] = componentSize;
        return componentSize;
    }

    private static int dfsUtil(int v, List<Integer> adjList[], boolean[] visited, int n) {
        int edgeCount = 0;
        int[] componentArray = new int[n];
        dfs(v, adjList, visited, componentArray);

        for(int i = 1; i < n; i++) {
            if(componentArray[i] % 2 == 0)
                edgeCount++;
        }

        return edgeCount;
    }

    static int evenTree(int n, int m, int[][] tree) {
        // Complete this function
        boolean[] visited = new boolean[n];
        List<Integer> adjList[] = new LinkedList[n];
        for(int i = 0; i < n; i++)
            adjList[i] = new LinkedList<Integer>();

        for(int[] anTree : tree) {
            adjList[anTree[0]-1].add(anTree[1]-1);
            adjList[anTree[1]-1].add(anTree[0]-1);
        }

        return dfsUtil(0, adjList, visited, n);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] tree = new int[m][2];
        for(int tree_i = 0; tree_i < m; tree_i++){
            for(int tree_j = 0; tree_j < 2; tree_j++){
                tree[tree_i][tree_j] = in.nextInt();
            }
        }
        int result = evenTree(n, m, tree);
        System.out.println(result);
        in.close();
    }
}
