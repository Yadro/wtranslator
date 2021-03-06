package com.translator;

import java.io.*;

public class IOclass {

    private static String input;
    private static String output;
    private static String readText;
    private static PrintWriter writer;

    public IOclass(String in, String out) {
        input = in;
        output = out;
        readText = read();
        writer = writeFile(output);
    }

    private static File openFile(String name) throws FileNotFoundException {
        try {
            File file = new File(name);
            if (!file.exists()) {
                throw new FileNotFoundException(file.getName());
            }
            return file;
        } catch (NullPointerException e) {
            throw new FileNotFoundException(name);
        }
    }

    private static String read() {
        StringBuffer sb = new StringBuffer();
        try {
            File file = openFile(input);
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
        } catch (FileNotFoundException e) {
            System.out.println("File " + input + " not found");
            return "";
        }
    }

    private static PrintWriter writeFile(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            return new PrintWriter(file.getAbsoluteFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void println(String text) {
        print(text);
        print("\n");
    }

    public static void print(String text) {
        System.out.print(text);
        try {
            writer.print(text);
        } catch (Exception e){
            System.out.println("Error of write to file");
        }
    }

    public static void close() {
        writer.close();
    }

    public static String getText() {
        return readText;
    }
}
