import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by arnab.ray on 27/02/18.
 */
public class WeightedUniformString {

    /*
     * Complete the weightedUniformStrings function below.
     */
    static String[] weightedUniformStrings(String s, int[] queries) {
        /*
         * Write your code here.
         */
        String[] result = new String[queries.length];
        Set<Integer> uniformWeight = new HashSet<Integer>();
        int prevWeight = s.charAt(0) - 'a' + 1, currentWeight;
        uniformWeight.add(prevWeight);
        for(int i = 1; i < s.length(); i++) {
            currentWeight = (s.charAt(i) == s.charAt(i-1)) ?  prevWeight + (s.charAt(i) - 'a' + 1) : s.charAt(i) - 'a' + 1;
            uniformWeight.add(currentWeight);
            prevWeight = currentWeight;
        }

        for(int i = 0; i < queries.length; i++) {
            result[i] = uniformWeight.contains(queries[i]) ? "Yes" : "No";
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();

        int queriesCount = in.nextInt();
        int[] queries = new int[queriesCount];

        for(int i = 0; i < queriesCount; i++)
            queries[i] = in.nextInt();

        String[] result = weightedUniformStrings(s, queries);

        for(int i = 0; i < queriesCount; i++)
            System.out.println(result[i]);
    }
}
