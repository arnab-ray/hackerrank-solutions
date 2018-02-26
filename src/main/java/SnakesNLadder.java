import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by arnab.ray on 19/02/18.
 */
public class SnakesNLadder {

    private static class queueEntry {
        int v;
        int w;
    }

    private static int bfsUtil(int v, boolean[] visited, int[] move) {
        Queue<queueEntry> q = new LinkedList<queueEntry>();

        queueEntry qEntry = new queueEntry();
        qEntry.v = v;
        qEntry.w = 0;

        q.add(qEntry);
        visited[v] = true;
        int weight = -1;

        while (!q.isEmpty()) {
            qEntry = q.poll();
            int node = qEntry.v;

            if(node == 99) {
                weight = qEntry.w;
                break;
            }

            for(int j = node+1; j <= (node+6) && j < 100; j++) {
                if (!visited[j]) {
                    visited[j] = true;
                    queueEntry temp = new queueEntry();
                    if(move[j] != -1)
                        temp.v = move[j];
                    else
                        temp.v = j;
                    temp.w = qEntry.w + 1;
                    q.add(temp);
                }
            }
        }

        return weight;
    }

    static int quickestWayUp(int[][] ladders, int[][] snakes) {
        // Complete this function
        boolean[] visited = new boolean[100];
        int[] move = new int[100];
        for(int i = 0; i < 100; i++)
            move[i] = -1;
        for(int[] aladder : ladders)
            move[aladder[0]-1] = aladder[1]-1;
        for(int[] asnake: snakes)
            move[asnake[0]-1] = asnake[1]-1;

        return bfsUtil(0, visited, move);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            int[][] ladders = new int[n][2];
            for(int ladders_i = 0; ladders_i < n; ladders_i++){
                for(int ladders_j = 0; ladders_j < 2; ladders_j++){
                    ladders[ladders_i][ladders_j] = in.nextInt();
                }
            }
            int m = in.nextInt();
            int[][] snakes = new int[m][2];
            for(int snakes_i = 0; snakes_i < m; snakes_i++){
                for(int snakes_j = 0; snakes_j < 2; snakes_j++){
                    snakes[snakes_i][snakes_j] = in.nextInt();
                }
            }
            int result = quickestWayUp(ladders, snakes);
            System.out.println(result);
        }
        in.close();
    }

}
