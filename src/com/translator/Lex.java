package com.translator;


import com.translator.exceptions.HashTableIsFull;

public class Lex {

    public static final int COUNT_TABLES = 8;

    private final String code;
    private final int length;
    private int pos = 0;
    private final Hash[] hash_table;

    public Lex(String code) {
        this.code = code;
        this.length = this.code.length();
        this.hash_table = new Hash[8];
        for (int i = 0; i < COUNT_TABLES; i++) {
            this.hash_table[i] = new Hash(this.length);
        }

        int state;
        while ((state = getNext()) != -1) {
            System.out.println(state);
            System.out.println();
        }

        for (int i = 0; i < COUNT_TABLES; i++) {
            System.out.println("\nTable " + i + ":");
            this.hash_table[i].print_table();
        }

//        parse(code);
    }

    public int getNext() {
        String substr;
        int finalState,
            finalStatePos = 0,
            lastFinalState = -1;
        int[] state = initState();

        for (int i = this.pos; i < length; i++) {
            state = whatIs(code.charAt(i), state);
            if ((finalState = finalState(state)) == -1) {
                if (lastFinalState == -1) {
                    showError(i);
                    this.pos = i + 1;
                    return 0;
                } else {
                    substr = code.substring(this.pos, finalStatePos + 1);
                    System.out.print("'" + substr + "' is ");
                    printCode(lastFinalState);
                    try {
                        this.hash_table[whenTypeOfToken(lastFinalState)].push(substr, substr);
                    } catch (HashTableIsFull e) {
                        e.printStackTrace();
                    }
                    this.pos = i;
                    return finalStatePos;
                }
            } else if (finalState > 0) {
                lastFinalState = finalState;
                finalStatePos =  i;
            }
        }
        return -1;
    }

    public void parse(String code) {

        int beginPos = 0,
            finalState = 0,
            finalStatePos = 0,
            lastFinalState = 0;
        int[] state = initState();

        for (int i = 0; i < length; i++) {
            state = whatIs(code.charAt(i), state);
            if ((finalState = finalState(state)) == -1) {

                if (lastFinalState == -1) {
                    showError(i);
                    beginPos = i+1;
                    state = initState();
                    lastFinalState = 0;
                } else {
                    System.out.print("'" + code.substring(beginPos, finalStatePos + 1) + "' is ");
                    printCode(lastFinalState);
                    System.out.println();

                    beginPos = finalStatePos + 1;
                    lastFinalState = finalState;
                    state = initState();
                    i--;
                }
            } else if (finalState > 0) {
                lastFinalState = finalState;
                finalStatePos =  i;
            }
        }
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
        if (state[1] >= 20) return state[1];      // KEYWORD   [20-23]
        if (state[0] > 0) return 1;               // ID        [1]
        if (state[2] > 0) return 2;               // INT       [2]
        if (state[3] >= 10) return  state[3];     // if        [10-15]
        if (state[4] > 0) return 30 + state[4];   // OPERATORS [31-33]
        if (state[5] > 0) return 40 + state[5];   // BRECKETS  [41-44]
        if (state[6] > 0) return 50 + state[6];   // SPLITS    [51-55]
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

    private int whenTypeOfToken(int state) {
        if (state == 1) return 0; // id
        if (state == 2) return 2; // int
        if (state < 20) return 3; // if
        if (state < 30) return 1; // keyword
        if (state < 40) return 4; // operator
        if (state < 50) return 5; // breckets
        if (state < 60) return 6; // splits
        return -1;
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
        if (state > 0 && state < 8) {
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
                System.out.print('^');
                break;
            }
            else System.out.print('-');
        }
        System.out.println();
    }

    private void printCode(int code) {
        switch (code) {
            case 1:
                System.out.println("ID");
                return;
            case 2:
                System.out.println("INT");
                return;
            case 10:
                System.out.println("==");
                return;
            case 11:
                System.out.println("!=");
                return;
            case 12:
                System.out.println("<=");
                return;
            case 13:
                System.out.println(">=");
                return;
            case 14:
                System.out.println("<");
                return;
            case 15:
                System.out.println(">");
                return;
            case 20:
                System.out.println("IF");
                return;
            case 21:
                System.out.println("INT");
                return;
            case 22:
                System.out.println("ELSE");
                return;
            case 23:
                System.out.println("RETURN");
                return;
            case 31:
                System.out.println("+");
                return;
            case 32:
                System.out.println("-");
                return;
            case 33:
                System.out.println("=");
                return;
            case 41:
            case 42:
            case 43:
            case 44:
                System.out.println("BRECKETS");
                return;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
                System.out.println("SPLITS");
                return;

        }
    }
}
