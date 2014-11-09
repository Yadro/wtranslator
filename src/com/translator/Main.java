package com.translator;

import java.io.*;

public class Main {

    public static final int COUNT_TABLES = 7;

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 2) {
            System.out.println("Write args");
            System.out.println("usage: <input file> <output file>");
            System.exit(0);
        }
        new IOclass(args[0], args[1]);
        String code = IOclass.getText();

        Lex lex = new Lex(code, create_table(code.length()));

        int state;
        while ((state = lex.getNext()) != -1) {}

        for (int i = 0; i < COUNT_TABLES; i++) {
            lex.getHashTable()[i].print_table();
        }

        IOclass.close();
    }

    public static Hash[] create_table(int len) {
        Hash[] hash_table = new Hash[8];
        hash_table[0] = new Hash("ID", len);
        hash_table[1] = new Hash("KEYWORDS", len);
        hash_table[2] = new Hash("INT", len);
        hash_table[3] = new Hash("if", len);
        hash_table[4] = new Hash("OPERATORS", len);
        hash_table[5] = new Hash("BRECKETS", len);
        hash_table[6] = new Hash("SPLITS", len);
        return  hash_table;
    }
}
