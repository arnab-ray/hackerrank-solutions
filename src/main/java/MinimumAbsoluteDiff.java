import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by arnab.ray on 24/02/18.
 */
public class MinimumAbsoluteDiff {

    static long minimumAbsoluteDifference(int n, int[] arr) {
        // Complete this function
        long diff;
        Arrays.sort(arr);
        diff = Math.abs(arr[1] - arr[0]);

        for(int i = 2; i < n; i++)
            if(Math.abs(arr[i] - arr[i-1]) < diff)
                diff = Math.abs(arr[i] - arr[i-1]);

        return diff;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int arr_i = 0; arr_i < n; arr_i++){
            arr[arr_i] = in.nextInt();
        }
        long result = minimumAbsoluteDifference(n, arr);
        System.out.println(result);
        in.close();
    }
}
