import java.util.Scanner;

/**
 * Created by arnab.ray on 19/02/18.
 */
public class Clique {

    private static double getM(int n, int r) {
        int ceiling = (n % r == 0) ? (n/r) : (n/r) + 1;
        int floor = n/r;
        return 0.5 * (n*n - (n%r)*(ceiling*ceiling) - (r - (n%r))*(floor*floor));
    }

    private static int cliqueSize(int n, int m, int low, int high) {
        /*int r = n - 1;
        while( r >= 0) {
            double m_r = getM(n, r);
            if (m > m_r)
                return r+1;
            else
                r--;
        }
        return r;*/
        if(low < high) {
            int r = low + (high - low) / 2;
            double m_r = getM(n, r);
            if (m >= m_r)
                return r + 1;
            else if (m < m_r)
                return cliqueSize(n, m, low, r - 1);
            else
                return cliqueSize(n, m, r + 1, high);
        }
        return m;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int i = 0; i < T; i++) {
            int N = in.nextInt();
            int M = in.nextInt();
            System.out.println(cliqueSize(N, M, 0, N));
        }
        in.close();
    }
}
