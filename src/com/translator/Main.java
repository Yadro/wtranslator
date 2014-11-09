package com.translator;

import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 2) {
            System.out.println("Write args");
            System.out.println("usage: <input file> <output file>");
            System.exit(0);
        }
        new IOclass(args[0], args[1]);
        String code = IOclass.getText();
        new Lex(code);
        IOclass.close();
    }
}
