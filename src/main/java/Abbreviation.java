import java.util.Scanner;

/**
 * Created by arnab.ray on 27/02/18.
 */
public class Abbreviation {

    static String abbreviation(String a, String b) {
        // Complete this function
        int m = a.length();
        int n = b.length();

        boolean[][] isMatch = new boolean[m+1][n+1];

        isMatch[0][0] = true;
        for(int i = 1; i <= m; i++) {
            if (Character.isLowerCase(a.charAt(i - 1)))
                isMatch[i][0] = true;
            else
                break;
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(Character.isUpperCase(a.charAt(i-1))) {
                    isMatch[i][j] = ((a.charAt(i-1) == b.charAt(j-1) && isMatch[i-1][j-1]));
                }
                else {
                    isMatch[i][j] = (Character.toUpperCase(a.charAt(i - 1)) == b.charAt(j - 1) && isMatch[i-1][j-1]) ||
                            isMatch[i-1][j];
                }
            }
        }

        if(isMatch[m][n])
            return "YES";
        else
            return "NO";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            String a = in.next();
            String b = in.next();
            String result = abbreviation(a, b);
            System.out.println(result);
        }
        in.close();
    }
}
