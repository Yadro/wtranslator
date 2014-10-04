package com.translator;

import com.translator.Lex;

/**
 * Created by i-am on 03.10.14.
 */
public class Main {

    public static void main(String args) {

        String code = "int world = 100; \n heeyyy return 101 buuugggaagggaaa\n git is cool";
        new Lex(code);

    }
}
