package com.translator;

import com.translator.Lex;

public class Main {

    public static void main(String[] args) {

        String code = "int world = 100; \n heeyyy return 101 buuugggaagggaaa\n git is cool";
        new Lex(code);

    }
}
