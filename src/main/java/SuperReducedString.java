import java.util.Scanner;

/**
 * Created by arnab.ray on 27/02/18.
 */
public class SuperReducedString {

    static String super_reduced_string(String str){
        // Complete this function
        for(int i = 1; i < str.length(); i++) {
            if(str.charAt(i-1) == str.charAt(i)) {
                str = str.substring(0, i - 1) + str.substring(i+1);
                i = 0;
            }
        }

        if(str.length() == 0)
            return "Empty String";
        else
            return str;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String result = super_reduced_string(s);
        System.out.println(result);
    }
}
