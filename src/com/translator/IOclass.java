package com.translator;

import java.io.*;

/**
 * Created by Will_i_am on 06.11.2014.
 */
public class IOclass {

    private String input;
    private String output;
    private PrintWriter writer;

    public IOclass(String in, String out) {
        this.input = in;
        this.output = out;
        this.writer = writeFile(this.input);
    }

    private File openFile(String name) throws FileNotFoundException {
        File file = new File(name);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
        return file;
    }

    public String read() throws FileNotFoundException {
        StringBuffer sb = new StringBuffer();

        try {
            File file = openFile(this.input);
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
            System.out.println("File " + this.input + " not found");
            System.exit(0);
            return "";
        }
    }

    public static PrintWriter writeFile(String fileName) {
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

    public void write(String text) {
        try {
            this.writer.print(text);
        } catch (Exception e){
            System.out.println("Error of write to file");
        }
    }

    public void close() {
        this.writer.close();
    }
}
