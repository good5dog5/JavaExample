package com.Jordan.Example.AsyncExample.CompletableFutureExample;

import java.util.concurrent.CompletableFuture;

public class AsyncThreads {
    private static void whichThread(final String action) {
        System.out.printf("%s run at %s\n", action, Thread.currentThread().getName());
    }

    private static String world() {
        whichThread("world");
        return "world";
    }

    private static CompletableFuture<String> hello(final String who) {
        whichThread("hello");
        return CompletableFuture.completedFuture(String.format("hello %s", who));
    }

    private static void say(final String sth) {
        whichThread("say");
        System.out.println(sth);
    }

    public static void main(String[] args) {
        whichThread("main");
        CompletableFuture.supplyAsync(AsyncThreads::world)
                .thenComposeAsync(AsyncThreads::hello)
                .thenAccept(AsyncThreads::say);
    }
}
