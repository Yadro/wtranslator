package com.translator;

import com.translator.exceptions.HashTableElemNotFound;
import com.translator.exceptions.HashTableIsFull;

public class Hash {

    private final String[] keys;
    private final String[] values;
    private final int size;

    public Hash(int size) {
        this.size = size;
        this.keys = new String[size+1];
        this.values = new String[size+1];
    }


    public void push(String key, String value) throws HashTableIsFull {
        boolean fl = false;
        for(int hash = getHash(key), i = hash;; i++) {
            if (i > this.size) {
                i = 0;
                fl = true;
            } else if (fl && i > hash) {
                throw new HashTableIsFull();
            }
            if (this.keys[i] == null) {
                this.keys[i] = key;
                this.values[i] = value;
                return;
            }
        }
    }

    public String find(String key) throws HashTableElemNotFound {
        boolean fl = false;
        for(int hash = getHash(key), i = hash;; i++) {
            if (i > this.size) {
                i = 0;
                fl = true;
            } else if (fl && i > hash) {
                throw new HashTableElemNotFound();
            }

            if (this.keys[i].equals(key)) {
                return this.values[i];
            }
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
