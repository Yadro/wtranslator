package com.translator.tests;

import com.translator.Hash;
import com.translator.IOclass;
import com.translator.Lex;
import com.translator.Main;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by i-am on 22.10.14.
 */
public class LexTestJUnit extends Assert {

    private Lex lex;

    @Before
    public void start() {
        new IOclass(null, "output.txt");
    }

    @Test
    public void testINT() {
        this.lex = new Lex("1234567", null);
        assertEquals(this.lex.getNext(), 1);
    }

    @Test
    public void testID() {
        this.lex = new Lex("a", null);
        assertEquals(this.lex.getNext(), 1);
    }

    @Test
    public void testID2() {
        this.lex = new Lex("ab", null);
        assertEquals(this.lex.getNext(), 2);
    }

    @Test
    public void testID3() {
        this.lex = new Lex("abc", null);
        assertEquals(this.lex.getNext(), 3);
    }

    @Test
    public void testID4() {
        this.lex = new Lex("abcd", null);
        assertEquals(this.lex.getNext(), 4);
    }

    @Test
    public void testID5() {
        this.lex = new Lex("abcde", null);
        assertEquals(this.lex.getNext(), 5);
    }

    @Test
    public void testID6() {
        this.lex = new Lex("abcdef", null);
        assertEquals(this.lex.getNext(), 6);
    }

    @Test
    public void testID7() {
        this.lex = new Lex("abcdefg", null);
        assertEquals(this.lex.getNext(), 7);
    }

    @Test
    public void testID8() {
        this.lex = new Lex("abcdefgk", null);
        assertEquals(this.lex.getNext(), 8);
    }



    @Test
    public void testIf10() {
        this.lex = new Lex("==", null);
        assertEquals(this.lex.getNext(), 10);
    }

    @Test
    public void testIf11() {
        this.lex = new Lex("!=", null);
        assertEquals(this.lex.getNext(), 11);
    }

    @Test
    public void testIf12() {
        this.lex = new Lex("<=", null);
        assertEquals(this.lex.getNext(), 12);
    }

    @Test
    public void testIf13() {
        this.lex = new Lex(">=", null);
        assertEquals(this.lex.getNext(), 13);
    }

    @Test
    public void testIf14() {
        this.lex = new Lex("<", null);
        assertEquals(this.lex.getNext(), 15);
    }

    @Test
    public void testIf15() {
        this.lex = new Lex(">", null);
        assertEquals(this.lex.getNext(), 15);
    }


    @Test
    public void testKeywordInt() {
        this.lex = new Lex("int", null);
        assertEquals(this.lex.getNext(), 21);
    }

    @Test
    public void testKeywordIf() {
        this.lex = new Lex("if", null);
        assertEquals(this.lex.getNext(), 20);
    }

    @Test
    public void testKeywordElse() {
        this.lex = new Lex("else", null);
        assertEquals(this.lex.getNext(), 22);
    }

    @Test
    public void testKeywordReturn() {
        this.lex = new Lex("return", null);
        assertEquals(this.lex.getNext(), 23);
    }


    @Test
    public void testOperatorPlus() {
        this.lex = new Lex("+", null);
        assertEquals(this.lex.getNext(), 31);
    }

    @Test
    public void testOperatorMinus() {
        this.lex = new Lex("-", null);
        assertEquals(this.lex.getNext(), 32);
    }

    @Test
    public void testOperatorEqually() {
        this.lex = new Lex("=", null);
        assertEquals(this.lex.getNext(), 33);
    }


    @Test
    public void testSplitNewLine() {
        this.lex = new Lex("\n", null);
        assertEquals(this.lex.getNext(), 1);
    }

    @Test
    public void testSplitTab() {
        this.lex = new Lex("\t", null);
        assertEquals(this.lex.getNext(), 2);
    }

    @Test
    public void testSplitR() {
        this.lex = new Lex("\r", null);
        assertEquals(this.lex.getNext(), 3);
    }

    @Test
    public void testSplitSpace() {
        this.lex = new Lex(" ", null);
        assertEquals(this.lex.getNext(), 4);
    }


    @Test
    public void testMarkBeginFig() {
        this.lex = new Lex("{", null);
        assertEquals(this.lex.getNext(), 1);
    }

    @Test
    public void testMarkEndFig() {
        this.lex = new Lex("{", null);
        assertEquals(this.lex.getNext(), 2);
    }

    @Test
    public void testMarkBeginRound() {
        this.lex = new Lex("(", null);
        assertEquals(this.lex.getNext(), 3);
    }

    @Test
    public void testMarkEndRound() {
        this.lex = new Lex(")", null);
        assertEquals(this.lex.getNext(), 4);
    }


}