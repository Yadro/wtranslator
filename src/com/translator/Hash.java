package com.translator;

import com.translator.exceptions.HashTableElemNotFound;
import com.translator.exceptions.HashTableIsFull;

public class Hash {

    private final String[] hash_table;
    private final int size;

    public Hash(int size) {
        this.size = size;
        this.hash_table = new String[size+1];
    }


    public void push(String value) throws HashTableIsFull {
        boolean fl = false;
        int hash = getHash(value);
        int i = hash;
        while(true) {
            if (i > this.size) {
                i = 0;
                fl = true;
            } else if (fl && i > hash) {
                throw new HashTableIsFull();
            }

            if (this.hash_table[i] == null) {
                this.hash_table[i] = value;
                return;
            }
            i++;
        }
    }

    public String find(String value) throws HashTableElemNotFound {
        boolean fl = false;
        int hash = getHash(value);
        int i = hash;
        while(true) {
            if (i > this.size) {
                i = 0;
                fl = true;
            } else if (fl && i > hash) {
                throw new HashTableElemNotFound();
            }

            if (this.hash_table[i].equals(value)) {
                return this.hash_table[i];
            }
            i++;
        }
    }

    private int getHash(String value) {
        int len = value.length();
        int k = 11;
        long key = 0;
        for (int i = 0; i < len; i++) {
            key += value.charAt(i) * Math.pow(k, i);
        }
        return (int)(key % this.size);
    }
}
