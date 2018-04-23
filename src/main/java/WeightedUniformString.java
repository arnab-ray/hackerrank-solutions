import java.util.Scanner;

/**
 * Created by arnab.ray on 27/02/18.
 */
public class WeightedUniformString {

    private static String isPresent(String s, int[] countChar, int x) {
        return null;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int[] countChar = new int[256];
        for(int i = 0; i < s.length(); i++)
            countChar[s.charAt(i)]++;

        for(int i = 0; i < 256; i++)
            System.out.print(countChar[i] + " ");
        System.out.println();
        int n = in.nextInt();
        for(int a0 = 0; a0 < n; a0++){
            int x = in.nextInt();
            // your code goes here
            String result = isPresent(s, countChar, x);
        }
    }
}
