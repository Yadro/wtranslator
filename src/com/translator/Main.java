package com.translator;

import com.translator.Lex;

public class Main {

    public static void main(String[] args) {

        String code = "int a1 = 12 heeyyy else t1hen return 101 buuugggaagggaaa\n git is cool";
        new Lex(code);

    }
}
