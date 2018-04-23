import java.util.Scanner;

/**
 * Created by arnab.ray on 26/03/18.
 */
public class StringGarbling {

    private static String modifyString(String str) {
        char[] input = new char[str.length() * 2];
        for(int i = 0; i < str.length(); i++)
            input[i] = str.charAt(i);

        int state = 1;
        int j = 0;

        for(int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            if(state == 1 && temp != 'a' && temp != 'b')
                input[j++] = temp;
            else if(state == 1 && temp == 'a') {
                input[j++] = temp;
                state = 2;
            }
            else if(state == 1 && temp == 'b') {
                state = 3;
                input[j++] = temp;
            }
            else if(state == 3) {

            }
            else if(state == 2 && temp != 'b') {
                input[--j] = temp;
                state = 1;
            }
            else if(state == 2) {
                input[j++] = temp;
                state = 1;
            }
        }
        return String.copyValueOf(input);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int i = 0; i < n; i++) {
            String str = in.nextLine();
            System.out.println(modifyString(str));
        }
        in.close();
    }
}
