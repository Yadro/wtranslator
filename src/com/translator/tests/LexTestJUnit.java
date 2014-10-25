package com.translator.tests;

import com.translator.Lex;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by i-am on 22.10.14.
 */
public class LexTestJUnit {

    private Lex lex;

    @Before
    public void init() {
        this.lex = new Lex("if else return lol l12345678 l123456789 ; \n \"");
    }

    @Test
    public void test() {
        int pos = 0;
        while ((pos = this.lex.getNext(pos)) != -1) {}
    }

}