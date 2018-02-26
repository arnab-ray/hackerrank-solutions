import java.util.*;

/**
 * Created by arnab.ray on 20/02/18.
 */
public class SteinerTree {

    private static void printGraph(List<Node>[] adjList) {
        System.out.println("::Tree View::");
        for(int i = 0; i < adjList.length; i++) {
            System.out.print(i + "-> ");
            for(Node node : adjList[i])
                System.out.print(node.id + " ");
            System.out.println();
        }
    }

    private static class Node {
        int id;
        int w;

        Node(int id, int w) {
            this.id = id;
            this.w = w;
        }

        public boolean equals(Object o) {
            if(o == this)
                return true;
            if(!(o instanceof Node))
                return false;
            Node n = (Node) o;
            return n.id == this.id;
        }
    }

    private static boolean isPresent(int[] cities, int source, int low, int high) {
        if (low <= high) {
            int mid = low + (high - low) / 2;
            if(cities[mid] == source)
                return true;
            else if (cities[mid] > source)
                return isPresent(cities, source, low, mid - 1);
            else
                return isPresent(cities, source, mid + 1, high);
        }
        return false;
    }

    private static int createSteinerTreeUtil(List<Node>[] adjList, int[] cities) {
        int count = 0;
        for(int i = 0; i < adjList.length; i++) {
            boolean found = isPresent(cities, i, 0, cities.length - 1);
            if(!found && adjList[i].size() == 1) {
                Node nodeV = adjList[i].get(0);
                Node nodeU = new Node(i, nodeV.w);
                adjList[i].remove(0);
                adjList[nodeV.id].remove(nodeU);
            }
        }

        for(int i = 0; i < adjList.length; i++) {
            boolean found = isPresent(cities, i, 0, cities.length - 1);
            if(!found && adjList[i].size() == 1)
                count++;
        }
        return count;
    }

    private static void createSteinerTree(List<Node>[] adjList, int[] cities) {
        int count = createSteinerTreeUtil(adjList, cities);
        while(count != 0)
            count = createSteinerTreeUtil(adjList, cities);
    }

    private static long getEulereanCircuitDistance(List<Node>[] adjList) {
        long distance = 0;
        for (int i = 0; i < adjList.length; i++) {
            for (Node node : adjList[i]) {
                distance += node.w;
            }
        }
        return distance;
    }

    private static void dfsUtil(int v, List<Node>[] adjList, boolean[] visited, long[] distance, long dist) {
        visited[v] = true;
        distance[v] = dist;
        for(Node node : adjList[v]) {
            if(!visited[node.id])
                dfsUtil(node.id, adjList, visited, distance, distance[v] + node.w);
        }
    }

    private static Map<Integer, Long> getMax(long[] distance, int n) {
        Map<Integer, Long> pair = new HashMap<Integer, Long>();
        long maxDistance = 0;
        int t = 0;
        for(int i = 0; i < n; i++) {
            if (distance[i] > maxDistance) {
                maxDistance = distance[i];
                t = i;
            }
        }

        pair.put(t, maxDistance);
        return pair;
    }

    private static long getDiameter(List<Node>[] adjList, int n) {
        boolean[] visited = new boolean[n];
        long[] distanceArr = new long[n];
        Arrays.fill(distanceArr, -1);

        int startIndex = n;
        for(int i = 0; i < n; i++) {
            if(adjList[i].size() >= 1) {
                startIndex = i;
                break;
            }
        }

        distanceArr[startIndex] = 0;
        dfsUtil(startIndex, adjList, visited, distanceArr, 0);

        Map<Integer, Long> pair = getMax(distanceArr, n);

        Map.Entry<Integer, Long> entry = pair.entrySet().iterator().next();
        Integer t = entry.getKey();

        Arrays.fill(distanceArr, -1);
        Arrays.fill(visited, false);
        distanceArr[t] = 0;
        dfsUtil(t, adjList, visited, distanceArr, 0);

        pair = getMax(distanceArr, n);
        entry = pair.entrySet().iterator().next();
        return entry.getValue();
    }

    private static long getDistance(List<Node>[] adjList, int[] cities, int n) {
        createSteinerTree(adjList, cities);
        long eulereanDist = getEulereanCircuitDistance(adjList);
        long diameter = getDiameter(adjList, n);
        return  eulereanDist - diameter;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();

        int[] cities = new int[k];
        List<Node>[] adjList = new LinkedList[n];
        for(int i = 0; i < n; i++)
            adjList[i] = new LinkedList<Node>();

        for(int i = 0; i < k; i++) {
            cities[i] = in.nextInt() - 1;
        }
        Arrays.sort(cities);

        for(int i = 0; i < n-1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int d = in.nextInt();

            Node nodeV = new Node(v, d);
            Node nodeU = new Node(u, d);
            adjList[u].add(nodeV);
            adjList[v].add(nodeU);
        }

        System.out.println(getDistance(adjList, cities, n));
    }
}
