import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by arnab.ray on 26/04/18.
 */
public class TwoStrings {

    static boolean validate(StringBuilder stringBuilder) {
        for(int i = 1; i < stringBuilder.length(); i++)
            if(stringBuilder.charAt(i) == stringBuilder.charAt(i-1))
                return false;
        return true;
    }

    static int twoCharaters(String s, int stringLength) {
        // Complete this function
        Set<Character> set = new HashSet<Character>();
        int maxLen = 0;

        for(int i = 0; i < stringLength; i++)
            set.add(s.charAt(i));

        Character[] charSet = (Character[]) set.toArray(new Character[0]);

        for(int i = 0; i < charSet.length; i++) {
            for(int j = i+1; j < charSet.length; j++) {
                StringBuilder stringBuilder = new StringBuilder();
                for(char c : s.toCharArray()) {
                    if(c == charSet[i] || c == charSet[j])
                        stringBuilder.append(c);
                }
                if(validate(stringBuilder))
                    maxLen = Math.max(maxLen, stringBuilder.length());
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int l = in.nextInt();
        String s = in.next();
        int result = twoCharaters(s, l);
        System.out.println(result);
        in.close();
    }

}
