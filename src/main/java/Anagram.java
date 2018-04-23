import java.util.Scanner;

/**
 * Created by arnab.ray on 01/03/18.
 */
public class Anagram {

    static int anagram(String s){
        // Complete this function
        int n = s.length();
        if(n % 2 == 1)
            return -1;
        else {
            int[] charCount = new int[256];
            for(int i = 0; i < n/2; i++)
                charCount[s.charAt(i)]++;
            for(int i = n/2; i < n; i++)
                if(charCount[s.charAt(i)] > 0)
                    charCount[s.charAt(i)]--;

            int result = 0;
            for(int i = 0; i < 256; i++)
                result += charCount[i];

            return result;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            String s = in.next();
            int result = anagram(s);
            System.out.println(result);
        }
    }
}
