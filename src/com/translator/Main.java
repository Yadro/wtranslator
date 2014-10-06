package com.translator;

import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String code = readFile("input.txt");
        new Lex(code);

    }

    public static String readFile(String fileName) throws FileNotFoundException {
        StringBuffer sb = new StringBuffer();

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                throw  new FileNotFoundException(file.getName());
            }

            try {
                BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));

                try {
                    String s;
                    while ((s = in.readLine()) != null) {
                        sb.append(s);
                        sb.append("/n");
                    }
                } finally {
                    in.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

    public static void writeFile(String fileName, String text) {
        File file = new File(fileName);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
