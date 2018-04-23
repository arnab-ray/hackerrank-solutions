import java.util.Scanner;

/**
 * Created by arnab.ray on 02/03/18.
 */
public class MinCostToFillBag {

    private static int minCost(int[] cost, int n, int w) {
        int[][] dpCost = new int[n+1][w+1];

        for(int i = 0; i <= w; i++)
            dpCost[0][i] = Integer.MAX_VALUE;

        for(int i = 1; i <= n; i++)
            dpCost[i][0] = 0;

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= w; j++) {

            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int n = in.nextInt();
        int[] cost = new int[n];
        for(int i = 0; i < n; i++)
            cost[i] = in.nextInt();

        int result = minCost(cost, n, w);
        System.out.println(result);
        in.close();
    }
}
