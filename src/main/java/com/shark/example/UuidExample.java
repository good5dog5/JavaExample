package com.shark.example;

import java.util.UUID;

public class UuidExample {
    public static void main(String[] argv) {
        UUID uuid = UUID.randomUUID();
        System.out.println("UUID: " + uuid.toString().replace("-", ""));
    }
}
