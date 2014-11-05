package com.translator;

import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String code = readFile("input.txt");
        new Lex(code);

    }

    public static File openFile(String name) throws FileNotFoundException {
        File file = new File(name);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
        return file;
    }

    public static String readFile(String fileName) throws FileNotFoundException {
        StringBuffer sb = new StringBuffer();

        File file = openFile(fileName);

        try {
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));

            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append('\n');
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
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

            PrintWriter writeFile = new PrintWriter(file.getAbsoluteFile());

            try {
                writeFile.print(text);
            } finally {
                writeFile.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
