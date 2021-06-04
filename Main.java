package com.example.test;

public class Main {

    public static void main(String[] args) {
        Utilities util = new Utilities();
        char[] ar = {'a', 'b', 'c', 'd'};
        char[] newAr = util.everyNthChar(ar, 1);
        for (char a : newAr) {
            System.out.println(a);
        }

        System.out.println(util.removePairs("aabb"));
    }
}
