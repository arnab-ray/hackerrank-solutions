import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by arnab.ray on 31/01/18.
 */
public class StringCompiler {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S;
        while((S = br.readLine()) != null && S.length() != 0) {
            processString(S);
        }
    }

    static void processString(String S) {
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0; i < S.length(); i++) {
            if(i > 0 && S.charAt(i) == 'b' && S.charAt(i-1) == '\\') {
                if(stringBuffer.length() >= 2)
                    stringBuffer.setLength(stringBuffer.length() - 2);
                else
                    stringBuffer.setLength(stringBuffer.length() - 1);
            }
            else if(i > 0 && S.charAt(i) == 'n' && S.charAt(i-1) == '\\') {
                stringBuffer.setLength(stringBuffer.length() - 1);
                System.out.println(stringBuffer.toString());
                stringBuffer = new StringBuffer();
            }
            else {
                stringBuffer.append(S.charAt(i));
            }
        }
        System.out.println(stringBuffer.toString());
    }
}
