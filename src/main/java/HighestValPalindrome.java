import java.io.IOException;
import java.util.Scanner;

/**
 * Created by arnab.ray on 05/08/18.
 */
public class HighestValPalindrome {

    private static boolean isPalindrome(char[] charArr) {

        for(int i = 0; i < charArr.length/2; i++) {
            if(charArr[i] != charArr[charArr.length - i - 1])
                return false;
        }

        return true;
    }

    // Complete the highestValuePalindrome function below.
    static String highestValuePalindrome(String s, int n, int k) {
        char[] charArr = s.toCharArray();
        boolean[] modified = new boolean[s.length()];
        int pre, post, changes = 0;

        if(n % 2 == 0) {
            pre = (n / 2) - 1;
            post = (n / 2);
        } else {
            pre = (n / 2) - 1;
            post = (n / 2) + 1;
        }

        while(pre >= 0 && post < n && changes < k) {
            if(charArr[pre] == charArr[post]) {
                pre--; post++;
            } else {
                modified[pre] = true;
                changes++;
                if(charArr[pre] > charArr[post]) {
                    charArr[post++] = charArr[pre--];
                } else {
                    charArr[pre++] = charArr[post--];
                }
            }
        }

        /*for(int i = 0; i < n/2; i++) {
            if(charArr[i] != charArr[n - i - 1]) {
                modified[i] = true;
                changes++;
                if(charArr[i] > charArr[n - i - 1])
                    charArr[n - i - 1] = charArr[i];
                else
                    charArr[i] = charArr[n - i - 1];
            }

            if(changes > k)
                return "-1";
        }*/

        for(int i = 0; (k-changes) > 0 && i < n/2; i++) {
            if(charArr[i] != '9') {
                if(modified[i])
                    changes--;
                if((k - changes) > 1) {
                    charArr[i] = charArr[n - i - 1] = '9';
                    changes += 2;
                }
            }
        }

        System.out.println(changes);

        if((n % 2 == 1) && ((k - changes) > 0))
            charArr[n / 2] = '9';

        return isPalindrome(charArr) ? String.valueOf(charArr) : "-1";
    }


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();
        String s = scanner.next();

        String result = highestValuePalindrome(s, n, k);

        System.out.println(result);

        scanner.close();
    }
}
