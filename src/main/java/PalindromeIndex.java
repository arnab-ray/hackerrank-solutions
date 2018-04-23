import java.util.Scanner;

/**
 * Created by arnab.ray on 01/03/18.
 */
public class PalindromeIndex {

    static int palindromeIndex(String s, int start, int end){
        // Complete this function
        if(start == end)
            return -1;
        if(start + 1 == end)
            return s.charAt(start) == s.charAt(end) ? -1 : start;
        if(s.charAt(start) == s.charAt(end))
            return palindromeIndex(s, start + 1, end - 1);
        else {
            if(palindromeIndex(s, start + 1, end) == -1)
                return start;
            else
                return end;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            String s = in.next();
            int result = palindromeIndex(s, 0, s.length() - 1);
            System.out.println(result);
        }
    }
}
