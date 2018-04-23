import java.util.Scanner;

/**
 * Created by arnab.ray on 01/03/18.
 */
public class LoveLetter {

    static int theLoveLetterMystery(String s, int start, int end){
        // Complete this function
        if(start == end)
            return 0;
        if(start + 1 == end) {
            if(s.charAt(start) == s.charAt(end))
                return 0;
            else
                return Math.abs(s.charAt(start) - s.charAt(end));
        }
        if(s.charAt(start) == s.charAt(end))
            return theLoveLetterMystery(s, start + 1, end - 1);
        else
            return Math.abs(s.charAt(start) - s.charAt(end)) + theLoveLetterMystery(s, start + 1, end - 1);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            String s = in.next();
            int result = theLoveLetterMystery(s, 0, s.length()-1);
            System.out.println(result);
        }
    }
}
