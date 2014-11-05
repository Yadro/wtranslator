package com.translator;

import java.io.*;

/**
 * Created by Will_i_am on 06.11.2014.
 */
public class IOclass {

    private static String input;
    private static String output;
    private static String readText;
    private static PrintWriter writer;

    public IOclass(String in, String out) {
        input = in;
        output = out;
        readText = read();
        writer = writeFile(input);
    }

    private static File openFile(String name) throws FileNotFoundException {
        File file = new File(name);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
        return file;
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
            System.exit(0);
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

    public static void write(String text) {
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
