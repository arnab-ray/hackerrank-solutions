import java.util.Scanner;

/**
 * Created by arnab.ray on 27/02/18.
 */
public class HackerRankString {

    static String hackerrankInString(String s) {
        // Complete this function
        String hack = "hackerrank";
        int n = s.length();
        int m = hack.length();
        int i = 0, j = 0;
        while(i < n && j < m) {
            if(s.charAt(i) == hack.charAt(j)) {
                i++; j++;
            }
            else
                i++;
        }

        return (j < m) ? "NO" : "YES";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            String s = in.next();
            String result = hackerrankInString(s);
            System.out.println(result);
        }
        in.close();
    }
}
