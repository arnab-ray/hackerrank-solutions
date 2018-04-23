import java.util.Scanner;

/**
 * Created by arnab.ray on 27/02/18.
 */
public class MarsExploration {

    static int marsExploration(String s) {
        // Complete this function
        String sosStr = "SOS";
        int count = 0, n = s.length();
        for(int i = 0; i < n; i = i + 3) {
            for(int j = 0; j < 3; j++)
                if(s.charAt(i + j) != sosStr.charAt(j))
                    count++;
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int result = marsExploration(s);
        System.out.println(result);
        in.close();
    }
}
