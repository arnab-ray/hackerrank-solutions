import java.util.Scanner;

/**
 * Created by arnab.ray on 01/03/18.
 */
public class BeautifulStr {

    static int beautifulBinaryString(String b) {
        // Complete this function
        System.out.println(b.replaceAll("010", "01"));
        return (b.length() - b.replaceAll("010", "01").length());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String b = in.next();
        int result = beautifulBinaryString(b);
        System.out.println(result);
        in.close();
    }
}
