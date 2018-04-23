import java.util.Scanner;

/**
 * Created by arnab.ray on 01/03/18.
 */
public class StringConstruction {

    static int stringConstruction(String s) {
        // Complete this function
        int[] charCount = new int[256];
        int n = s.length();
        for(int i = 0; i < n; i++) {
            charCount[s.charAt(i)]++;
        }

        int result = 0;
        for(int i = 0; i < 256; i++)
            if(charCount[i] > 0)
                result++;

        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            String s = in.next();
            int result = stringConstruction(s);
            System.out.println(result);
        }
        in.close();
    }
}
