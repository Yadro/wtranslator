/**
 * Created by i-am on 03.10.14.
 */
public class Lex {

    private final String code;



    public Lex(String code) {
        this.code = code;
    }

    public int parse(String code) {
        int len = code.length();

        for (int i = 0; i < len; i++) {

        }
        return 0;
    }


    private int parseKeyWord(char ch, int state) {
        switch(ch) {
            case 'i': if (state == 0) return 1; return -1;
            case 'f': if (state == 5) return 5; return -1;
            case 'l': if (state == 2) return 6; return -1;
            case 's': if (state == 6) return 9; return -1;
            case 'r':
                if (state == 0) return 3;
                if (state == 12) return 13;
                return -1;
            case 'n':
                if (state == 1) return 4;
                if (state == 13) return 14;
                return -1;
            case 't':
                if (state == 4) return 8;
                if (state == 7) return 10;
                return -1;
            case 'e':
                if (state == 0) return 2;
                if (state == 3) return 7;
                if (state == 9) return 11;
                if (state == 10) return 12;
                return -1;
        }
        return 0;
    }



}
