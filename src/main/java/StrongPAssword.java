import java.util.Scanner;

/**
 * Created by arnab.ray on 27/02/18.
 */
public class StrongPAssword {

    static int minimumNumber(int n, String password) {
        // Return the minimum number of characters to make the password strong
        String specialCharacters = "!@#$%^&*()-+";

        int digitCount = 0, lowerCaseCount = 0, upperCaseCount = 0, specialCharCount = 0;

        for(int i = 0; i < n; i++) {
            if(Character.isLowerCase(password.charAt(i)))
                lowerCaseCount++;
            if(Character.isUpperCase(password.charAt(i)))
                upperCaseCount++;
            if(Character.isDigit(password.charAt(i)))
                digitCount++;
            if(specialCharacters.contains(String.valueOf(password.charAt(i))))
                specialCharCount++;
        }

        int count = 0;
        if(lowerCaseCount == 0)
            count++;
        if(upperCaseCount == 0)
            count++;
        if(digitCount == 0)
            count++;
        if(specialCharCount == 0)
            count++;

        return Math.max(count, 6 - n);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String password = in.next();
        int answer = minimumNumber(n, password);
        System.out.println(answer);
        in.close();
    }
}
