import java.util.*;

/**
 * Created by arnab.ray on 22/02/18.
 */
public class RustNMurder {

    private static void bfsUtil(Map<Integer, HashSet<Integer>> nodeMap, int n, int s) {
        long[] distance = new long[n];
        Arrays.fill(distance, 0);
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);

        Set<Integer> notVisited = new HashSet<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        for(int i = 0; i < n; i++)
            if(i != s)
                notVisited.add(i);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            HashSet<Integer> adjNodes = nodeMap.get(u);
            for(Integer i : notVisited) {
                if(adjNodes == null || !adjNodes.contains(i)) {
                    visited.add(i);
                    distance[i] = distance[u] + 1;
                    queue.add(i);
                }
            }
            notVisited.removeAll(visited);
        }

        for(int i = 0; i < n; i++)
            if(i != s)
                System.out.print(distance[i] + " ");
        System.out.println();
    }

    private static void addNode(Map<Integer, HashSet<Integer>> nodeMap, int u, int v) {
        if(nodeMap.containsKey(u))
            nodeMap.get(u).add(v);
        else {
            HashSet<Integer> dest = new HashSet<Integer>();
            dest.add(v);
            nodeMap.put(u, dest);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int i = 0;i < T; i++) {
            int n = in.nextInt();

            Map<Integer, HashSet<Integer>> nodeMap = new HashMap<Integer, HashSet<Integer>>();

            int m = in.nextInt();
            for(int j = 0; j < m; j ++) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                addNode(nodeMap, u, v);
                addNode(nodeMap, v, u);
            }
            int s = in.nextInt() - 1;

            bfsUtil(nodeMap, n, s);
        }
    }
}
