package com.translator;

import com.translator.exceptions.HashTableElemNotFound;
import com.translator.exceptions.HashTableIsFull;

public class Hash {

    private final String name_table;
    private final String[] values;
    private final int size;

    public Hash(String name_table, int size) {
        this.name_table = name_table;
        this.size = size;
        this.values = new String[size+1];
    }

    public int add(String value) throws HashTableIsFull {
        boolean fl = false;
        for (int hash = getHash(value), i = hash;; i++) {
            if (i > this.size) {
                i = 0;
                fl = true;
            } else if (fl && i > hash) {
                throw new HashTableIsFull();
            }
            if (this.values[i] == null) {
                this.values[i] = value;
                return i;
            }
        }
    }

    public String find(String value) throws HashTableElemNotFound {
        boolean fl = false;
        for (int hash = getHash(value), i = hash;; i++) {
            if (i > this.size) {
                i = 0;
                fl = true;
            } else if (fl && i > hash) {
                throw new HashTableElemNotFound();
            }

            if (this.values[i].equals(value)) {
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

    public void printTable() {
        IOclass.println("\nTable " + name_table + ":");
        for (int i = 0; i < this.size; i++) {
            if (values[i] != null) {
                IOclass.println("[" + i + "]\t" + this.values[i]);
            }
        }
    }
}
