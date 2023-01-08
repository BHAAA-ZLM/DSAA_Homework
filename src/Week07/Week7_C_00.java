package Week7;

import java.io.*;
import java.util.StringTokenizer;

public class Week7_C_00 {
    public static final QReader in = new QReader();
    public static final QWriter out = new QWriter();

    public static void main(String[] args) {
        String str = in.next();
        int[][] fsaTable = new int[str.length()][26];
        int x = 0;
        for(int i = 0; i < fsaTable.length; i++) {
            int textChar = str.charAt(i) - 97;
            for(int alphabet = 0; alphabet < 26; alphabet++){
                if(alphabet == textChar) {
                    fsaTable[i][alphabet] = i + 1;
                }else {
                    fsaTable[i][alphabet] = fsaTable[x][alphabet];
                }
                out.print(fsaTable[i][alphabet] + " ");
            }
            if(i != 0) {
                x = fsaTable[x][textChar];
            }
            out.println("");
        }
        out.close();
    }
}

