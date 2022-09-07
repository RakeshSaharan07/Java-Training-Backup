package com.examples.java.core;

public class ControlStructuresDemo {
    public static void main(String[] args) {
        int a = 2;
        int b = 2;

        // control structure
        // if/else
        System.out.println("\n" + "if..else example...");
        if (a == b) {
            System.out.println("a and b are equal");
        } else {
            System.out.println("a and b are not equal");
        }

        // switch..case statement
        System.out.println("\n" + "switch..case example...");
        switch (a) {
            case 1:
                System.out.println("a has value 1");
                break;
            case 2:
                System.out.println("a has value 2");
                break;
            default:
                System.out.println("a has value " + a);
        }
    }
}
