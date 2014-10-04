/**
 * Created by i-am on 03.10.14.
 */
public class Lex {

    private final String code;



    public Lex(String code) {
        this.code = code;
        parse(code);
    }

    public int parse(String code) {
        int len = code.length();
        int lastState = 0;
        int[] state = initState();

        for (int i = 0; i < len; i++) {

            state = whatIs(code.charAt(i), state);

        }
        return 0;
    }

    private int[] initState() {
        int[] state = {0, 0, 0, 0, 0, 0};
        return state;
    }

    private int[] whatIs(char ch, int[] state) {

        state[0] = parseId(ch, state[0]);
        state[1] = parseKeyWord(ch, state[1]);
        state[2] = parseInt(ch, state[2]);
        state[3] = parseAttitude(ch, state[2]);
        state[4] = parseMethod(ch, state[4]);
        state[5] = parseMark(ch, state[5]);

        return state;
    }

    private int finalState(int[] state) {

        if (state[0] > 0) return state[0];
        if (state[1] >= 20) return state[1];
        if (state[2] > 0) return state[2];
        if (state[3] > 1000) return state[3];
        if (state[4] > 0) return state[4];
        if (state[5] > 0) return state[5];

        return 0;
    }


    private int parseKeyWord(char ch, int state) {
        switch(ch) {
            case 'i': if (state == 0) return 1; return -1;
            case 'f': if (state == 1) return 20; return -1;
            case 'l': if (state == 2) return 5; return -1;
            case 's': if (state == 5) return 7; return -1;
            case 'r':
                if (state == 0) return 6;
                if (state == 9) return 10;
                return -1;
            case 'n':
                if (state == 1) return 4;
                if (state == 10) return 23;
                return -1;
            case 't':
                if (state == 4) return 21;
                if (state == 6) return 8;
                return -1;
            case 'e':
                if (state == 0) return 2;
                if (state == 3) return 6;
                if (state == 7) return 22;
                if (state == 8) return 9;
                return -1;
        }
        return -1;
    }

    private int parseId(char ch, int state) {
        if (state == 0) {
            if (Character.isLetter(ch)) return 1;
        }
        if (state > 0 && state < 9) {
            if (Character.isLetter(ch) || Character.isDigit(ch))
                return ch + 1;
        }
        return -1;
    }

    private int parseAttitude(char ch, int state) {
        switch (ch) {
            case '=':
                if (state == 0) return 3;
                if (state == 1) return 5;
                if (state == 2) return 6;
                if (state == 3) return 7;
                if (state == 4) return 8;
                return -1;
            case '!':
                if (state == 0) return 4;
                return -1;
            case '<':
                if (state == 0) return 1;
                return -1;
            case '>':
                if (state == 0) return 2;
                return -1;

        }
        return 0;
    }

    private int parseInt(char ch, int state) {
        if (state == 0 || state == 1) {
            if (Character.isDigit(ch)) {
                return 1;
            }
        }
        return -1;
    }

    private int parseMethod(char ch, int state) {
        if (state == 0) {
            switch (ch) {
                case '+': return 1;
                case '-': return 2;
                case '=': return 3;
            }
        }
        return -1;
    }

    private int parseMark(char ch, int state) {
        if (state == 0) {
            switch (ch) {
                case '{': return 1;
                case '}': return 2;
                case '(': return 3;
                case ')': return 4;
                case ';': return 5;
            }
        }
        return -1;
    }

    private int parseS(char ch, int state) {
        if (state == 0) {
            switch (ch) {
                case '\n': return 1;
                case '\t': return 2;
                case '\r': return 3;
                case ' ': return 4;
            }
        }
        return -1;
    }


}
