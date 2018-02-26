import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by arnab.ray on 25/02/18.
 */
public class Cakewalk {

    static long marcsCakewalk(int[] calorie) {
        // Complete this function
        long miles = 0;
        Arrays.sort(calorie);
        int n = calorie.length;

        for(int i = n-1; i >= 0; i--) {
            miles += calorie[i] * (1L << (n - i - 1));
        }

        return miles;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] calorie = new int[n];
        for(int calorie_i = 0; calorie_i < n; calorie_i++){
            calorie[calorie_i] = in.nextInt();
        }
        long result = marcsCakewalk(calorie);
        System.out.println(result);
        in.close();
    }
}
