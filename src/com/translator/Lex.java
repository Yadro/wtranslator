package com.translator;


import com.sun.org.apache.xpath.internal.operations.Bool;

public class Lex {

    private final String code;

    public Lex(String code) {
        this.code = code;
        parse(code);
    }

    public int parse(String code) {

        int len = code.length(),
            beginToken = 0,
            finalState = 0;

        Boolean checked = false;

        int[] state = initState(),
              lastState;


        for (int i = 0; i < len; i++) {

            if (!checked) {
                state = whatIs(code.charAt(i), state);
            } else checked = false;

            if ((finalState = finalState(state)) == -1)
            {
                showError(i+1);

                beginToken = i+1;
                state = initState();
            }
            else if (finalState >= 20)  // breckets or split
            {
                System.out.println("'" + code.substring(beginToken, i+1) + "' is " + finalState);
                // pushHashTable(token, finalState);
                beginToken = i+1;
                state = initState();
            }
            else if (finalState != 0)
            {
                lastState = state;
                state[5] = 0;
                state[6] = 0;
                state = whatIs(code.charAt(++i), state);
                checked = true;

                if (state[5] > 0 || state[6] > 0) {
                    System.out.println("'" + code.substring(beginToken, i+1) + "' is " + lastState.toString());
                    // pushHashTable(token, finalState);
                    state = initState();
                    beginToken = i+1;
                }
            }

        }
        return 0;
    }

    private int[] initState() {
        int[] state = {0, 0, 0, 0, 0, 0, 0};
        return state;
    }

    private int[] whatIs(char ch, int[] state) {

        state[0] = parseId(ch, state[0]);
        state[1] = parseKeyWord(ch, state[1]);
        state[2] = parseInt(ch, state[2]);
        state[3] = parseIf(ch, state[2]);
        state[4] = parseOperator(ch, state[4]);
        state[5] = parseMark(ch, state[5]);
        state[6] = parseSplit(ch, state[6]);

        return state;
    }

    private int finalState(int[] state) {

        if (state[1] >= 20) return state[1]; // KEYWORD
        if (state[0] > 0) return state[0]; // ID
        if (state[2] > 0) return state[2]; // INT
        if (state[3] >= 10) return state[3]; // if
        if (state[4] > 0) return state[4]; // OPERATORS
        if (state[5] > 0) return 30 + state[5]; // BRECKETS
        if (state[6] > 0) return 40 + state[6]; // SPLITS

        if (state[0] == -1 ||
            state[1] == -1 ||
            state[2] == -1 ||
            state[3] == -1 ||
            state[4] == -1 ||
            state[5] == -1 ||
            state[6] == -1 ) {
            return -1; // ERROR
        }

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
                return state + 1;
        }
        return -1;
    }

    private int parseIf(char ch, int state) {
        switch (ch) {
            case '=':
                if (state == 0) return 1;
                if (state == 1) return 10;
                if (state == 2) return 11;
                if (state == 14) return 12;
                if (state == 15) return 13;
                return -1;
            case '!':
                if (state == 0) return 2;
                return -1;
            case '<':
                if (state == 0) return 14;
                return -1;
            case '>':
                if (state == 0) return 15;
                return -1;

        }
        return -1;
    }

    private int parseInt(char ch, int state) {
        if (state == 0 || state == 1) {
            if (Character.isDigit(ch)) {
                return 1;
            }
        }
        return -1;
    }

    private int parseOperator(char ch, int state) {
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
            }
        }
        return -1;
    }

    private int parseSplit(char ch, int state) {
        if (state == 0) {
            switch (ch) {
                case '\n': return 1;
                case '\t': return 2;
                case '\r': return 3;
                case ' ': return 4;
                case ';': return 5;
            }
        }
        return -1;
    }

    private void showError(int pos) {
        int len = code.length();

        for (int i = pos; i >= 0; i--) {
            switch (code.charAt(i)) {
                case '\n':
                case '\t':
                case '\r':
                    for (int j = pos; j > len; j++) {
                        switch (code.charAt(j)) {
                            case '\n':
                            case '\t':
                            case '\r':
                                printErrorSubstring(i + 1, j, pos);
                                return;
                        }
                    }
                    printErrorSubstring(i + 1, len - 1, pos);
                    return;
            }
        }
        for (int j = pos; j < len; j++) {
            switch (code.charAt(j)) {
                case '\n':
                case '\t':
                case '\r':
                    printErrorSubstring(0, j, pos);
                    return;
            }
        }
        printErrorSubstring(0, len - 1, pos);
    }

    private void printErrorSubstring(int b, int e, int pos) {
        System.out.println("ERROR:");

        System.out.println(code.substring(b, e));
        for (int i = b; i < e; i++) {
            if (i == pos) {
                System.out.println("^");
                break;
            }
            else System.out.print(' ');
        }
    }
}
