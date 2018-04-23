import java.util.Scanner;

/**
 * Created by arnab.ray on 27/02/18.
 */
public class CamelCase {

    static int camelcase(String s) {
        // Complete this function
        int n = s.length();
        int count = (n > 0) ? 1 : 0;
        for(int i = 0; i < s.length(); i++)
            if(Character.isUpperCase(s.charAt(i)))
                count++;
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int result = camelcase(s);
        System.out.println(result);
        in.close();
    }
}
