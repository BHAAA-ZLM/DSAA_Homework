package Week7;

import java.io.*;
import java.util.StringTokenizer;

public class Week7_A_00 {
    public static final QReader in = new QReader();
    public static final QWriter out = new QWriter();

    public static void main(String[] args) {
        int T = in.nextInt();
        for(int t = 0; t < T; t++){
            String text = in.next();
            int n = in.nextInt();
            String[] cases = new String[n];
            for(int ni = 0; ni < n; ni++){
                cases[ni] = in.next();
            }
            out.print(colour(text,cases));
        }
        out.close();
    }

    public static String colour(String text, String[] cases){
        int left = 0, right = 0;
        StringBuilder sb = new StringBuilder();
        String tmp = "";
        int count = 0;

        //Find the longest range to colour at the beginning of the text.
        for(int strIndex = 0; strIndex < cases.length; strIndex++){
            String str = cases[strIndex];
            if(str.length() > right) {
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) != text.charAt(i)) {
                        break;
                    }
                    if (i == str.length() - 1) {
                        right = str.length();
                        tmp = (strIndex + 1) + " " + "1\n";
                    }
                }
            }
        }
        if(right == 0){
            return "-1\n";
        }

        count++;
        sb.append(tmp);

        //finding a match in the coloured region
        while(right < text.length()){
            int tmpRight = 0;
            int tmpLeft = 0;
            for(int i = right; i > left; i--){
                for(int strIndex = 0; strIndex < cases.length; strIndex++){
                    String str = cases[strIndex];
                    if(str.length() + i > text.length()){
                        continue;
                    }
                    if(str.length() + i > tmpRight) {
                        for (int j = 0; j < str.length(); j++) {
                            if(j + i >= text.length()){
                                break;
                            }
                            if (str.charAt(j) != text.charAt(j + i)) {
                                break;
                            }

                            //find a match
                            if (j == str.length() - 1) {
                                tmpRight = str.length() + i;
                                tmpLeft = i;
                                tmp = (strIndex + 1) + " " + (i + 1) + "\n";
                            }
                        }
                    }
                }
            }
            //after all the str have been compared
            if(tmpRight != right){
                left = tmpLeft;
                right = tmpRight;
                sb.append(tmp);
                count++;
            }else{
                return "-1\n";
            }
        }
        if(right == text.length()){
            return count + "\n" + sb;
        }else{
            return "-1\n";
        }
    }

}

