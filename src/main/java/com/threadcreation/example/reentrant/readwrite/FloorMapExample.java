package com.threadcreation.example.reentrant.readwrite;

import java.util.NavigableMap;
import java.util.TreeMap;

public class FloorMapExample {
    public static void main(String[] args) {
        NavigableMap<Character, Integer> nm = new TreeMap<>();
        nm.put('C', 888);
        nm.put('D', 768);
        nm.put('Y', 999);
        nm.put('A', 444);
        nm.put('F', 784);
        nm.put('T', 555);
        nm.put('B', 666);
        System.out.println(nm.floorEntry('E'));
        System.out.println(nm.ceilingEntry('F'));
    }
}
