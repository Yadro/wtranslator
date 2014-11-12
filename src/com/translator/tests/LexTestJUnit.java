package com.translator.tests;

import com.translator.Hash;
import com.translator.IOclass;
import com.translator.Lex;
import com.translator.Main;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by i-am on 22.10.14.
 */
public class LexTestJUnit {

    private Lex lex;
    private int state;
    private Main main;

    @Before
    public void start() {
        new IOclass(null, "output.txt");
        main = new Main();
    }

    @Test
    public void testID() throws Exception {
        System.out.println("ID");
        String str = "ifelse return12";
        this.lex = new Lex(str, null);

        if ((state = this.lex.getNext()) != 1) throw new Exception("Fail");
        this.lex.getNext();
        if ((state = this.lex.getNext()) != 1) System.out.println("Fail");
        System.out.println();
    }

    @Test
    public void testINT() {
        System.out.println("testINT");
        this.lex = new Lex("1234567 00000", null);
        if (this.lex.getNext() != 2) System.out.println("testINT faild");
        if ((state = this.lex.getNext()) != 54) System.out.println("testID faild - split");
        if (this.lex.getNext() != 2) System.out.println("testINT faild");
        System.out.println();
    }

    @Test
    public void testIF() {
        System.out.println("testIF");
        this.lex = new Lex("== != < > <= >=", null);
        if (this.lex.getNext() != 10) System.out.println("== faild");
        this.lex.getNext();
        if (this.lex.getNext() != 11) System.out.println("!= faild");
        this.lex.getNext();
        if (this.lex.getNext() != 14) System.out.println("<  faild");
        this.lex.getNext();
        if (this.lex.getNext() != 15) System.out.println(">  faild");
        this.lex.getNext();
        if (this.lex.getNext() != 12) System.out.println("<= faild");
        this.lex.getNext();
        if (this.lex.getNext() != 13) System.out.println(">= faild");
        this.lex.getNext();
    }

    @Test
    public void testKeywords() {
        System.out.println("testKeywords");
        this.lex = new Lex("if int else return", null);
        if (this.lex.getNext() != 10) System.out.println("if faild");
        this.lex.getNext();
        if (this.lex.getNext() != 11) System.out.println("int faild");
        this.lex.getNext();
        if (this.lex.getNext() != 14) System.out.println("else  faild");
        this.lex.getNext();
        if (this.lex.getNext() != 15) System.out.println("return  faild");
        System.out.println();
    }

    @Test
    public void testOperators() {
        System.out.println("testOperators");
        this.lex = new Lex("+ - = -+ +=", null);
        if (this.lex.getNext() != 31) System.out.println("+ faild");
        this.lex.getNext();
        if (this.lex.getNext() != 32) System.out.println("- faild");
        this.lex.getNext();
        if (this.lex.getNext() != 33) System.out.println("=  faild");
        this.lex.getNext();
        if (this.lex.getNext() != 32) System.out.println("-  faild");
        this.lex.getNext();
        if (this.lex.getNext() != 31) System.out.println("+ faild");
        this.lex.getNext();
        if (this.lex.getNext() != 31) System.out.println("+ faild");
        this.lex.getNext();
        if (this.lex.getNext() != 33) System.out.println("=  faild");
        System.out.println();
    }

    @Test
    public void testBreckets() {
        this.lex = new Lex(" \n \t", null);
        int state;
        while ((state = this.lex.getNext()) != -1) {
            System.out.println(state);
        }
        System.out.println();
    }
}