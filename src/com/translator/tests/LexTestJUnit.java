package com.translator.tests;

import com.translator.IOclass;
import com.translator.Lex;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by i-am on 22.10.14.
 */
public class LexTestJUnit {

    private Lex lex;
    private int state;

    @Before
    public void start() {
        new IOclass(null, "output.txt");
    }

    @Test
    public void testID() {
        System.out.println("ID");
        this.lex = new Lex("ifelse return12", null);
        if ((state = this.lex.getNext()) != 1) System.out.println("testID faild");
        if ((state = this.lex.getNext()) != 54) System.out.println("testID faild - split");
        if ((state = this.lex.getNext()) != 1) System.out.println("testID faild");
    }

    @Test
    public void testINT() {
        this.lex = new Lex("1234567 00000", null);
        if (this.lex.getNext() != 2) System.out.println("testINT faild");
        if ((state = this.lex.getNext()) != 54) System.out.println("testID faild - split");
        if (this.lex.getNext() != 2) System.out.println("testINT faild");
    }

    @Test
    public void testIF() {
        this.lex = new Lex("== != < > <= >=", null);
        if (this.lex.getNext() != 10) System.out.println("== faild");
        if ((state = this.lex.getNext()) != 54) System.out.println("testID faild - split");
        if (this.lex.getNext() != 11) System.out.println("!= faild");
        if ((state = this.lex.getNext()) != 54) System.out.println("testID faild - split");
        if (this.lex.getNext() != 14) System.out.println("<  faild");
        if ((state = this.lex.getNext()) != 54) System.out.println("testID faild - split");
        if (this.lex.getNext() != 15) System.out.println(">  faild");
        if ((state = this.lex.getNext()) != 54) System.out.println("testID faild - split");
        if (this.lex.getNext() != 12) System.out.println("<= faild");
        if ((state = this.lex.getNext()) != 54) System.out.println("testID faild - split");
        if (this.lex.getNext() != 13) System.out.println(">= faild");
    }

    @Test
    public void testKeywords() {
        this.lex = new Lex("if int else return", null);
        if (this.lex.getNext() != 10) System.out.println("if faild");
        if ((state = this.lex.getNext()) != 54) System.out.println("testID faild - split");
        if (this.lex.getNext() != 11) System.out.println("int faild");
        if ((state = this.lex.getNext()) != 54) System.out.println("testID faild - split");
        if (this.lex.getNext() != 14) System.out.println("else  faild");
        if ((state = this.lex.getNext()) != 54) System.out.println("testID faild - split");
        if (this.lex.getNext() != 15) System.out.println("return  faild");
    }

    @Test
    public void testOperators() {
        this.lex = new Lex("+ - = -+ +=", null);
        if (this.lex.getNext() != 31) System.out.println("+ faild");
        if ((state = this.lex.getNext()) != 54) System.out.println("testID faild - split");
        if (this.lex.getNext() != 32) System.out.println("- faild");
        if ((state = this.lex.getNext()) != 54) System.out.println("testID faild - split");
        if (this.lex.getNext() != 33) System.out.println("=  faild");
        if ((state = this.lex.getNext()) != 54) System.out.println("testID faild - split");
        if (this.lex.getNext() != 32) System.out.println("-  faild");
        if ((state = this.lex.getNext()) != 54) System.out.println("testID faild - split");
        if (this.lex.getNext() != 31) System.out.println("+ faild");
        if ((state = this.lex.getNext()) != 54) System.out.println("testID faild - split");
        if (this.lex.getNext() != 31) System.out.println("+ faild");
        if ((state = this.lex.getNext()) != 54) System.out.println("testID faild - split");
        if (this.lex.getNext() != 33) System.out.println("=  faild");
    }

    @Test
    public void testBreckets() {
        this.lex = new Lex(" \n \t", null);
        int state;
        while ((state = this.lex.getNext()) != -1) {
            System.out.println(state);
        }
    }
}