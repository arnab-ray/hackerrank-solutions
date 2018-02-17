import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by arnab.ray on 31/01/18.
 */
public class SmallestSubstring {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        String S = br.readLine();

        int out_ = SmallestSubString(S);
        System.out.println(out_);

        wr.close();
        br.close();
    }

    static int maxDistinctChar(String S, int n){

        int[] count = new int[256];

        for (int i = 0; i < n;  i++)
            count[S.charAt(i)]++;

        int max_distinct = 0;
        for (int i = 0; i < 256;  i++)
            if (count[i] != 0)
                max_distinct++;

        return max_distinct;
    }

    static int SmallestSubString(String S) {
        // Write your code here
        int N = S.length();
        int maxDistinct = maxDistinctChar(S, N);

        int start = 0, start_index = -1;
        int minLength = Integer.MAX_VALUE;

        int count = 0;
        int[] currCount =  new int[256];
        for (int j=0; j<N; j++)
        {
            currCount[S.charAt(j)]++;

            if (currCount[S.charAt(j)] == 1 )
                count++;

            if (count == maxDistinct)
            {
                while (currCount[S.charAt(start)] > 1)
                {
                    if (currCount[S.charAt(start)] > 1)
                        currCount[S.charAt(start)]--;
                    start++;
                }

                // Update window size
                int len_window = j - start + 1;
                if (minLength > len_window)
                {
                    minLength = len_window;
                    start_index = start;
                }
            }
        }
        // Return substring starting from start_index
        // and length min_len
        return start_index+minLength - start_index;
    }
}
