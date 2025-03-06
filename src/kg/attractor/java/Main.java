package kg.attractor.java;

import kg.attractor.java.lesson46.Lesson46;
import kg.attractor.java.lesson47.Lesson47Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new Lesson47Server("localhost", 9889).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
