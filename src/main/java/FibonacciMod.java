import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by arnab.ray on 27/02/18.
 */
public class FibonacciMod {

    static BigInteger fibonacciModified(int t1, int t2, int n) {
        // Complete this function
        BigInteger[] fibArr = new BigInteger[n];
        fibArr[0] = new BigInteger(String.valueOf(t1));
        fibArr[1] = new BigInteger(String.valueOf(t2));

        for(int i = 2; i < n; i++)
            fibArr[i] = fibArr[i-2].add(fibArr[i-1].multiply(fibArr[i-1]));

        return fibArr[n-1];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t1 = in.nextInt();
        int t2 = in.nextInt();
        int n = in.nextInt();
        BigInteger result = fibonacciModified(t1, t2, n);
        System.out.println(result);
        in.close();
    }
}
